package fr.deroffal.eshop.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CatalogApplication.class);
        app.setBannerMode(OFF);
        app.run(args);
    }
}
