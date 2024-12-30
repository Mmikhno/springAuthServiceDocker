FROM openjdk:21-jdk
LABEL maintainer=mmikhno
EXPOSE 8080
ADD build/libs/authorizationService-0.0.1-SNAPSHOT.jar ./myapp/authapp.jar
CMD ["java","-jar","myapp/authapp.jar"]
