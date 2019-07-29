FROM java:8
EXPOSE 8080

ADD /target/starWars-1.0-SNAPSHOT.jar starWars.jar
ENTRYPOINT ["java","-jar","starWars.jar"]