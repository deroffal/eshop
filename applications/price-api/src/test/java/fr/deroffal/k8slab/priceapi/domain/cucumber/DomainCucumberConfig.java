package fr.deroffal.k8slab.priceapi.domain.cucumber;

import fr.deroffal.k8slab.priceapi.domain.DiscountPort;
import fr.deroffal.k8slab.priceapi.domain.PriceStoragePort;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = CucumberSpringConfig.class)
@CucumberContextConfiguration
@RequiredArgsConstructor
public class DomainCucumberConfig {

    @MockBean
    private PriceStoragePort priceStoragePort;

    @MockBean
    private DiscountPort discountPort;

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(final Object fromValue, final Type toValueType) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JavaType javaType = objectMapper.constructType(toValueType);
        return objectMapper.convertValue(fromValue, javaType);
    }
}
