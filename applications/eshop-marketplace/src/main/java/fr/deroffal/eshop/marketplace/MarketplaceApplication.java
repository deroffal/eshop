package fr.deroffal.eshop.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
public class MarketplaceApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(MarketplaceApplication.class);
    app.setBannerMode(OFF);
    app.run(args);
  }

}
