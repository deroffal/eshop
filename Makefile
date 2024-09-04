SHELL := /bin/bash

run-local: build-docker
	@docker compose --env-file=.env up

test-local: build-api-tests
	@npm --prefix api-tests run test:local

build-api-tests:
	@npm --prefix api-tests install

build-docker: build-maven
	@docker compose build

build-maven:
	@mvn clean package -f applications/pom.xml

download-otel-agent:
	@mkdir -p observability/otel 2>&1
	@curl -L -o observability/otel/opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
