@echo off
SET MYSQL_USER=root
SET MYSQL_PASS=banquinPessoaL287@$

mysql -u %MYSQL_USER% -p%MYSQL_PASS% -e "CREATE DATABASE IF NOT EXISTS login_credentials_rh; USE login_credentials_rh; CREATE TABLE IF NOT EXISTS users (   username VARCHAR(50) NOT NULL,  password_hash VARCHAR(64) NOT NULL); select * from users;"

echo Banco e Tabela provisionados.
pause
