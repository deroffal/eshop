package fr.deroffal.eshop.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PriceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceApplication.class, args);
  }

}
