FROM mysql/mysql-server
ENV MYSQL_DATABASE desafio
ENV DATABASE_HOST db
ADD /mysql/data.sql /docker-entrypoint-initdb.d/data.sql