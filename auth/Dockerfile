# --------- Fase de build -----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia todo el proyecto (si tu proyecto está en subcarpeta 'auth', el Dockerfile debe vivir en 'auth')
COPY . .

# Asegura permisos del wrapper (necesario en Linux)
RUN chmod +x mvnw

# Compila (sin tests) y genera el JAR
RUN ./mvnw -DskipTests clean package

# --------- Fase de runtime -----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/*.jar app.jar

# Render inyecta el puerto en $PORT
ENV PORT=8080
EXPOSE 8080

# Arranque
CMD ["java", "-Dserver.port=${PORT}", "-jar", "/app/app.jar"]
