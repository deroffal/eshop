package fr.deroffal.eshop.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PriceApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(PriceApplication.class);
    app.setBannerMode(OFF);
    app.run(args);
  }

}
