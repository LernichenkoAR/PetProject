FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY japp.jar /opt/app
CMD ["java", "-jar", "/opt/app/japp.jar"]
EXPOSE  8080:8080
RUN java -jar warehouse-service-0.0.1-SNAPSHOT.jar
# docker run --name warehouse_db -p 5432:5432 -e POSTGRES_USER=wh_user -e POSTGRES_DB=warehouse_db -e POSTGRES_PASSWORD=54321 -d postgres

