# OpenTelemetry test configuration

We are using the Spring profile `SKIP_OTEL`.
In main configuration, it means that we are not injecting the production `OpenTelemetry` instance (aka `GlobalOpenTelemetry#get`), but an instance that will not do anything (`OpenTelemetry#noop`).
It is easier to configure for testing, but it is not sufficient if we actually want to test the OpenTelemetry instrumentation.

We are registering and creating a bean with `OpenTelemetryExtension`.
It allows us to retrieve the otel test sdk. We can use `OpenTelemetryExtension#getOpenTelemetry` to have an instance of OpenTelemetry dedicated for test.

Next step would be to do [this kind of test](https://github.com/open-telemetry/opentelemetry-java-examples/blob/main/telemetry-testing/src/test/java/io/opentelemetry/example/telemetry/ApplicationTest.java), where we are creating a fake collector with MockServer, and verifying that the application is sending http requests to it.
