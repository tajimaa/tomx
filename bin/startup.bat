@echo off

REM �J�����g�f�B���N�g�����X�N���v�g�̃f�B���N�g���Ɉړ�
pushd %~dp0

REM TomServer�����s
java -cp "../lib;../lib/webserver" webserver.TomServer

REM �J�����g�f�B���N�g�������ɖ߂�
popd