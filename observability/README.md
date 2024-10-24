# Observability

## Run in local, with IDE


```shell
# from $project-dir
make download-otel-agent
make start-otel-platform
```

To start with IntelliJ, we can use run configurations stored in `.run/otel` directory.
They are configured to start with OpenTelemetry environment variables, and to use the downloaded agent.

## Run in local, with Docker

```shell
# from $project-dir
make build-docker
# then run docker-compose file
docker compose -f observability/docker-compose.yaml up
```

Then browse to `http://localhost:16686/`.

Use Bruno with environment `local`.

## Run in k8s

See 
