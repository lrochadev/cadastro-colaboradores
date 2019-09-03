FROM openjdk:12

EXPOSE 8080

ADD target/colaboradores-api.jar colaboradores-api.jar

ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "-jar", "colaboradores-api.jar"]