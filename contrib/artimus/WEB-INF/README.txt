Quick-Start Configuration notes for Tomcat 4.0
==============================================

+ Update your $TOMCAT_HOME/conf/tomcat-users.xml to include 

  <user name="artimus" password="g0rd0n" roles="manager,contributor" />
  
+ Place WAR in webapps folder (usually $TOMCAT_HOME/webapps) and let it deploy

+ The connection pool requires 3 standard JARs to be available: jdbc2_0-std-ext.jar, jta.jar, and xerces.jar. If these are not on your classpath or in the $TOMCAT_HOME/common/lib folder (the latter being recommended), you can 

- - Copy them from the WAR's WEB-INF/std-ext directory to your $TOMCAT_HOME/common/lib directory. 

- - Remove jaxp.jar from your $TOMCAT_HOME/lib directory (so Tomcat uses Xerces instead). 

+ Review WEB-INF/classes/poolman.xml for any changes needed for your setup, like your JDBC driver. Copy this to WEB-INF/src/conf so it survives a clean build.

+ Create a /var/lucene/artimus folder

- - Under windows, create \var\lucene\artimus on your container's drive.

+ Optionally, create a folder /var/applogs for the connection pool log.

+ Create a database named artimus in your DBMS package (MySQL, Oracle, Hypersonic, whatever).

+ Be sure the approprite JDBC driver is available to the container or application (and specified in the poolman.xml)

+ Restart Tomcat so the changes take affect. 

+ Login in (artimus g0rd0n), and create the tables from the menu.


Quick-Start FAQ
===============

What JARs do I need

- See /WEB-INF/lib/index.html

Where's the Struts Config?

- Under WEB-INF/conf

Where's the tablibs tlds?

- Under WEB-INF/lib

What changes do I have to make to build.xml?

- Usually none. The default target will build the entire project, including the Javadocs.

Where's the Application Resources?

- It's named Resources.properties. The original is under WEB-INF/src/conf. It's copied under classes during a build.

Why did the changes to my Resources.properties or poolman.xml disappear?

- The original configuration files are under WEB-INF/src/conf and copied under classes during a build. Change the WEB-INF/src/conf versions.

Can I use this stuff in my own application?

- Pursuant to the Apache Software License (see LICENSE file), yes.
