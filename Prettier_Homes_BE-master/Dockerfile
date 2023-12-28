FROM maven:3.8.1-openjdk-11-slim AS build
RUN mkdir -p workspace
WORKDIR /workspace
COPY pom.xml /workspace/
COPY src /workspace/src
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspot
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8092
<<<<<<< HEAD:jenkins-dockerfile/Prettier_Homes_BE-master/Dockerfile
ENTRYPOINT ["java","-jar","app.jar"]
=======
ENTRYPOINT ["java","-jar","app.jar"]
>>>>>>> cbf66cc56b65f9891f9ebc307345d16552f2da0c:Prettier_Homes_BE-master/Dockerfile
