package fr.deroffal.eshop.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private UUID productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(productId, ProductType.BIKE, "name", "description");
    }

    @Test
    void testSave() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertEquals(product, savedProduct);
        verify(productRepository).save(product);
    }

    @Test
    void testGetProductDetail() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductDetail(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(product, new Product(UUID.randomUUID(), ProductType.BIKE, "name", "description"));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.findAll();

        assertEquals(2, foundProducts.size());
        verify(productRepository).findAll();
    }
}
