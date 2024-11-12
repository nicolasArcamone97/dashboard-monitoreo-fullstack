# Utilizar Eclipse Temurin como base (Java 17)
FROM eclipse-temurin:17-jre-alpine

# Puerto donde correrá la aplicación
EXPOSE 8080

# Definir el directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración y el wrapper de Maven primero (para aprovechar el caché)
COPY ./pom.xml ./mvnw ./
COPY .mvn .mvn

# Descargar dependencias sin construir el proyecto (mejor uso del caché)
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline

# Copiar el código fuente
COPY ./src ./src

COPY ./target ./target

# Construir el proyecto, generando el .jar
# RUN ./mvnw clean package -DskipTests

# Ejecutar la aplicación con optimizaciones de memoria
ENTRYPOINT ["java", "-jar", "target/dash_monitoreo-0.0.1.jar"]


