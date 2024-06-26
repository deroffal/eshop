SHELL := /bin/bash

run-with-docker-compose:
	@docker compose up

build-docker: build-maven
	@./scripts/build-docker.sh

build-maven:
	@mvn clean package -f applications/pom.xml

download-otel-agent:
	@mkdir -p observability/otel 2>&1
	@curl -L -o observability/otel/opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
