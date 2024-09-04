package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final CartMapper mapper;

    public CartController(CartService service, CartMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/")
    public ResponseEntity<CartModel> newCart() {
        Cart cart = service.create();
        return ResponseEntity.created(URI.create("/cart/" + cart.id()))
                .body(mapper.toModel(cart));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCart(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CartModel updateItem(@PathVariable("id") UUID cartId, @RequestBody ItemModel item) {
        return mapper.toModel(service.updateItem(cartId, mapper.toItem(item)));
    }

    @GetMapping("/{id}")
    public CartModel getCart(@PathVariable("id") UUID id) {
        return mapper.toModel(service.getByUuid(id));
    }

    @GetMapping("/{id}/price")
    public CartPriceModel getPrice(@PathVariable("id") UUID id) {
        var price = service.getPrice(id);
        return mapper.from(id, price);
    }
}
