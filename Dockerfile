FROM openjdk:8-jdk-alpine AS java-base

RUN wget -O agent.jar #{opentelemetry-v1.24.0.jar}#

RUN addgroup -S hype && adduser -S -G hype hype
RUN mkdir /app
RUN chown hype:hype /app

ADD target/centrico-token-manager-#{setVersion.originalVersion}#.jar /app/centrico-token-manager.jar
ADD opentelemetry-agent-extension.jar /app/opentelemetry-agent-extension.jar
RUN mv agent.jar /app/agent.jar

FROM gcr.io/distroless/java11-debian11

COPY --from=java-base /etc/passwd /etc/group /etc/shadow /etc/
COPY --from=java-base /app /app

EXPOSE 8080

USER hype

VOLUME /tmp

ENTRYPOINT ["java", "-Xmx2g", "-jar", "-javaagent:/app/agent.jar", "/app/centrico-token-manager.jar"]