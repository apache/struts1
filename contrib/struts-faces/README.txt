The Struts-Faces Integration Library (Version 0.3) README File
$Id: README.txt,v 1.1 2003/03/07 03:22:42 craigmcc Exp $


INTRODUCTION:
============

This package contains an add-on library that supports the use of
JavaServer Faces user interface technology in a Struts based web application,
in place of the Struts custom tag libraries.  As a proof of concept, it also
includes the canonical "struts-example" example web application, converted
to use JavaServer Faces tags, as well as tags from the JSP Standard Tag
Library (JSTL), version 1.0.

Note that this software is based on the Early Access 3 release of
JavaServer Faces technology, and is itself very new.  Therefore, it is
appropriate only for evaluation and learning, and not yet appropriate
for production application deployments.

EXAMPLE APPLICATION NOTE -- Because the JavaServer Faces reference
implementation is an early access release, the license under which it is
available does not allow redistribution.  See RUNNING THE EXAMPLE APPLICATION,
below, for information on how to integrate your own copy of the required
JAR files into the example app, which is required before it will run.



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


Directory "conf":
----------------

  struts-faces.tld  -- The JSP tag library descriptor file for the
                       Struts-Faces integration tag library.


Directory "docs":
----------------

  struts-faces.html -- Reference documentation for the Struts-Faces
                       integration tag library.

  api/              -- Javadocs for the classes included in the Struts-Faces
                       integration library (org.apache.struts.faces.*).


Directory "lib":
---------------

  struts-faces.jar  -- The compiled classes (and embedded tag library
                       descriptor) for the Struts-Faces integration library.


Directory "src":
---------------

  conf/             -- Source files for the generated TLD and tag library
                       documentation (only required for building from source).

  example/          -- Source files for the "struts-example" application that
                       has been converted to use JavaServer Faces components.
                       You can compare this to the corresponding sources in a
                       standard Struts release to see what had to be changed.

  java/             -- Source files for the Struts-Faces integration library
                       classes (only required for building from source).  The
                       following packages (under org.apache.struts.faces)
                       contain the necessary code:

                       application -- Integrate with ApplicationHandler,
                                      custom RequestProcessor

                       component   -- Custom JavaServer Faces component
                                      implementations (only Form for now)

                       renderer    -- Custom JavaServer Faces renderer
                                      implementations

                       taglib      -- Custom JavaServer Faces component tag
                                      implementations, and LifecycleListener
                                      that registers the custom stuff

                       util        -- Utility classes


Directory "web":
---------------

  example/          -- JSP and web application configuration files for the
                       "struts-example" application that has been converted
                       to use JavaServer Faces components.  You can compare
                       this to the corresponding sources in a standard Struts
                       release to see what had to be changed.


Directory "webapps":
-------------------

  struts-faces.war  -- The converted example application, minus the required
                       JAR files from the JavaServer Faces reference
                       implementation Early Access 3 release.  See RUNNING
                       THE EXAMPLE APPLIATION for information on how to
                       configure and deploy this web application on your
                       container.


RUNNING THE EXAMPLE APPLICATION:
===============================

The following steps are required to deploy and run the example application
(struts-faces.war) included in this distribution:


Install A Java Development Kit:
------------------------------

The Struts-Faces integration library requires a Java Development Kit (not
the Java Runtime Environment), version 1.3 or later.  It was tested against
Sun's JDK 1.4.1_01 release, available at:

    http://java.sun.com/j2se/


Install A Servlet/JSP Container:
-------------------------------

The Struts-Faces integration library requires a container that supports
Servlet 2.3 (or later) and JSP 1.2 (or later).  Any J2EE 1.3 (or later)
application server should also work.  You must acquire and install such
a container in order to execute the example application, following that
container's standard installation instructions.

The Struts-Faces Integration Library was tested on the following two
containers, which are freely available:

(1) Apache Tomcat (tested on version 4.1.18):

    http://jakarta.apache.org/site/binindex.cgi

(2) Java Web Services Developer Pack (tested on version 1.0_01):

    http://java.sun.com/webservices/webservicespack.html


Install The JavaServer Faces Reference Implementation Release:
-------------------------------------------------------------

Acquire the JavaServer Faces reference implementation EA3 release from:

    http://java.sun.com/j2ee/javaserverfaces/

and unpack it into a convenient directory.


Install A Struts 1.1 Release:
----------------------------

The Struts-Faces integration library requires a nightly build of Struts 1.1
dated 20030216 or later, or a Struts 1.1 (Release Candidate 2) or
Struts 1.1 (Final) release, when they are available.  It will not run
correctly with any previous version.  Get Struts from:

    http://jakarta.apache.org/site/binindex.cgi

You will want to become familiar with the operation of the standard example
web application (struts-example.war), if you are not already.


Install a JSTL 1.0 Release (Optional):
-------------------------------------

The JavaServer Faces reference implementation distribution includes the Apache
implementation of JSTL 1.0 (jstl.jar and standard.jar), and encourages you to
use JSTL tags in conjunction with JavaServer Faces tags in your JSP pages.  You
may also wish to download and install the complete JSTL release, in order to
gain access to JSTL documentation and examples:

    http://jakarta.apache.org/site/binindex.cgi

Select the "Taglibs" distribution, and navigate to the "standard" tag library's
subdirectory.


Add JavaServer Faces JAR Files To Example WAR:
---------------------------------------------

Because the JavaServer Faces release is still an Early Access version, the
license under which it is made available does not permit redistribution of
the required JAR files.  Therefore, you will need to download the JavaServer
Faces release yourself (as described above), and manually incorporate the
required JAR files into the struts-faces.war file, as follows:

* Use the "jar" command line utility from your JDK
  to unpack the "webapps/struts-faces.war" file
  into an empty directory.

    cd {directory in which you unpacked the struts-faces integration library}
    mkdir temp
    cd temp
    jar xvf ../webapps/struts-faces.war

* Copy the "jsf-api.jar", "jsf-ri.jar", and "jstl_el.jar" files from
  the "lib" directory of the JavaServer Faces reference
  implementation EA release into the "WEB-INF/lib" directory
  of your expanded web application.

* Use the "jar" command line utility from your JDK
  to repack the "webapps/struts-faces.war" file
  with the new JAR files included.

    cd {directory in which you unpacked the struts-faces integration library}
    cd temp
    jar cvf ../struts-faces.war *

These steps will provide you a new "struts-faces.war" web application that can
be deployed into your container.


Deploy And Execute The Sample Application:
-----------------------------------------

Follow the standard procedures for your container to deploy a web application
that is packaged as a WAR file.  For example, you can deploy on Tomcat by
simply copying the (customized) struts-faces.war file into the "webapps"
subdirectory of your Tomcat installation, and restarting Tomcat.

To execute the sample application, access it with a web browser under URL to
which it was installed.  This will usually be something like:

    http://localhost:8080/struts-faces/

Operation of the sample application should be identical to that of the
struts-example web application included in a standard Struts release.  The
only difference is the use of JavaServer Faces components (and JSTL tags),
rather than the use of Struts tag libraries, to create the user interface.


USING THE STRUTS-FACES LIBRARY IN YOUR OWN APPLICATIONS:
=======================================================

Using the Struts-Faces integration library in your own Struts-based web
applications is straightforward, and requires the following steps:

* Add the "struts-faces.jar" file from the "lib" subdirectory of this
  release into the "/WEB-INF/lib" subdirectory of your webapp.

* Add the following JAR files from the JavaServer Faces reference
  implementation's "lib" directory to your application's
  "/WEB-INF/lib" directory:  jsf-api.jar, jsf-ri.jar, jstl_el.jar.

* Add the following JAR files, containing the JSTL release (or
  from the JavaServer Faces release) to your application's
  "/WEB-INF/lib" directory:  jstl.jar, standard.jar.

* Add the servlet definition for the JavaServer Faces servlet into
  your web application's deployment descriptor (/WEB-INF/web.xml):

    <servlet>
      <servlet-name>faces</servlet-name>
      <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
    </servlet>

* If you have a <load-on-startup> element on your declaration of the
  Struts controller servlet, modify the value to be "2" or greater
  so that FacesServlet is initialized first.

* Add the servlet mapping for the JavaServer Faces servlet into
  your web application's deployment descriptor (/WEB-INF/web.xml):

    <servlet-mapping>
      <servlet-name>faces</servlet-name>
      <url-pattern>/faces/*</url-pattern>
    </servlet-mapping>

* The tag library in the Struts-Faces integration library (as well
  as those in the JavaServer Faces reference implementation) are
  embedded in the JAR files themselves, and rely on the ability of a
  Servlet 2.3 (or later) container to automatically recognize them.
  Therefore, there is no need to copy the TLD files into the WEB-INF
  subdirectory of your web application.

* Modify the JSP pages of your web application to use the JSTL,
  JavaServer Faces, and Struts-Faces integration library tags, instead
  of the traditional Struts tag libraries.  This migration can occur
  one page at a time, as you become familiar with the new technologies.
  You will want to note the following points in particular:

  - Include the following tag library directives at the top of your
    page in order to declare them:

    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
    <%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
    <%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
    <%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

  - The Struts-Faces tag library (prefix "s" above) contains replacements
    for functionality in the existing Struts HTML tag library that are
    not directly provided by JavaServer Faces components.  You should
    convert your existing use of the Struts HTML variants of these tags
    to use the Struts-Faces version instead.  (Functionality and attributes
    should be basically compatible, so this is usually just a matter of
    changing the tag prefixes.)

  - In particular, you must use the Struts-Faces version of the
    form tag (<s:form>) in order to activate standard Struts features
    like automatic creation of the form bean, and looking up the
    appropriate action to invoke based on the "action" attribute.

  - Replace the use of tags from the Struts HTML library with user interface
    component tags provided by the JavaServer Faces reference implementation,
    by other third party libraries, or by your application itself.  For
    example, on the logon.jsp page, the username field was changed from:

      <html:text property="username" size="16" maxlength="18"/>

    to the following JavaServer Faces Component tag:

      <h:input_text id="username" size="16"
        modelReference="logonForm.map.username"/>

  - You will note that the model reference expression is an EL expression
    that ties this input component to the "username" property of your
    logonForm bean.  In this particular case, "logonForm" is a
    DynaActionForm bean, so you must specify ".map." in the middle to
    access the property with an EL expression -- on a standard ActionForm
    bean, this would be replaced by a single period (".").

  - JavaServer Faces provides its own mechanisms for internationalizing
    user interfaces.  These can be used directly; however, to ease the
    transition for existing Struts-based web applications, the Struts-Faces
    integration library supports the <s:message/> tag, which is functionally
    equivalent to the previous <bean:message> tag.

  - Optionally, replace the use of tags from the Struts BEAN and LOGIC
    libraries with corresponding functionality from JSTL tags.  This is
    recommended, because JSTL tags are more powerful than their Struts
    library counterparts, and the expression language syntax is the same
    as that used for model reference expressions.

* For each JSP page that you have modified to use JavaServer Faces
  components instead of traditional Struts tags, modify any <forward>
  elements in your webapp's struts-config.xml file to include "/faces"
  in front of the path to that page.  For example, change:

    <forward name="registration" path="/registration.jsp"/>

  to this:

    <forward name="registration" path="/faces/registration.jsp"/>

* In most circumstances, you should not need to make any changes in
  your Actions, or the business logic classes invoked by your actions.
  They are still invoked as part of the standard Struts request
  processing lifecycle, and are still expected to return an
  ActionForward (or null) defining what view layer technology
  should be invoked next.

* If your application itself provides additional UIComponent and/or
  Renderer implementations, you must register them with the default
  JavaServer Faces RenderKit before they can be used.  The simplest
  way to do this is to define a ServletContextListener instance, and
  use the contextInitialized() method to perform the necessary
  registration.  (The Struts-Faces integration library declares this
  listener in its tag library descriptor file, which is in turn
  embedded in the struts-faces.jar file and is therefore automatically
  recognized -- no changes to web.xml are required).  For a simple
  example of how this can be done, see the sources for the example
  application (org.apache.struts.webapp.example.FacesPlugIn), which
  registers a custom Renderer provided by this application.

* A more complex example is the listener in the Struts-Faces integration
  library itself (org.apache.struts.faces.taglib.LifecycleListener),
  which loads information about the custom components and renderers
  to be registered from properties files embedded in the JAR file.
  This class also illustrates how the library integrates a custom
  ApplicationHandler implementation that hooks in to the standard
  Struts request processing lifecycle.

* Note that the ApplicationHandler mechanism in the EA3 release of
  JavaServer Faces is deprecated, because it is undergoing active
  modificaiton in the JSR-127 Expert Group.  The Struts-Faces integration
  library will be modified as needed to maintain the current level of
  transparency, but the actual mechanism will *not* be backwards
  compatible.


BUILDING FROM SOURCE:
====================

If you wish, you can build the Struts-Faces integration library, and the
sample application, from the source code included in this distribution.
Follow these steps:


Install An Ant Distribution:
---------------------------

The provided build.xml script requires Ant, version 1.5.1 or later.  You can
get it from:

    http://jakarta.apache.org/site/binindex.cgi

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

The following items identify functionality areas that have not yet been
fully implemented or tested:

* Use of the Tiles Framework.

* Use of the Struts-Faces integration library in multiple application modules.

* Use of the "forwardPattern" or "pagePattern" attributes on the
  <controller> element.

* Use of the Struts Nested tag libraries.

* Use of the Struts-EL tag library (although this should be unnecessary,
  since you are free to use JSTL tags directly).

* Use of a custom RequestProcessor subclass.  The Struts-Faces integration
  library provides its own custom subclass
  (org.apache.struts.faces.application.FacesRequestProcessor), which must
  be used (or subclassed) for the integration to operate successfuly.


