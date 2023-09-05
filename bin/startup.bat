@echo off

REM カレントディレクトリをスクリプトのディレクトリに移動
pushd %~dp0

REM TomServerを実行
java -cp "../lib;../lib/webserver" webserver.TomServer

REM カレントディレクトリを元に戻す
popd