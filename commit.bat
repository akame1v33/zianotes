@echo off


cd E:\
cd \springmvcnotes

echo CURRENT LOCATION %cd%

set GIT_PATH="C:\Users\kevin.fabian\AppData\Local\Programs\Git\bin\git.exe"

:P
set FUCK= 
set /P FUCK=Action: %=%

if "%FUCK%"=="commit" (
    %GIT_PATH% add .
	%GIT_PATH% commit -m "Auto-committed on %date%"
	%GIT_PATH% push origin master
)
if "%FUCK%"=="exit" exit /b
goto P