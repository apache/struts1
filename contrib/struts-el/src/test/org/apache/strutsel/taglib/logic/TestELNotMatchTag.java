package org.apache.strutsel.taglib.logic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import junit.framework.*;
import org.apache.cactus.*;
import org.apache.struts.taglib.logic.*;

import org.apache.strutsel.taglib.utils.TestFormBean;

public class TestELNotMatchTag
    extends JspTestCase {

    protected final static String PROP_KEY = "stringProperty";
    protected final static String VAR_KEY = "stringVar";
    protected final static String VALUE_KEY = "abcde";
    protected final static String PREFIX_VALUE_KEY = "abc";
    protected final static String BAD_VALUE_KEY = "abx";
    protected ELNotMatchTag elNotMatchTag = null;

    public TestELNotMatchTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELNotMatchTag.class.getName() });
    }

    public static Test suite() {

        return new TestSuite(TestELNotMatchTag.class);
    }

    public void setUp() {
        elNotMatchTag = new ELNotMatchTag();
        elNotMatchTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elNotMatchTag = null;
    }

    public void beginMatchStringMatches(WebRequest testRequest) {
    }

    public void testMatchStringMatches()
                                throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();
        formBean.setStringProperty(VALUE_KEY);
        pageContext.setAttribute("testFormBean", formBean);

        pageContext.setAttribute(VAR_KEY, VALUE_KEY);

        elNotMatchTag.setName("testFormBean");
        elNotMatchTag.setProperty(PROP_KEY);

        elNotMatchTag.setValue(VALUE_KEY);

        int startTagReturn = elNotMatchTag.doStartTag();
        assertEquals("Match string matches comparison", false, 
                     startTagReturn == Tag.EVAL_BODY_INCLUDE);
    }

//     public void testPrefixMatchStringMatches()
//                                       throws ServletException, JspException {
//         pageContext.setAttribute(PROP_KEY, PREFIX_VALUE_KEY);
//         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
//         elNotMatchTag.setVar(VAR_KEY);
//         elNotMatchTag.setValue("${" + PROP_KEY + "}");

//         int startTagReturn = elNotMatchTag.doStartTag();
//         assertEquals("Match prefix string matches comparison", false, 
//                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
//     }

//     public void testMatchStringNotMatches()
//                                    throws ServletException, JspException {
//         pageContext.setAttribute(PROP_KEY, BAD_VALUE_KEY);
//         pageContext.setAttribute(VAR_KEY, VALUE_KEY);
//         elNotMatchTag.setVar(VAR_KEY);
//         elNotMatchTag.setValue("${" + PROP_KEY + "}");

//         int startTagReturn = elNotMatchTag.doStartTag();
//         assertEquals("unMatched string not matches comparison", true, 
//                      startTagReturn == Tag.EVAL_BODY_INCLUDE);
//     }
}
