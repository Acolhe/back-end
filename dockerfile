## Use uma imagem base do OpenJDK para Java 11
#FROM openjdk:11-jre-slim
#
## Define o diretório de trabalho dentro do contêiner
#WORKDIR /app
#
## Copie todos os arquivos do diretório 'target' para o diretório de trabalho no contêiner
#COPY --from=Build /target/colhe-api-0.0.1-SNAPSHOT.jar app.jar
#
## Expõe a porta em que a aplicação Spring Boot irá ouvir (ajuste de acordo com a sua aplicação)
#EXPOSE 8080
#
## Comando para iniciar a aplicação Spring Boot quando o contêiner for iniciado
#CMD ["java", "-jar", "colhe-api-0.0.1-SNAPSHOT.jar"]

# Build Stage
FROM maven:3.8.3-openjdk-17 as Build
COPY . .
RUN mvn clean package -DskipTests

# Package Stage

FROM openjdk:17-jdk-slim

COPY --from=Build /target/colhe-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]