
To build and run the Cactus tests:

	1.	Create a file called build.properties (or reuse the one you have) 
		in the top level of Struts Taglibs.
	
	2.	Add a line (as shown below) that points to your local install of
		any supported server.
		
		See http://jakarta.apache.org/cactus/integration/maven/ for more
		details.
	
	build.properties
	----------------
	cactus.home.tomcat5x = D:/home/jmitchell/apache_home/jakarta-tomcat-5.0.28


	3.	Execute the following:  (this assumes you have Maven installed)
	
	$ maven cactus:test
