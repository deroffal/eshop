package fr.deroffal.eshop.marketplace.clients;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.Logger.Level.BASIC;

@Configuration
@EnableFeignClients
public class FeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return BASIC;
    }
}
