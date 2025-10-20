FROM maven:3.8.5-eclipse-temurin-17

WORKDIR /usr/src/app

COPY ./demo ./demo
COPY ./inventory ./inventory

WORKDIR /usr/src/app/demo
RUN mvn clean install -Dmaven.test.skip=true

WORKDIR /usr/src/app/inventory
RUN mvn clean install -Dmaven.test.skip=true

WORKDIR /usr/src/app/inventory/target

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "Inventory.jar"]
