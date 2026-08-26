package fr.deroffal.eshop.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModuleTest {

    @Test
    void verifyModules() {
        ApplicationModules.of(CatalogApplication.class).verify();
    }
}
