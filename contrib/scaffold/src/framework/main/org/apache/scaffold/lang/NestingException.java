package org.apache.scaffold.lang;


import java.io.StringWriter;
import java.io.PrintWriter;


/**
 * http://www.javaworld.com/javaworld/javatips/jw-javatip91.html
 * @author Terren Suydam
 * @version $Revision: 1.2 $ $Date: 2002/03/05 02:29:24 $
 */
public class NestingException extends Exception {
     // the nested exception

     private Throwable nestedException;
     // String representation of stack trace - not transient!

     private String stackTraceString;
     // convert a stack trace to a String so it can be serialized

     static public String generateStackTraceString(Throwable t) {

             StringWriter s = new StringWriter();

             t.printStackTrace(new PrintWriter(s));

             return s.toString();

     }
     // java.lang.Exception constructors

     public NestingException() {}
     public NestingException(String msg) {

             super(msg);

     }
     // additional c'tors - nest the exceptions, storing the stack trace

     public NestingException(Throwable nestedException) {

             this.nestedException = nestedException;

             stackTraceString = generateStackTraceString(nestedException);

     }
     public NestingException(String msg, Throwable nestedException) {

             this(msg);

             this.nestedException = nestedException;

             stackTraceString = generateStackTraceString(nestedException);

     }
     // methods

     public Throwable getNestedException() {return nestedException;}
     // descend through linked-list of nesting exceptions, & output trace

     // note that this displays the 'deepest' trace first

     public String getStackTraceString() {

             // if there's no nested exception, there's no stackTrace

             if (nestedException == null)
                     return null;
             StringBuffer traceBuffer = new StringBuffer();
             if (nestedException instanceof NestingException) {

traceBuffer.append(((NestingException)nestedException).getStackTraceString());
traceBuffer.append("-------- nested by:\n");

             }
             traceBuffer.append(stackTraceString);

             return traceBuffer.toString();

     }
     // overrides Exception.getMessage()

     public String getMessage() {

             // superMsg will contain whatever String was passed into the
             // constructor, and null otherwise.

             String superMsg = super.getMessage();
             // if there's no nested exception, do like we would always do

             if (getNestedException() == null)

                     return superMsg;
             StringBuffer theMsg = new StringBuffer();
             // get the nested exception's message

             String nestedMsg = getNestedException().getMessage();
             if (superMsg != null)
                     theMsg.append(superMsg).append(": ").append(nestedMsg);
             else
                     theMsg.append(nestedMsg);
             return theMsg.toString();

     }

     // overrides Exception.toString()

     public String toString() {

             StringBuffer theMsg = new StringBuffer(super.toString());
             if (getNestedException() != null)

                     theMsg.append("; \n\t---> nested ").append(getNestedException());
             return theMsg.toString();

     }
}