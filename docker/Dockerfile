# --- Estágio 1: Build ---
FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

# Copia o pom.xml para aproveitar o cache
COPY pom.xml .

# Acessa o secret para autenticar no GitHub Packages
# Este passo cria o settings.xml DENTRO do container
RUN --mount=type=secret,id=PACKAGES_TOKEN \
    mkdir -p /root/.m2 && \
    echo "<settings><servers><server><id>github</id><username>${{ github.actor }}</username><password>$(cat /run/secrets/PACKAGES_TOKEN)</password></server></servers></settings>" > /root/.m2/settings.xml

# Baixa as dependências do projeto (agora autenticado)
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila e gera o JAR
RUN mvn package -DskipTests


# --- Estágio 2: Imagem Final ---
FROM eclipse-temurin:23-jre

WORKDIR /app

ENV TZ=America/Sao_Paulo
ENV LANG=pt_BR.UTF-8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY --from=build /app/target/administrator-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]