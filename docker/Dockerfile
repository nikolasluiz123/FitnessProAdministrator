# --- Estágio 1: Build ---
# Usa uma imagem oficial do Maven com o JDK 23 para compilar a aplicação
FROM maven:3.9-eclipse-temurin-23 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache de camadas do Docker
COPY pom.xml .

# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila a aplicação e gera o JAR executável
# O -DskipTests acelera o build por não rodar os testes
RUN mvn package -DskipTests


# --- Estágio 2: Imagem Final ---
# Usa uma imagem JRE (Java Runtime Environment) enxuta, apenas com o necessário para rodar
FROM eclipse-temurin:23-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# [cite_start]Define o Timezone e Locale, como no seu Dockerfile original [cite: 26]
ENV TZ=America/Sao_Paulo
ENV LANG=pt_BR.UTF-8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Copia o JAR executável gerado no estágio de build para a imagem final
COPY --from=build /app/target/administrator-*.jar app.jar

# Expõe a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para iniciar a aplicação
# As variáveis de ambiente (FITNESS_PRO_ADMINISTRATOR_ENV e JWT_TOKEN)
# serão passadas pelo seu script de deploy com a flag -e no comando `docker run`
CMD ["java", "-jar", "app.jar"]