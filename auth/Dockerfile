# syntax=docker/dockerfile:1

########## STAGE 1: BUILD (con Maven + JDK 17) ##########
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copiamos wrapper y POM para cachear dependencias
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# Copiamos el código y construimos
COPY src ./src
RUN ./mvnw -q -DskipTests clean package

########## STAGE 2: RUNTIME (JRE 17, imagen liviana) ##########
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Opciones de memoria y puerto (Render setea PORT)
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Dserver.port=${PORT:-8080}"
# Perfil por defecto en producción
ENV SPRING_PROFILES_ACTIVE=prod

# Copiamos el jar
COPY --from=build /workspace/target/*.jar app.jar

# Para desarrollo local; Render ignora EXPOSE y usa PORT
EXPOSE 8080

# Arranque
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
