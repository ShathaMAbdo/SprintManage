FROM java:8
ADD /target/docker-spring-mongo.jar docker-spring-mongo.jar
EXPOSE 5000
ENTRYPOINT  ["java", "-jar","docker-spring-mongo.jar"]