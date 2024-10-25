package fr.deroffal.eshop.marketplace;

import fr.deroffal.eshop.observability.OpenTelemetryConfiguration;
import fr.deroffal.eshop.observability.OpenTelemetryNoopConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
@ComponentScan(basePackageClasses = {MarketplaceApplication.class, OpenTelemetryConfiguration.class})// FIXME auto import
public class MarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MarketplaceApplication.class);
        app.setBannerMode(OFF);
        app.run(args);
    }

}
