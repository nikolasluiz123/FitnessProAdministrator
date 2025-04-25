FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/*

ENV WILDFLY_VERSION=30.0.1.Final
ENV WILDFLY_HOME=/opt/wildfly

RUN apt-get update && \
    apt-get install -y wget && \
    rm -rf /var/lib/apt/lists/*

RUN wget https://github.com/wildfly/wildfly/releases/download/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz -P /tmp

RUN tar xf /tmp/wildfly-$WILDFLY_VERSION.tar.gz -C /opt && \
    mv /opt/wildfly-$WILDFLY_VERSION $WILDFLY_HOME && \
    rm /tmp/wildfly-$WILDFLY_VERSION.tar.gz

RUN rm -rf $WILDFLY_HOME/standalone/deployments/*
RUN rm -rf $WILDFLY_HOME/standalone/tmp/*
RUN rm -rf $WILDFLY_HOME/standalone/work/*

RUN apt-get update && apt-get install -y --no-install-recommends tzdata && rm -rf /var/lib/apt/lists/*
ENV TZ=America/Sao_Paulo

RUN apt-get update && \
    apt-get install -y locales && \
    locale-gen pt_BR.UTF-8 && \
    update-locale LANG=pt_BR.UTF-8

ENV LANG=pt_BR.UTF-8 \
    LANGUAGE=pt_BR:pt \
    LC_ALL=pt_BR.UTF-8

COPY administrator.war $WILDFLY_HOME/standalone/deployments/

EXPOSE 8080

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]