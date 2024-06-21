package fr.deroffal.eshop.stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    private StockService stockService;

    private static final UUID EXISTING_PRODUCT_ID = UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710");
    private static final UUID NON_EXISTING_PRODUCT_ID = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        stockService = new StockService();
    }

    @Test
    void testGetStockByProductExisting() {
        Optional<Stock> stock = stockService.getStockByProduct(EXISTING_PRODUCT_ID);

        assertTrue(stock.isPresent());
        assertEquals(EXISTING_PRODUCT_ID, stock.get().name());
        assertEquals(10, stock.get().quantity());
    }

    @Test
    void testGetStockByProductNonExisting() {
        Optional<Stock> stock = stockService.getStockByProduct(NON_EXISTING_PRODUCT_ID);

        assertFalse(stock.isPresent());
    }
}
