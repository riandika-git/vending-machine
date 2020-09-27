title demo
cd cfg

@ECHO OFF

SET BIN_CMD=java com.example.demo.DemoApplication
SET CLASSPATH=../bin;.;
SET LIB_PATH=../lib
SET BIN_PATH=../bin
set PATH=%PATH%;%BIN_PATH%;.;

FOR %%i IN ("%LIB_PATH%/*.jar") DO CALL ../cpappend.bat %LIB_PATH%/%%i;
FOR %%i IN ("%BIN_PATH%/*.jar") DO CALL ../cpappend.bat %BIN_PATH%/%%i;
@echo CLASSPATH=%CLASSPATH%

@ECHO ON	
%BIN_CMD%