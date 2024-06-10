package fr.deroffal.eshop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
public class ProductApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ProductApplication.class);
    app.setBannerMode(OFF);
    app.run(args);
  }

}
