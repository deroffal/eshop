package fr.deroffal.eshop.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StockApplication.class);
        app.setBannerMode(OFF);
        app.run(args);
    }

}
