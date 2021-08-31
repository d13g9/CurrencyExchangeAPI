Spring Currency Exchange API.

To start the application run the following commands where the POM file is located:`

```
mvn install
mvn spring-boot:run´
```

To insert the application into a container run the fowllowing command where the POM file is located:

```
./mvnw package && java -jar target/currencyexchange.jar

```

Create the Docker file as follows on the root directory of the application:

```
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} currencyexchange.jar
ENTRYPOINT ["java","-jar","/currencyexchange.jar"]
```

To create the image run the following where the docker file is located:

```
docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/currencyexchange-spring-boot-docker.
```

To run the container:

```
docker run springio/currencyexchange-spring-boot-docker
```

