package fr.deroffal.k8slab.stockapi;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/stock")
public class StockController {

  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @GetMapping("/{product}")
  public Stock getStockByProduct(@PathVariable UUID product) {
    return stockService.getStockByProduct(product)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

}
