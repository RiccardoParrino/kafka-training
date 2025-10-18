FROM maven:3.8.5-eclipse-temurin-17

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install -Dmaven.test.skip=true

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "Inventory.jar"]
