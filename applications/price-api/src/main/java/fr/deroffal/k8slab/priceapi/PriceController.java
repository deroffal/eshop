package fr.deroffal.k8slab.priceapi;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/price")
public class PriceController {

  private final static Map<UUID, Price> PRICES = Stream.of(
          UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"),
          UUID.fromString("e207a162-610b-4edf-953f-271b3adfbef7"),
          UUID.fromString("9f84da1e-415b-4e3e-aad4-8012234f629d"),
          UUID.fromString("ee31ce9b-661d-4ef5-a8f8-913215c59368"),
          UUID.fromString("c3faabbc-1035-4ed4-93d5-af5af715013b"),
          UUID.fromString("bc7254fd-6ab7-436f-ad79-1362af6f0497"),
          UUID.fromString("d8f69d6c-d334-4de9-b406-3da5b3669bc2"),
          UUID.fromString("cb0cb429-eac3-4a92-891c-9bb174b22076")
      )
      .collect(Collectors.toMap(Function.identity(), id -> new Price(id, new BigDecimal("10.00"), "EUR")));

  @GetMapping("/{product}")
  public Price getStockByProductName(@PathVariable UUID product) {
    return Optional.ofNullable(PRICES.get(product))
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

}
