The Struts-Faces Integration Library (Version 0.4) README File
$Id: README.txt,v 1.9 2004/06/08 20:02:47 jholmes Exp $


============
INTRODUCTION:
============

This package contains an add-on library that supports the use of
JavaServer Faces user interface technology in a Struts based web application,
in place of the Struts custom tag libraries.  As a proof of concept, it also
includes the canonical "struts-example" example web application, converted
to use JavaServer Faces tags, as well as tags from the JSP Standard Tag
Library (JSTL), version 1.0 or later.  It also includes a very basic Tiles
based application, modified in a similar manner.

Note that this software is based on the Beta release of
JavaServer Faces technology, and is itself very new.  Therefore, it is
appropriate only for evaluation and learning, and not yet appropriate
for production application deployments.

EXAMPLE APPLICATION NOTE -- Because the JavaServer Faces reference
implementation is an early access release, the license under which it is
available does not allow redistribution.  See RUNNING THE EXAMPLE APPLICATION,
below, for information on how to integrate your own copy of the required
JAR files into the example app, which is required before it will run.


========================
NEW AND REVISED FEATURES:
========================


Struts-Faces Integration Library (Version 0.5):
----------------------------------------------

This release of the Struts-Faces Integration Library (Version 0.5) has the
following new features relative to the previous (0.4) release:

* It is now possible to mix pure JavaServer Faces pages, and those
  using the struts-faces integration library, in the same webapp.  Previously,
  it was required to use only Stuts-based handlers for form submts.

* All attributes of the component tags in the Struts-Faces integration library
  have been "value binding enabled", meaning you can use value binding
  expressions ("#{...}") to calculate attribute values dynamically.

* It is now possible to use the Struts-Faces Integration Library in conjunction
  with application modules using Tiles.

* You many now use a managed bean named "struts" at the beginning of any
  value binding expression in order to gain access to request, session, and
  application scope objects provided by Struts.  See the Javadocs for the
  implementation class (org.apache.struts.faces.util.StrutsContext)
  for more information about what objects are available.

* You may now use either prefix mapping (/faces/*) or extension mapping
  (*.faces) for the JavaServer Faces controller servlet.

* In addition to the <s:message> tag that operates as a direct replacement
  for <html:message>, you may also consider using the new <s:loadMessages>
  tag that exposes a MessageResources instance as a request attribute
  containing a Map.  This makes the messages included in the instance
  available via value binding expressions (or JSP 2.0 EL expressions).
  For example, the logon.jsp page of the example application includes:

    ...
    <s:loadMessages var="messages"/>
    ...
    <h:outputText value="#{messages['logon.header']}"/>
    ...

  to create the header text for the logon form.  You may either specify the
  application scope key for the MessageResources bunde you want, or omit
  the "messages" attribute to load the default MessageResources for the
  current application module.

* You can leverage advanced JavaServer Faces features in a Struts based
  web application.  For example, the converted "Mail Reader" example includes
  using the <h:dataTable> for multi-row input as well as output.

This release of the Struts-Faces Integration Library (Version 0.5) has the
following revised features relative to the previous (0.4) release:

* The "focus" attribute on the <s:form> tag should work properly
  in all cases now.

* Integration with the Validator Framework should work properly
  in all cases now.


Struts-Faces Integration Library (Version 0.4):
----------------------------------------------

The previous release of the Struts-Faces Integration Library (Version 0.4)
had the following new features relative to the previous (0.3) release:

* Value reference expressions in "valueRef" attributes of UI component tags
  can now recognize and support the properties of DynaBeans directly, thanks
  to the use of the pluggable PropertyResolver made available in
  JavaServer Faces 1.0ea4.  For backwards compatibility, the ".map"
  pseudo-property on DynaActionForm and its subclasses is still recognized.

The previous release of the Struts-Faces Integration Library (Version 0.4)
had the following revised features relative to the previous (0.3) release:

* In all UI tags for input and output components, the "modelReference"
  attribute has been replaced with "valueRef", for consistency with
  the corresponding change in the standard JavaServer Faces component tags.

* In all UI tags corresponding to JavaServer Faces components, the following
  changes have been made to reflect the corresponding changes in the
  underlying API classes:
  - Replaced javax.faces.webapp.FacesTag by javax.faces.webapp.UIComponentTag.
  - Replaced createComponent() method that returned a new component instance
    by getComponentType() method that returns a component type.

* The library utilizes the new (in EA4) capability to embed a configuration
  file in the struts-faces.jar file (META-INF/faces-config.xml) to
  automatically register the custom components and renderers included
  in the library.  Previously, these application elements needed to be
  registered programmatically in a ServletContextListener.

* Previously, the (deprecated) ApplicationHandler API was used to connect
  form submits to the Struts request processing lifecycle.  This has been
  replaced by plugging a custom implementation of the new ActionListener
  interface.

* The renderer implementation classes have been substantially simplified
  due to the removal of the requirement to provide information about
  supported render-dependent attributes.



========================
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

  faces-config.xml  -- Configuration file used by the struts-faces.jar file
                       to automatically register JavaServer Faces components
                       and renderers that are implemented in the library.  This
                       is for reference only; your application does not need
                       to do anything to include this file.

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
                       documentation (only required for building from source),
                       as well as the faces-config.xml file used to register
                       custom components and renderers.

  example/          -- Source files for the "struts-example" application that
                       has been converted to use JavaServer Faces components.
                       You can compare this to the corresponding sources in a
                       standard Struts release to see what had to be changed.

  example2/         -- Source files for a Tiles-based example application
                       that has been converted to use JavaServer Faces
                       components.

  java/             -- Source files for the Struts-Faces integration library
                       classes (only required for building from source).  The
                       following packages (under org.apache.struts.faces)
                       contain the necessary code:

                       application -- Integrate with ActionListener,
                                      custom PropertyResolver,
                                      custom RequestProcessors

                       component   -- Custom JavaServer Faces component
                                      implementations (only Form for now)

                       renderer    -- Custom JavaServer Faces renderer
                                      implementations

                       taglib      -- Custom JavaServer Faces component tag
                                      implementations

                       uti         -- Miscellaneous utility classes


Directory "web":
---------------

  example/          -- JSP and web application configuration files for the
                       "struts-example" application that has been converted
                       to use JavaServer Faces components.  You can compare
                       this to the corresponding sources in a standard Struts
                       release to see what had to be changed.

  example2/         -- JSP and web application configuration files for a
                       Tiles-based example application.


Directory "webapps":
-------------------

  struts-faces.war  -- The converted example application, minus the required
                       JAR files from the JavaServer Faces reference.  See
                       RUNNING THE EXAMPLE APPLIATION for information on how to
                       configure and deploy this web application on your
                       container.

  struts-faces2.war -- The Tiles-based example application, minus the required
                       JAR fies from the JavaSErver Faces reference
                       implementation.


================================
RUNNING THE EXAMPLE APPLICATIONS:
================================

The following steps are required to deploy and run the example application
(struts-faces.war) included in this distribution.  A similar process is
necessary to run the second (struts-faces2.war) example as well.


Install A Java Development Kit:
------------------------------

The Struts-Faces integration library requires a Java Development Kit (not
the Java Runtime Environment), version 1.3 or later.  It was tested against
Sun's JDK 1.4.2 release, available at:

    http://java.sun.com/j2se/


Install a Servlet Container:
---------------------------

The JavaServer Faces reference implementation final release has been tested
against Tomcat 5.0.16, but should run in any container that supports the
Servlet 2.3 and JSP 1.2 specifications.

If you attempt to run the example program in a servlet container that has a
version of JavaServer Faces installed already (such as the Java Web Services
Developer Pack, version 1.2 or 1.3), you must take one of the following two
actions before you can successfully use the new Struts-Faces Integration Library
and the corresponding beta release of JavaServer Faces.  Either:

* Replace any existing (EA4) version of JavaServer Faces in your container,
  following the container's specific instructions for this process (and leave
  the JavaServer Faces JARs out of your web application); or

* Remove any existing (EA4) version of JavaServer Faces in your container,
  following the container's specific instructions for this process (and plan
  on including the JavaServer Faces JARs with your webapp.  If you plan to
  build the example application from scratch, be sure to include the property
  setting "build.standalone=true" in your local "build.properties" file.


Install The Struts-Faces Integration Library 0.5 Distribution:
-------------------------------------------------------------

Download a nightly build of the Struts-Faces Integration Library (what will
eventually be version 0.5) from:

  http://jakarta.apache.org/builds/jakarta-struts/nightly/struts-faces/

Note that the source code for this distribution is included in the nightly
builds of the jakarta-struts repository, in directory:

  http://jakarta.apache.org/builds/jakarta-struts/nightly/src/


Add JavaServer Faces JAR Files To Example WAR:
---------------------------------------------

If you are planning to execute the sample application on a servlet container
containing a previous version of JavaServer Faces (such as EA4), be sure to
replace that version with the beta version, following the container provided
instructions.

If you are planning to execute the example application on a servlet container
that does not include JavaServer Faces (such as Tomcat 5.0.16 or later),
you will need to manually integrate the required JAR fles into the WAR file.
Yo can do this by executing the following steps from the command line, where
$JSF_HOME is the directory into which you have downloaded the final release
of JavaServer Faces 1.0, and $STRUTS_FACES_HOME is the directory into which
you have downloaded the Struts-Faces Integration Library binary release:

  mkdir temp
  cd temp
  jar xvf $STRUTS_FACES_HOME/webapps/struts-faces.war
  cp $JSF_HOME/lib/jsf-api.jar WEB-INF/lib
  cp $JSF_HOME/lib/jsf-impl.jar WEB-INF/lib
  jar cvf ../struts-faces.war *
  cd ..

After executing these steps, your current working directory will contain a
revised web application archive (WAR) file that has all the required libraries
to execute on any Servlet 2.4 / JSP 2.0 (or later) container.

    VERSION COMPATIBILITY NOTE:  The example application
    is distributed with the JSTL 1.1 JAR files (jstl.jar
    and standard.jar), and is therefore dependent upon
    JSP 2.0.  You can easily make a version of the webapp that
    runs on a Servlet 2.3 / JSP 1.2 container (such as
    Tomcat 4.1.27) by replacing these two JAR with the
    corresponding JARs from a JSTL 1.0 release.


Deploy The Example Application:
------------------------------

Follow the standard instructions for your container to deploy the
struts-faces.war web application archive.  For the JWSDP 1.2 or 1.3 release,
or for any standard Tomcat 5.0.x release, you have the following choices:

* Drop the "struts-faces.war" file into the "webapps" subdirectory
  of the servlet container release, and wait a few moments for the container
  to recognize the new application and deploy it for you.

* Use the dynamic deployment Ant commands, as described below.


Execute The Example Application:
-------------------------------

By default, the application will be installed at context path "/struts-faces",
so the URL to access it will typically be something like:

  http://localhost:8080/struts-faces/

The example application is functionally identical to the canonical
struts-example.war application included in standard Struts 1.1 releases.
When first started, the only valid username/password combination is
"user" and "pass".


====================
BUILDING FROM SOURCE:
====================

If you wish, you can build the Struts-Faces integration library, and the
sample application, from the source code included in this distribution.
Follow these steps:


Install An Ant Distribution:
---------------------------

If you have downloaded the JWSDP 1.2 or 1.3 release described above, Ant is
already included.  Otherwise, download Apache Ant, version 1.5.2 or later, from:

    http://jakarta.apache.org/site/binindex.cgi

Install this environment as described in the Ant documentation, and ensure
that Ant's "bin" directory is on your PATH.


Configure Your Build Properties:
-------------------------------

Copy the "build.properties.sample" file in the top level directory to a file
named "build.properties", and customize the settings that are specified there.
The default values are set up for easy use with the JWSDP 1.2ea download.


Build The Sources:
-----------------

The simplest way to build is to execute:

    ant clean dist

to recreate the entire distribution in the "dist" subdirectory.  Use the
"ant -projecthelp" command to see what other targets are available.


=======================================================
USING THE STRUTS-FACES LIBRARY IN YOUR OWN APPLICATIONS:
=======================================================

Using the Struts-Faces integration library in your own Struts-based web
applications is straightforward, and requires the following steps:

* Add the "struts-faces.jar" file from the "lib" subdirectory of this
  release into the "/WEB-INF/lib" subdirectory of your webapp.

* Add the following JAR files from the JavaServer Faces reference
  implementation's "lib" directory to your application's
  "/WEB-INF/lib" directory:  jsf-api.jar, jsf-impl.jar.

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
      <url-pattern>*.faces</url-pattern>
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

      <h:inputText id="username" size="16" maxlength="18"
              value="#{logonForm.username}"/>

  - JavaServer Faces provides its own mechanisms for internationalizing
    user interfaces.  These can be used directly; however, to ease the
    transition for existing Struts-based web applications, the Struts-Faces
    integration library supports the <s:message/> tag, which is functionally
    equivalent to the previous <bean:message> tag.

  - Optionally, replace the use of tags from the Struts BEAN and LOGIC
    libraries with corresponding functionality from JSTL tags.  This is
    recommended, because JSTL tags are more powerful than their Struts
    library counterparts, and the expression language syntax is the same
    as that used for value reference expressions.

* Modify your struts-config.xml file to include identification of the custom
  request processor implementation class to be used, by adding the following
  element in the appropriate location (typically just before any existing
  <message-resources> and <plug-in> elements), one of the following
  controller element declarations.

  If your application module does *not* use Tiles:

    <controller>
      <set-property property="processorClass"
       value="org.apache.struts.faces.application.FacesRequestProcessor"/>
    </controller>

  If your application module *does* use Tiles:

    <controller>
      <set-property property="processorClass"
       value="org.apache.struts.faces.application.FacesTilesRequestProcessor"/>
    </controller>


* For each JSP page that you have modified to use JavaServer Faces
  components instead of traditional Struts tags, modify any <forward>
  elements in your webapp's struts-config.xml file to include "/faces"
  in front of the path to that page (if you are using prefix mapping),
  or a ".faces" extension (if you are using extenaion mapping.  For
  example, change:

    <forward name="registration" path="/registration.jsp"/>

  to this:

    <forward name="registration" path="/registration.faces"/>

* In most circumstances, you should not need to make any changes in
  your Actions, or the business logic classes invoked by your actions.
  They are still invoked as part of the standard Struts request
  processing lifecycle, and are still expected to return an
  ActionForward (or null) defining what view layer technology
  should be invoked next.

    NOTE:  If you have a command component whose "immediate" property
    is set to "true", it will be processed as it would in a pure
    JavaServer Faces based application.  Only command components with
    immediate="false" (which is the default value), that are nested
    inside a Struts-Faces <s:form> tag, will be forwarded through the
    normal Struts request processing lifecycle.

* If your application contains cancel buttons rendered by the <html:cancel>
  tag, you should replace them with an <h:commandButton> that has an
  "id" attribute set to "cancel" in order for this button to be recognized
  by Struts as a cancel button.

* If your application itself provides additional UIComponent and/or
  Renderer implementations, you must register them with the default
  JavaServer Faces RenderKit before they can be used.  The simplest
  way to do this is to define a "faces-config.xml" file that contains
  the declaration for your custom classes.  Such a file can be included
  either in the "/WEB-INF" directory of your web application, or in the
  "META-INF" directory of a JAR file included in "/WEB-INF/lib".
  (The Struts-Faces Integration Library itself uses the latter technique
  to register its custom components automatically for any web application
  that includes "struts-faces.jar" in its "/WEB-INF/lib" directory.)


=================
KNOWN LIMITATIONS:
=================

The following items identify functionality areas that have not yet been
fully implemented or tested:

* Use of the Struts-Faces integration library in multiple application modules
  (although in theory this should "just work").

* Use of the "forwardPattern" or "pagePattern" attributes on the
  <controller> element.

* Use of the Struts Nested tag libraries.

* Use of the Struts-EL tag library (although this should be unnecessary,
  since you are free to use JSTL tags directly).

* Use of a custom RequestProcessor subclass.  The Struts-Faces integration
  library provides its own custom subclasses
  (org.apache.struts.faces.application.FacesRequestProcessor or
  org.apache.struts.faces.application.FacesTilesRequestProcessor), which must
  be used (or subclassed) for the integration to operate successfuly.
