@echo off

set JAR_FILE=GameTimeMonitor-0.2.jar

rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

javaw -jar %JAR_FILE% %CMD_LINE_ARGS%