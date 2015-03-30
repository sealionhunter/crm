rem @echo off

cd %~dp0

set SERVER="(local)\FXAW"
set DATABASE_WEBUI=CRM
set DATABASE_JETSPEED=
set DBUSER=crm
set DBPASSWORD=crm_password
set SA_USER=
set SA_PASSWORD=
set DBPATH="C:\Program Files (x86)\Microsoft SQL Server\MSSQL11.FXAW\MSSQL\DATA"

echo ********************* WEBUI Begin **************************
set VARIABLES=db_name=%DATABASE_WEBUI%
set VARIABLES=%VARIABLES% db_user=%DBUSER%
set VARIABLES=%VARIABLES% db_password=%DBPASSWORD%
if %DBPATH% neq "" set VARIABLES=%VARIABLES% db_path=%DBPATH%
setlocal ENABLEDELAYEDEXPANSION
for /F "eol=- delims=*" %%v in (setupValues.txt) do (
  set VARIABLES=!VARIABLES! %%v
)
echo %VARIABLES%>variables.tmp
endlocal
set /p result=<variables.tmp
if exist variables.tmp del variables.tmp
set VARIABLES=%result% %SETUP%

if "%SA_USER%" equ "" (
set CMD_PARAMS=/S %SERVER% /E /I /v %VARIABLES%
) else (
set CMD_PARAMS=/S %SERVER% /U %SA_USER% -P %SA_PASSWORD% /I /v %VARIABLES%
)

echo create db.
SQLCMD %CMD_PARAMS% /i createDatabase.sql> result.txt
if errorlevel 1 goto err
echo create table.
SQLCMD %CMD_PARAMS% /i CRMSystem.sql> result.txt

pause

echo ********************* WEBUI End **************************

:end
echo ********************* Success **************************
if exist result.txt del result.txt
exit 0

:err
echo ********************* Error **************************
if %ERRORLEVEL%==0 (
  echo This setup has been executed successfully.
  if exist result.txt del result.txt
) else (
  echo An error has occurred while executing this setup.
)

exit /b %ERRORLEVEL%
