FROM eclipse-temurin:21-jdk as build

# prepare app dependecies
WORKDIR /workspace/app
COPY target/*.jar /workspace/app
RUN mkdir -p /workspace/app/dependency && (cd /workspace/app/dependency; jar -xf ../*.jar)

# prepare otel agent
WORKDIR /workspace/app/otel
ENV OTEL_URL=${OTEL_URL:-"https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar"}
RUN curl -L -o ./opentelemetry-javaagent.jar ${OTEL_URL}

FROM eclipse-temurin:21-jdk
RUN addgroup spring && useradd spring -g spring
USER spring:spring

ARG BUILD_DIR=/workspace/app
ARG DEPENDENCY=$BUILD_DIR/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ARG OTEL_DIR=$BUILD_DIR/otel
COPY --from=build ${OTEL_DIR}/opentelemetry-javaagent.jar /app/otel/opentelemetry-javaagent.jar

ENTRYPOINT ["java","-cp","app:app/lib/*","fr.deroffal.eshop.marketplace.MarketplaceApplication"]
