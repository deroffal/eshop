package fr.deroffal.eshop.price.domain.cucumber;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.DomainTestConfig;
import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DomainTestConfig.class, DomainCucumberConfig.class})
@CucumberContextConfiguration
public class DomainContextCucumberConfig {

    @Autowired
    private ObjectMapper cucumberObjectMapper;

    @Autowired
    private DiscountPort discountPort;

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(final Object fromValue, final Type toValueType) {
        final JavaType javaType = cucumberObjectMapper.constructType(toValueType);
        return cucumberObjectMapper.convertValue(fromValue, javaType);
    }

    @Before
    public void before() {
        when(discountPort.loadByProduct(any())).thenReturn(Mono.empty());
    }

}
