FROM maven:3.9.1-eclipse-temurin-20

COPY . /app
WORKDIR /app

RUN mvn clean package
FROM eclipse-temurin-20
COPY --from=build /app/consumer/target/*.jar /app/consumer.jar
COPY --from=build /app/provider/target/*.jar /app/lib/provider.jar
COPY --from=build /app/service/target/*.jar /app/lib/service.jar


ENTRYPOINT ["java", "--module-path", "/app:/app/lib/service.jar:/app/lib/converter.jar", "-m", "org.example.consumer/org.example.consumer.Consumer"]
