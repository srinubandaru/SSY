set projectLocation=E:\OxygenW2\SSY
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\CommonJarFiles\*
java org.testng.TestNG %projectLocation%\testng.xml
pause