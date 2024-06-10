package fr.deroffal.eshop.price.clients;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ClientConfiguration.class)
@ConfigurationPropertiesScan(basePackageClasses = ClientConfiguration.class)
public class ClientTestConfiguration {

}
