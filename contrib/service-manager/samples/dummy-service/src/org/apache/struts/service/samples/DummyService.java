package org.apache.struts.service.samples;

import org.apache.commons.digester.Digester;
import org.apache.struts.service.Service;

public class DummyService extends Service {

        static private int totalNumber = 0;

        private int number = 0;

        private synchronized int increment() {
                return ++totalNumber;
        }

        public DummyService() {
                super();
                number = increment();
        }

        public void init()
                throws Exception {
                logHelper.log( "Init DummyService(" + Integer.toString(number) + ")" );
        }

        public void destroy()
                throws Exception {
                logHelper.log( "Destroy DummyService(" + Integer.toString(number) + ")" );
        }

        public void initDigester( Digester digester, String path ) {
                logHelper.log( "Init digester in DummyService(" + Integer.toString(number) + ") - doing nothing!" );
        }

        public void processTest( Object parameters ) {
                logHelper.log( "Call to the processTest in DummyServise(" + Integer.toString(number) + ")" );
        }

        public void processAnother( Object parameters ) {
                logHelper.log( "Call to the processAnother in DummyServise(" + Integer.toString(number) + ")" );
        }

}
