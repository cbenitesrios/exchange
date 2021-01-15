FROM openjdk:8
ADD target/exchange.jar exchange.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "exchange.jar"]