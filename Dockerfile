FROM openjdk:8-jre-alpine
WORKDIR /usr/app
COPY ./target/achat-1.0.jar .
CMD [ "java","-c","/usr/app/achat-1.0.jar" ]