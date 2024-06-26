SHELL := /bin/bash

run-with-docker-compose:
	@docker compose -f integration/docker-compose.yaml up

build-docker: build-maven
	@./scripts/build-docker.sh

build-maven:
	@mvn clean package -f applications/pom.xml

download-otel-agent:
	@mkdir -p applications/otel 2>&1
	@curl -L -o applications/otel/opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
