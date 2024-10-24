# OpenTelemetry test configuration

## Testing with OpenTelemetry noop.

We are using the Spring profile `SKIP_OTEL`.
In main configuration, it means that we are not injecting the production `OpenTelemetry` instance (aka `GlobalOpenTelemetry#get`), but an instance that will not do anything (`OpenTelemetry#noop`).

It is easier to configure for testing, but it is not sufficient if we actually want to test the OpenTelemetry instrumentation : indeed, the `noop` implementation does not do anything, so we can't know if our instrumentation is correct.

Implementation : `EshopTracerNoopTest.java`.

## Testing with OpenTelemetryExtension

We are registering and creating a bean with `OpenTelemetryExtension`.
It allows us to retrieve the otel test sdk. We can use `OpenTelemetryExtension#getOpenTelemetry` to have an instance of OpenTelemetry dedicated for test.

It is now possible to detect span created.

Implementation : `EshopTracerTest.java`.

## Testing with mock collector

Next step would be to do [this kind of test](https://github.com/open-telemetry/opentelemetry-java-examples/blob/main/telemetry-testing/src/test/java/io/opentelemetry/example/telemetry/ApplicationTest.java), where we are creating a fake collector with MockServer, and verifying that the application is sending http requests to it.
