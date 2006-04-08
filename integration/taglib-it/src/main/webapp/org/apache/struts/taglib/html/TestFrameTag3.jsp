<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!-- --------Testing attributes using action------ -->
<logic:equal name="runTest" value="testFrameAction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionAnchor">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" anchor="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do#XXX")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionFrameborder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" frameborder="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               frameborder="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionFrameName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" frameName="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               name="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionLongdesc">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" longdesc="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               longdesc="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionMarginheight">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" marginheight="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               marginheight="15">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionMarginwidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" marginwidth="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               marginwidth="10">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramMap"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionNamePropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramPropertyMap"
                    property="map"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map"
                 type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramMap"
                    scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testFrameActionNamePropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramPropertyMap"
                    property="map" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map"
                 type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramMap" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNamePropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramPropertyMap"
                    property="map" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map"
                 type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramMap" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNamePropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" name="paramPropertyMap"
                    property="map" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map"
                 type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=org.apache.struts.taglib.TagUtils.getInstance().computeURL(pageContext, null, null, null, "simpleAction", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionNoresize1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNoresize2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>"
               noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNoresize3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNoresize4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNoresize5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionNoresize6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" noresize="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL( request.getContextPath() + "/simpleAction.do")%>">
    </bean:define>
</logic:equal>


<%
    String expected = (String) pageContext.getAttribute("EXPECTED_RESULTS");
    String compareTo = (String) pageContext.getAttribute("TEST_RESULTS");

    if ((expected == null) || (expected == null)) {
        Assert.fail(
                "An invalid (or mispelled) test on this page was called.  Please verify that you've setup the tests (and spellings) correctly.");
    }

    Assert.assertEquals(expected, compareTo);
%>