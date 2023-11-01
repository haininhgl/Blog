FROM openjdk:20
COPY target/spring-boot-blog.jar spring-boot-blog.jar
ENTRYPOINT ["java", "-jar", "spring-boot-blog.jar"]
EXPOSE 8080