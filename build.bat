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
rem   JAVA_HOME             Java Development Kit directory. [REQUIRED]
rem
rem   SERVLETAPI_HOME       Distribution directory for "jakarta-servletapi".
rem                         [../jakarta-servletapi]
rem
rem   TOMCAT_HOME           Distribution directory for "jakarta-tomcat".
rem                         [../jakarta-tomcat]
rem
rem $Id: build.bat,v 1.5 2000/06/24 03:16:09 craigmcc Exp $
rem ---------------------------------------------------------------------------


rem ----- Save Environment Variables That May Change --------------------------

set _ANT_HOME=%ANT_HOME%
set _ANT_OPTS=%ANT_OPTS%
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


if not "%SERVLETAPI_HOME%" == "" goto gotServletapiHome
set SERVLETAPI_HOME=..\jakarta-servletapi
:gotServletapiHome

if not "%TOMCAT_HOME%" == "" goto gotTomcatHome
set TOMCAT_HOME=..\jakarta-tomcat
:gotTomcatHome



rem ----- Set Up The Runtime Classpath ----------------------------------------

if "%CLASSPATH%" == "" goto noClasspath
set CP=%ANT_HOME%\lib\ant.jar;%JAVA_HOME%\lib\tools.jar;%CLASSPATH%
goto gotClasspath
:noClasspath
set CP=%ANT_HOME%\lib\ant.jar;%JAVA_HOME%\lib\tools.jar
:gotClasspath


rem ----- Execute The Requested Build -----------------------------------------

java %ANT_OPTS% -classpath "%CP%" org.apache.tools.ant.Main -Dant.home=%ANT_HOME% -Dservlet.jar=%SERVLETAPI_HOME%\lib\servlet.jar -Dtomcat.home=%TOMCAT_HOME% %1 %2 %3 %4 %5 %6 %7 %8 %9


rem ----- Clean Up Environment Variables --------------------------------------

:cleanup

set ANT_HOME=%_ANT_HOME%
set ANT_OPTS=%_ANT_OPTS%
set SERVLETAPI_HOME=%_SERVLETAPI_HOME%
set TOMCAT_HOME=%_TOMCAT_HOME%

set _ANT_HOME=
set _ANT_OPTS=
set _SERVLETAPI_HOME=
set _TOMCAT_HOME=

