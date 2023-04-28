package fr.deroffal.k8slab.stockapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

  @GetMapping("/{productName}")
  public Stock getStockByProductName(@PathVariable String productName) {
    return new Stock(productName, 10);
  }

}
