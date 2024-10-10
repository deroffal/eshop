package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final CartMapper mapper;
    private final Tracer tracer;

    public CartController(CartService service, CartMapper mapper, OpenTelemetry openTelemetry) {
        this.service = service;
        this.mapper = mapper;
        this.tracer = openTelemetry.getTracer("fr.deroffal.eshop.marketplace", "0.10-span");
    }

    @PostMapping("/")
    public ResponseEntity<CartModel> newCart() {
        Cart cart = service.create();

        Span span = tracer.spanBuilder("cart").startSpan();
        span.addEvent("cart created", Attributes.of(
                AttributeKey.stringKey("date"), DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                AttributeKey.stringKey("id"),  cart.id().toString()
        ));
        span.setAttribute("cart", cart.id().toString());

        return ResponseEntity.created(URI.create("/cart/" + cart.id()))
                .body(mapper.toModel(cart));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCart(@PathVariable("id") UUID id) {
        Span span = tracer.spanBuilder("cart").startSpan();
        span.setAttribute("cart", id.toString());
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CartModel updateItem(@PathVariable("id") UUID cartId, @RequestBody ItemModel item) {
        Span span = tracer.spanBuilder("cart").startSpan();
        span.setAttribute("cart", cartId.toString());
        return mapper.toModel(service.updateItem(cartId, mapper.toItem(item)));
    }

    @GetMapping("/{id}")
    public CartModel getCart(@PathVariable("id") UUID id) {
        Span span = tracer.spanBuilder("cart").startSpan();
        span.setAttribute("cart", id.toString());
        return mapper.toModel(service.getByUuid(id));
    }

    @GetMapping("/{id}/price")
    public CartPriceModel getPrice(@PathVariable("id") UUID id) {
        Span span = tracer.spanBuilder("cart").startSpan();
        span.setAttribute("cart", id.toString());

        var price = service.getPrice(id);
        span.addEvent("price calculated", Attributes.of(
                AttributeKey.stringKey("id"),  id.toString(),
                AttributeKey.stringKey("price"),  price.toString()

        ));

        return mapper.from(id, price);
    }
}
