package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import fr.deroffal.eshop.marketplace.observability.EshopTracer;
import fr.deroffal.eshop.marketplace.observability.TracerFactory;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final CartMapper mapper;
    private final EshopTracer tracer;

    public CartController(CartService service, CartMapper mapper, TracerFactory tracerFactory) {
        this.service = service;
        this.mapper = mapper;
        this.tracer = tracerFactory.newTracer(CartController.class);
    }

    @PostMapping("/")
    public ResponseEntity<CartModel> newCart() {
        return tracer.executeInSpan("cart-create", span -> {
            Cart cart = service.create();

            span.addEvent("cart created", Attributes.of(
                    AttributeKey.stringKey("date"), DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                    AttributeKey.stringKey("id"), cart.id().toString()
            ));
            span.setAttribute("cart", cart.id().toString());


            return ResponseEntity.created(URI.create("/cart/" + cart.id()))
                    .body(mapper.toModel(cart));
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCart(@PathVariable("id") UUID id) {
        tracer.executeInSpan("cart-delete", span -> {
            span.setAttribute("cart", id.toString());
            service.delete(id);
        });
    }

    @PutMapping("/{id}")
    public CartModel updateItem(@PathVariable("id") UUID cartId, @RequestBody ItemModel item) {
        return tracer.executeInSpan("cart-update", span -> {
            span.setAttribute("cart", cartId.toString());
            return mapper.toModel(service.updateItem(cartId, mapper.toItem(item)));
        });

    }

    @GetMapping("/{id}")
    public CartModel getCart(@PathVariable("id") UUID id) {
        return tracer.executeInSpan("span-get", span -> {
            span.setAttribute("cart", id.toString());
            return mapper.toModel(service.getByUuid(id));
        });
    }

    @GetMapping("/{id}/price")
    public CartPriceModel getPrice(@PathVariable("id") UUID id) {
        return tracer.executeInSpan("cart-price", span -> {
            span.setAttribute("cart", id.toString());

            var price = service.getPrice(id);
            span.addEvent("price calculated", Attributes.of(
                    AttributeKey.stringKey("id"), id.toString(),
                    AttributeKey.stringKey("price"), price.toString()

            ));

            return mapper.from(id, price);
        });
    }
}
