FROM openjdk:8-jre-alpine
EXPOSE 65000
# copy WAR into image
COPY target/paragraphanalytics-0.0.1-SNAPSHOT.jar /app.jar 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]