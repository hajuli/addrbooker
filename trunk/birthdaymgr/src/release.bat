copy /V /Y /B TrayIcon12.dll %SystemRoot%\SYSTEM\
copy /V /Y /B JIntellitype.dll %SystemRoot%\SYSTEM\
jar cvfm birthdaymgr%date:~0,4%%date:~5,2%%date:~8,2%.jar manifest.mf hoi
pause
