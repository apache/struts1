package org.apache.strutsel.taglib.logic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import junit.framework.*;
import org.apache.cactus.*;
import org.apache.struts.taglib.logic.*;

import org.apache.strutsel.taglib.utils.TestFormBean;

public class TestELMatchTag
    extends JspTestCase {

    protected final static String PROP_KEY = "stringProperty";
    protected final static String VAR_KEY = "stringVar";
    protected final static String VALUE_KEY = "abcde";
    protected final static String PREFIX_VALUE_KEY = "abc";
    protected final static String BAD_VALUE_KEY = "abx";
    protected ELMatchTag elMatchTag = null;

    public TestELMatchTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELMatchTag.class.getName() });
    }

    public static Test suite() {

        return new TestSuite(TestELMatchTag.class);
    }

    public void setUp() {
        elMatchTag = new ELMatchTag();
        elMatchTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elMatchTag = null;
    }

    public void beginMatchStringMatches(WebRequest testRequest) {
    }

    public void testMatchStringMatches()
                                throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();
        formBean.setStringProperty(VALUE_KEY);
        pageContext.setAttribute("testFormBean", formBean);

        pageContext.setAttribute(VAR_KEY, VALUE_KEY);

        elMatchTag.setName("testFormBean");
        elMatchTag.setProperty(PROP_KEY);

        elMatchTag.setValue(VALUE_KEY);

        int startTagReturn = elMatchTag.doStartTag();
        assertEquals("Match string matches comparison", true, 
                     startTagReturn == Tag.EVAL_BODY_INCLUDE);
    }

//     public void testPrefixMatchStringMatches()
//                                       throws ServletException, JspException {
//         pageContext.setAttribute(PROP_KEY, PREFIX_VALUE_KEY);
//         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
//         elMatchTag.setVar(VAR_KEY);
//         elMatchTag.setValue("${" + PROP_KEY + "}");

//         int startTagReturn = elMatchTag.doStartTag();
//         assertEquals("Match prefix string matches comparison", true, 
//                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
//     }

//     public void testMatchStringNotMatches()
//                                    throws ServletException, JspException {
//         pageContext.setAttribute(PROP_KEY, BAD_VALUE_KEY);
//         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
//         elMatchTag.setVar(VAR_KEY);
//         elMatchTag.setValue("${" + PROP_KEY + "}");

//         int startTagReturn = elMatchTag.doStartTag();
//         assertEquals("unMatched string not matches comparison", false, 
//                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
//     }
}
