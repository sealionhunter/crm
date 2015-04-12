@echo off

cd %~dp0

setlocal ENABLEDELAYEDEXPANSION

rem Command-line arguments
rem -nopause
rem		Directs not to pause until the user presses any key when Setup.bat finishes.
rem -server (server name[\instance name])
rem		Specifies the instance of SQL Server to which to connect.
rem -sa_user (system account name)
rem		Specifies the user login ID that has "System Administrator" permission.
rem		If this option is not specified, Setup.bat tries to connect by using Microsoft Windows Authentication mode.
rem -sa_password (system account password)
rem		Specifies the user password.
rem -db_name (database name)
rem		Specifies the name of the new database.
rem -db_user (user name)
rem		Specifies the name of the user that is created.
rem -db_password (password)
rem		Specifies the password for the user that is being created.
rem -db_path (file path)
rem		Specifies the directory path used by the operating system when it creates the database files.
rem -collate (collate)
rem		Specifies the collation for the string type column.
rem -collate_id (collate)
rem		Specifies the collation for the entity id.
rem Example
rem		C:\>setup -nopause -server "(local)\SQL2008" -db_path "C:\Program Files\Microsoft SQL Server\MSSQL10.MSSQLSERVER\MSSQL\DATA"

rem Initializing variables
set SERVER="(local)\FXAW"
set NOPAUSE=0
set SA_USER=
set SA_PASSWORD=
set CMDLINEVARIABLES=

rem Processing command-line arguments
:START_CHECKARG
if "%~1" == "" (goto END_CHECKARG
) else if /I "%~1" equ "-nopause" (set NOPAUSE=1
) else if /I "%~1" equ "-server" ( if "%~2" neq "" (
set SERVER="%~2"
shift
)) else if /I "%~1" equ "-sa_user" ( if "%~2" neq "" (
set SA_USER="%~2"
shift
)) else if /I "%~1" equ "-sa_password" ( if "%~2" neq "" (
set SA_PASSWORD="%~2"
shift
)) else (
rem Processing earch argument
set TEMPARG=%~1
if "!TEMPARG:~0,1!" == "-" ( if "%~2" neq "" (
set CMDLINEVARIABLES=%CMDLINEVARIABLES% !TEMPARG:~1!=%2
shift
)))

shift
goto START_CHECKARG
:END_CHECKARG

rem Generating sqlcmd command-line
rem variable precedence (Low to High)
rem 1. File "setupValues.txt"
rem 2. System-level environmental variable "SETUP"
rem 3. Command-line arguments
set VARIABLES=

rem from file "setupValues.txt"
for /F "eol=- delims=*" %%v in (setupValues.txt) do (
	set VARIABLES=!VARIABLES! %%v
)

rem from system-level environmental variable "SETUP"
set VARIABLES=%VARIABLES% %SETUP%

rem from command-line arguments
set VARIABLES=%VARIABLES% %CMDLINEVARIABLES%

rem generating sqlcmd command-line and scripting variable
if "%SA_USER%" equ "" (
set CMD_PARAMS=/S %SERVER% /E /I /v %VARIABLES%
) else (
set CMD_PARAMS=/S %SERVER% /U %SA_USER% -P %SA_PASSWORD% /I /v %VARIABLES%
)

rem Evarating scripting variable "db_name"
sqlcmd %CMD_PARAMS% /b /h -1 /W /i getDatabaseName.sql > db_name.txt
for /F %%v in (db_name.txt) do (
	set DATABASE=%%v
	goto beak
)
:beak
del db_name.txt

if exist dropDatabase.sql (
	echo Deleting database...
	echo dropDatabase.sql
	sqlcmd %CMD_PARAMS% /i dropDatabase.sql
	echo.
)

echo Creating database...
echo createDatabase.sql
sqlcmd %CMD_PARAMS% /b /i createDatabase.sql
if errorlevel 1 goto end
echo.

echo create tables...
echo CRMSystem_Unicom.sql
sqlcmd %CMD_PARAMS% -d %DATABASE% /b /i CRMSystem_Unicom.sql -f 65001
if errorlevel 1 goto end

echo table created ok.


:end

echo.
if %ERRORLEVEL%==0 (
	echo build successed.
) else (
	echo build failed.
)

if %NOPAUSE%==1 (
	exit /b %ERRORLEVEL%
) else (
	echo.
	pause
)
