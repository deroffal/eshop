package fr.deroffal.eshop.marketplace.domain;

import fr.deroffal.eshop.marketplace.application.ApplicationConfiguration;
import fr.deroffal.eshop.marketplace.domain.service.PricePort;
import fr.deroffal.eshop.marketplace.domain.service.ProductPort;
import fr.deroffal.eshop.marketplace.domain.service.StockPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@Import(ApplicationConfiguration.class)
@ComponentScan(basePackages = {"fr.deroffal.eshop.marketplace.domain"})
public class MarketplaceDomainTestConfig {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @MockitoBean(types = {ProductPort.class, StockPort.class, PricePort.class, CartPort.class})
    public @interface MarketplaceDomainMocks {

    }

}
