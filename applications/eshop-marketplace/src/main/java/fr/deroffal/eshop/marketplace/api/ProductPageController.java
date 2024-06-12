package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.ProductDetail;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductPageController {

    private final ProductAggregatorService productAggregatorService;

    public ProductPageController(ProductAggregatorService productAggregatorService) {
        this.productAggregatorService = productAggregatorService;
    }

    @GetMapping("/{id}")
    public ProductDetail getProductDetail(@PathVariable UUID id) {
        return productAggregatorService.getProductDetail(id);
    }
}
