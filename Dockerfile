FROM openjdk:11
WORKDIR /app
COPY target/data-warehouse-0.0.1-SNAPSHOT.jar data-warehouse-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "data-warehouse-0.0.1-SNAPSHOT.jar"]
