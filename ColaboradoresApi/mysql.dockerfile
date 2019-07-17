FROM mysql/mysql-server
ENV MYSQL_DATABASE desafio
ADD /mysql/data.sql /docker-entrypoint-initdb.d/data.sql