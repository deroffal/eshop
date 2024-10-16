package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import static io.opentelemetry.api.OpenTelemetry.noop;

@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryConfiguration.class)
public class OpenTelemetryTestConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return noop();
    }


//    public class OtelNoopInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
//        @Override
//        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
//            var values = TestPropertyValues.of(
//                    "marketplace.observability.enabled=false"
//            );
//            values.applyTo(applicationContext);
//        }
//    }
}
