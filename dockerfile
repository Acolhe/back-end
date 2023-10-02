# Use uma imagem base do OpenJDK para Java 11
FROM openjdk:11-jre-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie todos os arquivos do diretório 'target' para o diretório de trabalho no contêiner
COPY target/ /app/

# Expõe a porta em que a aplicação Spring Boot irá ouvir (ajuste de acordo com a sua aplicação)
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot quando o contêiner for iniciado
CMD ["java", "-jar", "colhe-api-0.0.1-SNAPSHOT.jar"]
