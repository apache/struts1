package org.apache.struts.upload;

import junit.framework.TestSuite;
import junit.framework.Test;
import junit.framework.TestCase;

public class MultipartTestSuite extends TestSuite
{

    public static final void main(String args[])
    {
        junit.textui.TestRunner.run(MultipartTestSuite.suite());
        System.exit(0);
    }


    public static Test suite()
    {
        TestSuite suite = new MultipartTestSuite();
        suite.addTest(new TestSuite(MultipartBoundaryInputStreamTest.class));

        return suite;
    }



}
