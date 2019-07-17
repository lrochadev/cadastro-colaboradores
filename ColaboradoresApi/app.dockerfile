FROM java:8
EXPOSE 8080

ADD target/colaboradores-api.jar colaboradores-api.jar

ENTRYPOINT ["java","-jar","colaboradores-api.jar"]