FROM openjdk:21
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/cenomi-wakanda.jar
EXPOSE 5555
ENTRYPOINT ["java","-jar","/app/cenomi-wakanda.jar"]