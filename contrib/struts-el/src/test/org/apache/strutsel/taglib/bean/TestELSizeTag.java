package org.apache.strutsel.taglib.bean;

import junit.framework.Test;
import junit.framework.TestSuite;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import org.apache.cactus.JspTestCase;
import org.apache.strutsel.taglib.utils.TestFormBean;

public class TestELSizeTag extends JspTestCase
{
    protected  ELSizeTag   elSizeTag   = null;

    public TestELSizeTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELSizeTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELSizeTag.class);
    }

    public void setUp() {
        elSizeTag = new ELSizeTag();
        elSizeTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elSizeTag = null;
    }

    public void testPlain()
        throws ServletException, JspException {
        TestFormBean formBean = new TestFormBean();
        formBean.setArrayProperty(new String[] {"abc", "def", "ghi"});
        pageContext.setAttribute("testFormBean", formBean);

        elSizeTag.setId("sizeVar");
        elSizeTag.setName("testFormBean");
        elSizeTag.setProperty("arrayProperty");

        int startTagReturn = elSizeTag.doStartTag();

        Object object   = pageContext.getAttribute("sizeVar");
        if (object != null) {
            if (object instanceof Integer) {
                if (((Integer) object).intValue() != 3) {
                    fail("Size variable \"sizeVar\" is not equal to 3.");
                }
            }
            else {
                fail("Size variable \"sizeVar\" is not an Integer object.");
            }
        }
        else {
            fail("Size variable \"sizeVar\" not in page context.");
        }
    }

    public void testCollectionProperty()
        throws ServletException, JspException {

        TestFormBean formBean = new TestFormBean();
        formBean.setArrayProperty(new String[] {"abc", "def", "ghi"});
        pageContext.setAttribute("testFormBean", formBean);

        elSizeTag.setId("sizeVar");
        elSizeTag.setCollectionExpr("${" + "testFormBean.arrayProperty" + "}");

        int startTagReturn = elSizeTag.doStartTag();

        System.out.println("collection[" + elSizeTag.getCollection() + "]");

        Object object   = pageContext.getAttribute("sizeVar");
        if (object != null) {
            if (object instanceof Integer) {
                if (((Integer) object).intValue() != 3) {
                    fail("Size variable \"sizeVar\" is not equal to 3.");
                }
            }
            else {
                fail("Size variable \"sizeVar\" is not an Integer object.");
            }
        }
        else {
            fail("Size variable \"sizeVar\" not in page context.");
        }
    }
}
