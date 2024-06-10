download-otel-agent:
	@mkdir -p applications/otel 2>&1
	@curl -L -o applications/otel/opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

build-docker-images:
	@./applications/build-docker.sh
