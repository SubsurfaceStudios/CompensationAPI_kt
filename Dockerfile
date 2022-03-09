FROM adoptopenjdk/openjdk16:ubi
RUN mkdir /opt/app
COPY build/libs/*.jar /opt/app
CMD ["java", "-jar", "/opt/app/CVRAPI-1.0-SNAPSHOT.jar"]