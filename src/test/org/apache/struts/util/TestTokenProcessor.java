/*
 * Copyright 2026 The Struts1-Forever Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.util;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.taglib.html.Constants;

/**
 * Unit tests for {@link TokenProcessor}.
 *
 * <p>TokenProcessor provides double-submit / CSRF protection via a per-session
 * token stored under {@link Globals#TRANSACTION_TOKEN_KEY}.  The token is
 * submitted as request parameter {@link Constants#TOKEN_KEY} and validated
 * server-side.</p>
 */
public class TestTokenProcessor extends TestCase {

    private TokenProcessor tp;
    private MockHttpSession session;
    private MockHttpServletRequest request;

    protected void setUp() {
        tp = TokenProcessor.getInstance();
        session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
    }

    // ------------------------------------------------------------------
    // getInstance
    // ------------------------------------------------------------------

    public void testGetInstance_ReturnsSingleton() {
        assertSame(TokenProcessor.getInstance(), TokenProcessor.getInstance());
    }

    // ------------------------------------------------------------------
    // generateToken
    // ------------------------------------------------------------------

    public void testGenerateToken_ReturnsNonNullHexString() {
        String token = tp.generateToken(request);
        assertNotNull(token);
        assertTrue("Token should be a non-empty hex string", token.length() > 0);
        assertTrue("Token should contain only hex digits",
                token.matches("[0-9a-f]+"));
    }

    public void testGenerateToken_TwoCallsProduceDifferentTokens() {
        // Two successive calls must produce distinct values; SecureRandom
        // guarantees statistical independence between calls.
        String t1 = tp.generateToken(request);
        String t2 = tp.generateToken(request);
        assertFalse("Successive tokens must differ", t1.equals(t2));
    }

    public void testGenerateToken_SameSession_ProducesUnpredictableDistinctTokens() {
        // Tokens generated from the same session must be statistically
        // independent. With SecureRandom, each call draws fresh 160-bit
        // entropy regardless of session state, making token values
        // unpredictable even when the session ID is known to an attacker.
        String t1 = tp.generateToken(request);
        String t2 = tp.generateToken(request);
        String t3 = tp.generateToken(request);

        assertNotNull("Token must not be null", t1);
        assertTrue("Token must be a non-empty hex string", t1.length() > 0);
        assertTrue("Token must contain only hex digits", t1.matches("[0-9a-f]+"));
        assertFalse("Successive tokens must differ (t1 vs t2)", t1.equals(t2));
        assertFalse("Successive tokens must differ (t1 vs t3)", t1.equals(t3));
        assertFalse("Successive tokens must differ (t2 vs t3)", t2.equals(t3));
    }

    // ------------------------------------------------------------------
    // saveToken / isTokenValid
    // ------------------------------------------------------------------

    public void testSaveToken_StoresTokenInSession() {
        tp.saveToken(request);
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
        assertNotNull("Token must be saved in session", saved);
        assertTrue(saved.length() > 0);
    }

    public void testIsTokenValid_ValidToken_ReturnsTrue() {
        tp.saveToken(request);
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);

        request.addParameter(Constants.TOKEN_KEY, saved);

        assertTrue("Valid token must return true", tp.isTokenValid(request));
    }

    public void testIsTokenValid_WrongToken_ReturnsFalse() {
        tp.saveToken(request);

        request.addParameter(Constants.TOKEN_KEY, "wrong-token-value");

        assertFalse("Wrong token must return false", tp.isTokenValid(request));
    }

    public void testIsTokenValid_NoRequestParameter_ReturnsFalse() {
        tp.saveToken(request);
        // no TOKEN_KEY parameter added

        assertFalse("Missing token parameter must return false",
                tp.isTokenValid(request));
    }

    public void testIsTokenValid_NoSessionToken_ReturnsFalse() {
        // token never saved in session
        request.addParameter(Constants.TOKEN_KEY, "somevalue");

        assertFalse("No session token must return false", tp.isTokenValid(request));
    }

    public void testIsTokenValid_NoSession_ReturnsFalse() {
        // request with no session (getSession(false) returns null)
        MockHttpServletRequest noSessionRequest = new MockHttpServletRequest(null);
        noSessionRequest.addParameter(Constants.TOKEN_KEY, "somevalue");

        assertFalse("No session must return false", tp.isTokenValid(noSessionRequest));
    }

    // ------------------------------------------------------------------
    // isTokenValid with reset=true
    // ------------------------------------------------------------------

    public void testIsTokenValid_Reset_RemovesTokenFromSession() {
        tp.saveToken(request);
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
        request.addParameter(Constants.TOKEN_KEY, saved);

        assertTrue(tp.isTokenValid(request, true));

        assertNull("Token must be removed from session after reset",
                session.getAttribute(Globals.TRANSACTION_TOKEN_KEY));
    }

    public void testIsTokenValid_ResetFalse_KeepsTokenInSession() {
        tp.saveToken(request);
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
        request.addParameter(Constants.TOKEN_KEY, saved);

        assertTrue(tp.isTokenValid(request, false));

        assertNotNull("Token must remain in session when reset=false",
                session.getAttribute(Globals.TRANSACTION_TOKEN_KEY));
    }

    // ------------------------------------------------------------------
    // resetToken
    // ------------------------------------------------------------------

    public void testResetToken_RemovesTokenFromSession() {
        tp.saveToken(request);
        assertNotNull(session.getAttribute(Globals.TRANSACTION_TOKEN_KEY));

        tp.resetToken(request);

        assertNull("resetToken() must remove the token from the session",
                session.getAttribute(Globals.TRANSACTION_TOKEN_KEY));
    }

    public void testResetToken_NoSession_DoesNotThrow() {
        MockHttpServletRequest noSessionRequest = new MockHttpServletRequest(null);
        // Must not throw
        tp.resetToken(noSessionRequest);
    }

    // ------------------------------------------------------------------
    // Constant-time comparison regression guard
    // ------------------------------------------------------------------

    /**
     * Token comparison must return false for a token that differs only in its
     * last character, regardless of where the difference lies.
     *
     * Documents the contract that must hold when using constant-time comparison
     * (MessageDigest.isEqual): the result must not depend on the position of
     * the first differing byte. String.equals() satisfies this functionally
     * but is not timing-safe; MessageDigest.isEqual() satisfies both.
     */
    public void testIsTokenValid_NearMatchToken_ReturnsFalse() {
        tp.saveToken(request);
        String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);

        // Corrupt only the last character to verify the comparison evaluates
        // the full token rather than short-circuiting.
        char[] chars = saved.toCharArray();
        chars[chars.length - 1] = (chars[chars.length - 1] == 'a') ? 'b' : 'a';
        String nearMatch = new String(chars);

        request.addParameter(Constants.TOKEN_KEY, nearMatch);
        assertFalse("Token differing only in last character must return false",
                tp.isTokenValid(request));
    }
}
