FROM maven:3.8.5-eclipse-temurin-17

WORKDIR /usr/src/app

COPY /demo ./demo
COPY /order ./order

WORKDIR /usr/src/app/demo
RUN mvn clean install -Dmaven.test.skip=true

WORKDIR /usr/src/app/order
RUN mvn clean install -Dmaven.test.skip=true

WORKDIR /usr/src/app/order/target

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "Order.jar"]
