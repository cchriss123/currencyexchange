FROM maven:3.9.1-eclipse-temurin-20 as build

COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:20-jre

COPY --from=build /app/provider/target/*.jar /app/lib/provider.jar
COPY --from=build /app/consumer/target/*.jar /app/lib/consumer.jar
COPY --from=build /app/provider/target/alternateLocation/*.jar /app/lib/

ENTRYPOINT java --module-path /app:app/lib:app/lib/consumer.jar -m org.example.consumer/org.example.consumer.Consumer
