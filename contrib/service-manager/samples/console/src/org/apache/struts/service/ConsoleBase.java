package org.apache.struts.service.samples;

import java.io.*;
import org.apache.struts.service.*;

public class ConsoleBase {

        public static void main( java.lang.String[] args ) {

                BufferedReader stdin = new BufferedReader( new InputStreamReader( System.in ) );
                ServiceManager manager = new ServiceManager();
                String processName = null;
                String message = "Enter any string...\nCommand strings:\ntest\nanotherTest\nquit\n";

                try {
                        manager.init();

                        while( true ) {
                                System.out.println( message );
                                processName = stdin.readLine();
                                if( "quit".equals( processName ) )
                                        break;
                                else
                                        manager.performCall( processName, null );
                        }

                        manager.destroy();
                } catch ( Exception e ) {
                        System.out.println( "Exception!" );
                        e.printStackTrace( System.out );
                }

        }

}