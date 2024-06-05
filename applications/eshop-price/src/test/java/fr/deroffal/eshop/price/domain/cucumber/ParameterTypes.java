package fr.deroffal.eshop.price.domain.cucumber;

import fr.deroffal.eshop.price.domain.model.Price;
import io.cucumber.java.ParameterType;

import java.math.BigDecimal;

public class ParameterTypes {

    @ParameterType(".*")
    public Price price(String price) {
        String[] input = price.split(" ");
        BigDecimal amount = new BigDecimal(input[0]);
        return new Price(amount, input[1]);
    }
}
