@echo off

set SERVER_URL=http://localhost:8001/shutdown
set COMMAND=shutdown

curl -X POST %SERVER_URL% -d "%COMMAND%" >nul