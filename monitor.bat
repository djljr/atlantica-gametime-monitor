@echo off

set CLASSPATH=%CLASSPATH%;myjar.jar
set CLASS=org.erenda.atlantica.gametime.GameTimeMonitor

rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

javaw -classpath "%CLASSPATH%" %CLASS% %CMD_LINE_ARGS%