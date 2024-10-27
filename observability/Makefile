run-observability:
	@docker compose -f docker-compose.yaml up

start-otel-platform:
	@docker compose up otel-collector jaeger-server

download-otel-agent:
	@mkdir -p otel 2>&1
	@curl -L -o otel/opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
