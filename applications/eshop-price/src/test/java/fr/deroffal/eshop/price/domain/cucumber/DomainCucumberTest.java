package fr.deroffal.eshop.price.domain.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = { "fr.deroffal.eshop.price.domain" },
    features = { "src/test/resources/features/" },
    plugin = {"json:target/cucumber.json"}
)
public class DomainCucumberTest {
}
