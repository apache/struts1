@ECHO OFF
SET SERVICE_MANAGER=..\..\..\dist\service-manager.jar
SET DUMMY_SERVICE=..\..\dummy-service\dist\dummy-service.jar
SET CONSOLE_SAMPLE=..\dist\console-sample.jar
SET DIGESTER=..\..\..\..\..\..\jakarta-commons\digester\dist\commons-digester.jar
SET COLLECTIONS=..\..\..\..\..\..\jakarta-commons\collections\dist\commons-collections.jar
SET BEANUTILS=..\..\..\..\..\..\jakarta-commons\beanutils\dist\commons-beanutils.jar
SET XALAN=..\..\..\..\..\..\jaxp-1.1\xalan.jar
SET STRUTS=..\..\..\..\..\dist\lib\struts.jar
SET CP=%SERVICE_MANAGER%;%DUMMY_SERVICE%;%CONSOLE_SAMPLE%;%DIGESTER%;%XALAN%;%STRUTS%;%COLLECTIONS%;%BEANUTILS%
ECHO CLASSPATH=%CP%
java -Dorg.apache.struts.service.ConfigLocation=..\conf\service-manager.xml -classpath %CP% org.apache.struts.service.samples.ConsoleBase