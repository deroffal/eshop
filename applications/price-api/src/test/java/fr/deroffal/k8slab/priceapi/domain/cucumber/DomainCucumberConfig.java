package fr.deroffal.k8slab.priceapi.domain.cucumber;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Type;

@ContextConfiguration(classes = CucumberSpringConfig.class)
@CucumberContextConfiguration
public class DomainCucumberConfig {

    @Autowired
    private ObjectMapper cucumberObjectMapper;

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(final Object fromValue, final Type toValueType) {
        final JavaType javaType = cucumberObjectMapper.constructType(toValueType);
        return cucumberObjectMapper.convertValue(fromValue, javaType);
    }
}
