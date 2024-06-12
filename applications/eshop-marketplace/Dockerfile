FROM eclipse-temurin:21-jdk as build
WORKDIR /workspace/app
COPY target/*.jar /workspace/app
RUN mkdir -p /workspace/app/dependency && (cd /workspace/app/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jdk
RUN addgroup spring && useradd spring -g spring
USER spring:spring
ARG DEPENDENCY=/workspace/app/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","fr.deroffal.eshop.marketplace.MarketplaceApplication"]
