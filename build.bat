@echo off
rem ---------------------------------------------------------------------------
rem build.bat -- Build Script for the "Struts" Toolkit
rem
rem Supported Environment Variables (default values in square brackets):
rem
rem   ANT_HOME              Distribution directory for "jakarta-ant".
rem                         [../jakarta-ant]
rem
rem   ANT_OPTS              Command line options to pass to the JVM when
rem                         executing Ant. [none]
rem
rem   CATALINA_HOME         Build directory for "jakarta-tomcat-4.0".
rem                         [../build/tomcat-4.0]
rem
rem   JAVA_HOME             Java Development Kit directory. [REQUIRED]
rem
rem   SERVLETAPI_HOME       Distribution directory for "jakarta-servletapi".
rem                         [../jakarta-servletapi]
rem
rem   TOMCAT_HOME           Build directory for "jakarta-tomcat".
rem                         [../build/tomcat]
rem
rem $Id: build.bat,v 1.9 2000/11/24 14:53:58 pierred Exp $
rem ---------------------------------------------------------------------------


rem ----- Save Environment Variables That May Change --------------------------

set _ANT_HOME=%ANT_HOME%
set _ANT_OPTS=%ANT_OPTS%
set _CATALINA_HOME=%CATALINA_HOME%
set _SERVLETAPI_HOME=%SERVLETAPI_HOME%
set _TOMCAT_HOME=%TOMCAT_HOME%


rem ----- Verify and Set Required Environment Variables -----------------------

if not "%JAVA_HOME%" == "" goto gotJavaHome
echo You must set JAVA_HOME to point at your Java Development Kit distribution
goto cleanup
:gotJavaHome

if not "%ANT_HOME%" == "" goto gotAntHome
set ANT_HOME=..\jakarta-ant
:gotAntHome

if not "%CATALINA_HOME%" == "" goto gotCatalinaHome
set CATALINA_HOME=..\build\tomcat-4.0
:gotCatalinaHome

if not "%SERVLETAPI_HOME%" == "" goto gotServletapiHome
set SERVLETAPI_HOME=..\jakarta-servletapi
:gotServletapiHome

if not "%TOMCAT_HOME%" == "" goto gotTomcatHome
set TOMCAT_HOME=..\build\tomcat
:gotTomcatHome



rem ----- Set Up The Runtime Classpath ----------------------------------------

if "%CLASSPATH%" == "" goto noClasspath
set CP=%ANT_HOME%\lib\ant.jar;%ANT_HOME%\lib\optional.jar;%JAVA_HOME%\lib\tools.jar;%CLASSPATH%
goto gotClasspath
:noClasspath
set CP=%ANT_HOME%\lib\ant.jar;%ANT_HOME%\lib\optional.jar;%JAVA_HOME%\lib\tools.jar
:gotClasspath


rem ----- Execute The Requested Build -----------------------------------------

%JAVA_HOME%\bin\java %ANT_OPTS% -classpath "%CP%" org.apache.tools.ant.Main -Dant.home=%ANT_HOME% -Dcatalina.home=%CATALINA_HOME% -Dservlet.jar=%SERVLETAPI_HOME%\lib\servlet.jar -Dtomcat.home=%TOMCAT_HOME% %1 %2 %3 %4 %5 %6 %7 %8 %9


rem ----- Clean Up Environment Variables --------------------------------------

:cleanup

set ANT_HOME=%_ANT_HOME%
set ANT_OPTS=%_ANT_OPTS%
set CATALINA_HOME=%_CATALINA_HOME%
set SERVLETAPI_HOME=%_SERVLETAPI_HOME%
set TOMCAT_HOME=%_TOMCAT_HOME%

set _ANT_HOME=
set _ANT_OPTS=
set _CATALINA_HOME=
set _SERVLETAPI_HOME=
set _TOMCAT_HOME=

