The Chain Of Responsibility Adapter README File
$Id: README.txt,v 1.1 2003/08/11 04:55:34 craigmcc Exp $


INTRODUCTION:
============

FIXME


CONTENTS OF THIS RELEASE:
========================


Top Level Directory:
-------------------

  LICENSE.txt       -- The Apache Software License, under which all software
                       included in this bundle is licensed.

  README.txt        -- This README file.

  build.xml         -- Ant build script (only required for building from
                       source).

  build.properties.sample
                    -- Sample "build.properties" file that may be customized
                       (only required for building from source).


Directory "docs":
----------------

  api/              -- Javadocs for the classes included in the Struts Chain
                       of Responsibility library (org.apache.struts.chain.*).


Directory "lib":
---------------

  struts-chain.jar  -- The compiled classes for the Chain Of Responsibility
                       adapter library.


Directory "src":
---------------

  conf/             -- Source files for the JAR file's manifest.

  example/          -- Source files for the demonstration web application.

  java/             -- Source files for the Chain of Responsibility library
                       classes (only required for building from source).
                       The base package is "org.apache.struts.chain".


Directory "web":
---------------

  example/          -- JSP and web application configuration files for the
                       demonstration web application.


Directory "webapps":
-------------------

  struts-chain.war  -- Executable version of the demonstration
                       web application.


RUNNING THE EXAMPLE APPLICATION:
===============================

The following steps are required to deploy and run the example application
(struts-chain.war) included in this distribution:


Install A Java Development Kit:
------------------------------

The Struts Chain Of Responsibility library requires a Java Development Kit (not
the Java Runtime Environment), version 1.3 or later.  It was tested against
Sun's JDK 1.4.2 release, available at:

    http://java.sun.com/j2se/


Install A Servlet/JSP Container:
-------------------------------

The Struts Chain Of Responsibility library requires a container that supports
Servlet 2.3 (or later) and JSP 1.2 (or later).  Any J2EE 1.3 (or later)
application server should also work.  You must acquire and install such
a container in order to execute the example application, following that
container's standard installation instructions.


Install A Struts 1.2 Nightly Release:
------------------------------------

The Struts Chain of Responsibility library requires a recent nightly build of
the head branch of the Struts CVS repository (i.e. the code being used for the
Struts 1.2 development track).

    http://jakarta.apache.org/site/binindex.cgi

You will want to become familiar with the operation of the standard example
web application (struts-example.war), if you are not already.


Deploy And Execute The Sample Application:
-----------------------------------------

Follow the standard procedures for your container to deploy a web application
that is packaged as a WAR file.  For example, you can deploy on Tomcat by
simply copying the struts-chain.war file into the "webapps"
subdirectory of your Tomcat installation, and restarting Tomcat.

To execute the sample application, access it with a web browser under URL to
which it was installed.  This will usually be something like:

    http://localhost:8080/struts-chain/


USING THE CHAIN OF RESPONSIBILITY LIBRARY IN YOUR OWN APPLICATIONS:
==================================================================

FIXME


BUILDING FROM SOURCE:
====================

If you wish, you can build the Struts Chain Of Responsibility library, and the
sample application, from the source code included in this distribution.
Follow these steps:


Install An Ant Distribution:
---------------------------

The provided build.xml script requires Ant, version 1.5.1 or later.  You can
get it from:

    http://ant.apache.org/

Install this environment as described in the Ant documentation, and ensure
that Ant's "bin" directory is on your PATH.


Configure Your Build Properties:
-------------------------------

Copy the "build.properties.sample" file in the top level directory to a file
named "build.properties", and customize the paths that are specified there.


Build The Sources:
-----------------

The simplest way to build is to execute:

    ant clean dist

to recreate the entire distribution in the "dist" subdirectory.  Use the
"ant -projecthelp" command to see what other targets are available.



KNOWN LIMITATIONS:
=================

FIXME
