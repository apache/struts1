package org.apache.scaffold.text;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import java.sql.Timestamp;


/**
 * An <b>experimental</b> class with some standard conversion
 * utilities. Needs more proof of concept and unit testing.
 *
 * @author Ted Husted
 * @version $Revision: 1.2 $ $Date: 2002/03/05 02:29:48 $
 */
public class ConvertUtils {

/*
    protected Locale[] availableLocales = null;
    public static Locale[] getAvailableLocales() {
        return availableLocales;
    }
    public static void setAvailableLocales(Locale[] _availableLocales) {
        availableLocales = _availableLocales;
    }

*/


// --------------------------------------------------------------- Text


    /**
     * An empty string.
     */
    public static String EMPTY_STRING = "";


    /**
     * Returns true if null or trims to an empty string.
     */
    public static boolean isBlank(String s) {
        return ((s==null) || (s.trim().equals(EMPTY_STRING)));
    }


    /**
     * Appends name=value parameter.
     */
    public static String addParam(String path, String name, String value) {
        StringBuffer uri = new StringBuffer(path);
        boolean isQuery = (path.indexOf("?")>=0);
        if (isQuery)
            uri.append("&amp;");
        else
            uri.append("?");
        uri.append(name);
        uri.append("=");
        uri.append(value);
        return uri.toString();

     }


// ------------------------------------------------------------ Numeric


    /**
     * Return String with of digits only (0..9).
     * http://java.sun.com/j2se/1.4/docs/api/java/lang/Character.html
     */
    public static String getDigits(String s) {
        if (s==null) return null;
        int n = s.length();
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) sb.append(c);
        }
        return (sb.toString());
    }


    /**
     * Returns number formatted for default or given locale.
     */
    public static String getNumber(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getInstance().format(value));
       return (NumberFormat.getInstance(locale).format(value));
    }


    /**
     * Returns percent formatted for default or given locale.
     */
    public static String getPercent(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getPercentInstance().format(value));
        return (NumberFormat.getPercentInstance(locale).format(value));
    }


    /*
    public static String getInteger(Number value, Locale locale) {
        if (locale==null)
            return (NumberFormat.getIntegerInstance().format(value));
        return (NumberFormat.getIntegerInstance(locale).format(value));
    }
    */


    /*
     * Returns whether the last digit of numeric string is a parity
     * check on the others per the "primes of nines" method.
     * Reference: http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
     * @param Number - Number to check.
     * Must be all digits and not null.
     */
    public static boolean luhnCheck(String number) {
        int no_digit = number.length();
        int oddoeven = no_digit & 1;
        long sum = 0;
        for (int count = 0; count < no_digit; count++) {
            int digit = Integer.parseInt(
                String.valueOf(number.charAt(count)));
            if ( ( (count & 1) ^ oddoeven) ==0 ) { // not
                digit *= 2;
                if (digit > 9) digit -= 9;
            };
            sum += digit;
        };
        if (sum == 0) return false;
        if (sum % 10 == 0) return true;
        return false;
    }


    /**
     * Returns number with the appropriate digit appended
     * so that is passes a "luhnCheck".
     * @param Number - Number to process.
     * Must be all digits and not null.
     */
    public static String addLuhnDigit(String number) {
        // I don't actually understand the alogorithm
        // so we just use brute force to find the digit.
        char[] digits = {'1','2','3','4','5','6','7','8','9','0'};
        int c = number.length();
        StringBuffer tryNumber = new StringBuffer(number + digits[0]);
        int i;
        for (i=0; i<10; i++) {
            tryNumber.setCharAt(c,digits[i]);
            if (luhnCheck(tryNumber.toString()))
                break;
        }
        return tryNumber.toString();
    }


// ------------------------------------------------------------ Decimal


    /**
     * Default decimal pattern.
     * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
     */
    public static String DECIMAL_PATTERN ="###,###.###";


    /**
     * Symbols that can be used in a decimal pattern.
     */
    public static DecimalFormatSymbols getGenericDecimal(Locale locale) {
        DecimalFormatSymbols symbols =
            new DecimalFormatSymbols(locale);
        symbols.setGroupingSeparator('`'); // :FIXME: Want apostrophe here
        return symbols;
    }


    /**
     * Return decimal number formatted for default or given locale.
     */
    public static String getDecimal(Number value, Locale locale) {
        if (locale==null)
            return (DecimalFormat.getInstance().format(value));
        return (DecimalFormat.getInstance().format(value));
    }


    /**
     * Return decimal number formatted for default or given locale
     * using given pattern.
     */
    public static String getDecimal(Number value, Locale locale, String pattern) {
        NumberFormat formatter;
        if (locale==null)
            formatter = new java.text.DecimalFormat(pattern);
        else {
            formatter = NumberFormat.getNumberInstance(locale);
            DecimalFormat df = (DecimalFormat) formatter;
            df.applyPattern(pattern);
            return df.format(value);
        }
        return (formatter.format(value));
    }


    /**
     *  Return decimal for default locale using standard pattern.
     */
    public static String getDecimal(Number value) {
        return getDecimal(value,(Locale) null,DECIMAL_PATTERN);
    }


// ----------------------------------------------------------- Currency


    /**
     * Standard currency pattern.
     * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
     */
    public static String CURRENCY_PATTERN ="$" + DECIMAL_PATTERN;


    /**
     *  Return currency for default locale using standard pattern.
     */
    public static String getCurrency(Number value) {
        return getDecimal(value,null,CURRENCY_PATTERN);
    }


// --------------------------------------------------------------- Date


    /**
     * Default style for dates and times.
     */
    public static int DEFAULT = DateFormat.DEFAULT;


    /**
     * Short style for dates and times.
     */
    public static int SHORT = DateFormat.SHORT;


    /**
     * Medium style for dates and times.
     */
    public static int MEDIUM = DateFormat.MEDIUM;


    /**
     * Long style for dates and times.
     */
    public static int LONG = DateFormat.LONG;


    /**
     * Full style for dates and times.
     */
    public static int FULL = DateFormat.FULL;


    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;


    /**
     * A "default" date format.
     */
    public static String ESCAPE_DATE_PATTERN = "yyyy-mm-dd";


    /**
     * Convert String to Date using given format.
     * Returns null if conversion fails.
     * Set lenient=false to disallow dates like 2001-9-32.
     * http://java.sun.com/j2se/1.4/docs/api/java/text/SimpleDateFormat.html
     * @author Hal Deadman
     */
    public static Date getDate(String dateText,
            String format, boolean lenient) {
        if (dateText == null) {
            return null;
        }
        DateFormat df = null;
        try {
            if (format==null) {
                df = new SimpleDateFormat();
            }
            else {
                df = new SimpleDateFormat(format);
            }
                // setLenient avoids allowing dates like 9/32/2001
                // which would otherwise parse to 10/2/2001
            df.setLenient(false);
            return df.parse(dateText);
        }
        catch(ParseException e) {
            return null;
        }
    }


    /**
     * Convert String to Date using given format.
     * Returns null if conversion fails.
     * Uses "strict" coNversion (lenient=false).
     * @author Hal Deadman
     */
    public static Date getDate(String dateString, String format) {
        return getDate(dateString,format,LENIENT_DATE);
    }


    /**
     * Convert String to Date using a medium (weekday day month year) format.
     * Returns null if conversion fails.
     * Uses "strict" coNversion (lenient=false).
     * @author Hal Deadman
     */
    public static Date getDate(String dateString) {
        return getDate(dateString,null,LENIENT_DATE);
    }


    /**
     * Return Date value using a String.
     * Null or conversion error returns null.
     * @param String representing Date
     */
    public static Date toDate(String string) {
        if (string==null)
            return null;
        else try {
            return getDate(string);
        } catch (Throwable t) {
            return null;
        }
    }


    /**
     * Convert date to String for given locale in given style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date, Locale locale, int style) {
        if (locale==null)
            return (DateFormat.getDateInstance(style).format(date));
        return (DateFormat.getDateInstance(style,locale).format(date));
    }


    /**
     * Convert date to String for default locale in given style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date, int style) {
       return getDate(date,(Locale) null,style);
    }


    /**
     * Convert date to String for default locale in DEFAULT style.
     * A null locale will return the default locale.
     */
    public static String getDate(Date date) {
       return getDate(date,(Locale) null,DEFAULT);
    }


    /**
     * Return String value representing Date.
     * Null returns null.
     * @param Date
     */
    public static String toString(Date date) {
        if (date==null)
            return null;
        else
            return getDate(date);
    }


    /**
     * Return String value representing Date.
     * Null returns null.
     * @param Date
     */
    public static String toEscape(Date date) {
        if (date==null)
            return null;
        DateFormat df = null;
        try {
            df = new SimpleDateFormat(ESCAPE_DATE_PATTERN);
        } catch (Throwable t) {
            return null;
        }
        df.setLenient(false);
        return df.format(date);
    }


// ---------------------------------------------------------- Timestamp


    /**
     * Escape string to create Timestamp representing
     * "January 1, 1970 00:00:00".
     */
    public static String NULL_TIMESTAMP_TEXT = "1970-01-01 00:00:00";


    /**
     * Value needed to create Timestamp representing
     * "January 1, 1970 00:00:00".
     * From the documentation, you would think this would be
     * Timestamp(0), but empirical tests show it to be
     * Timestamp(18000000).
     */
    public static long NULL_TIME = (long) 18000000;


    /**
     * Timestamp representing "January 1, 1970 00:00:00".
     */
    public static Timestamp NULL_TIMESTAMP = new Timestamp(NULL_TIME);


    /**
     * Return null if timestamp is null or equals
     * "January 1, 1970 00:00:00".
     */
    public static boolean isNull(Timestamp timestamp) {
        return ((timestamp==null) || (timestamp.getTime()==NULL_TIME));
    }


    /**
     * Convert timestamp to String for given locale in given style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp,
            Locale locale, int style) {
        Date date = (Date) timestamp;
        if (locale==null)
            return (DateFormat.getDateTimeInstance(style,style).
                format(date));
        return (DateFormat.getDateTimeInstance(style,style,locale).
            format(date));
    }


    /**
     * Convert date to String for default locale in given style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp,
            int style) {
       return getTimestamp(timestamp,(Locale) null,style);
    }


    /**
     * Convert date to String for default locale in DEFAULT style.
     * A null locale will return the default locale.
     */
    public static String getTimestamp(Timestamp timestamp) {
       return getTimestamp(timestamp,(Locale) null,DEFAULT);
    }


    /**
     * Return Timestamp value using a String.
     * Null or conversion error returns null.
     * @param String representing Timestamp
     */
    public static Timestamp toTimestamp(String string) {
        if (string==null)
            return null;
        else try {
            return Timestamp.valueOf(string);
        } catch (Throwable t) {
            return null;
        }
    }


    /**
     * Return String value representing Timestamp.
     * Null returns null.
     * @param Timestamp
     */
    public static String toString(Timestamp timestamp) {
        if (timestamp==null)
            return null;
        else
            return timestamp.toString();
    }

}


/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
**/
