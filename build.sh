#! /bin/sh
# -----------------------------------------------------------------------------
# build.sh -- Build Script for the "Struts" Toolkit
#
# Supported Environment Variables (default values in square brackets):
#
#   ANT_HOME              Distribution directory for "jakarta-ant".
#                         [../jakarta-ant]
#
#   ANT_OPTS              Command line options to pass to the JVM when
#                         executing Ant. [none]
#
#   CATALINA_HOME         Build directory for "jakarta-tomcat-4.0".
#                         [../build/tomcat-4.0]
#
#   JAVA_HOME             Java Development Kit directory. [REQUIRED]
#
#   SERVLETAPI_HOME       Distribution directory for "jakarta-servletapi".
#                         [../jakarta-servletapi]
#
#   TOMCAT_HOME           Build directory for "jakarta-tomcat".
#                         [../build/tomcat]
#
# $Id: build.sh,v 1.5 2001/02/02 02:26:05 craigmcc Exp $
# -----------------------------------------------------------------------------


# ----- Verify and Set Required Environment Variables -------------------------

if [ "$ANT_HOME" = "" ] ; then
  ANT_HOME=../jakarta-ant
fi

if [ "$ANT_OPTS" = "" ] ; then
  ANT_OPTS=""
fi

if [ "$CATALINA_HOME" = "" ] ; then
  CATALINA_HOME=../jakarta-tomcat-4.0/build
fi

if [ "$JAVA_HOME" = "" ] ; then
  echo You must set JAVA_HOME to point at your Java Development Kit directory
  exit 1
fi

if [ "$SERVLETAPI_HOME" = "" ] ; then
  SERVLETAPI_HOME=../jakarta-servletapi
fi

if [ "$TOMCAT_HOME" = "" ] ; then
  TOMCAT_HOME=../build/tomcat
fi


# ----- Set Up The Runtime Classpath ------------------------------------------

if [ "$CLASSPATH" = "" ] ; then
  CP=$ANT_HOME/lib/ant.jar:$ANT_HOME/lib/optional.jar:$SERVLETAPI_HOME/lib/servlet.jar:$JAVA_HOME/lib/tools.jar
else
  CP=$ANT_HOME/lib/ant.jar:$ANT_HOME/lib/optional.jar:$SERVLETAPI_HOME/lib/servlet.jar:$JAVA_HOME/lib/tools.jar:$CLASSPATH
fi


# ----- Execute The Requested Build -------------------------------------------

java $ANT_OPTS -classpath "$CP" org.apache.tools.ant.Main -Dant.home=$ANT_HOME -Dcatalina.home=$CATALINA_HOME -Dservlet.jar=$SERVLETAPI_HOME/lib/servlet.jar -Dtomcat.home=$TOMCAT_HOME "$@"
