@echo off


cd E:\
cd \springmvcnotes

echo CURRENT LOCATION %cd%

set GIT_PATH="C:\Users\kevin.fabian\AppData\Local\Programs\Git\bin\git.exe"
%GIT_PATH% add .
%GIT_PATH% commit -m "Auto-committed on %date%"
%GIT_PATH% push origin master
exit /b
