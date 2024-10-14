package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private final CartService service;
    private final CartMapper mapper;
    private final Tracer tracer;

    public CartController(CartService service, CartMapper mapper, OpenTelemetry openTelemetry) {
        this.service = service;
        this.mapper = mapper;
        this.tracer = GlobalOpenTelemetry.getTracer(CartController.class.getName());

//                openTelemetry.getTracer(
////                "fr.deroffal.eshop.marketplace",
//                CartController.class.getName(),
//                "0.10-span"
//        );
    }

    @PostMapping("/")
    public ResponseEntity<CartModel> newCart() {


        Context current = Context.current();
        Span span = tracer.spanBuilder("cart")
                .setParent(current)
                .startSpan();





        SpanContext spanContext = span.getSpanContext();
        String spanId = spanContext.getSpanId();
        String traceId = spanContext.getTraceId();

        LOGGER.info("traceId: {}, spanId: {}", traceId, spanId);

        try (Scope scope = span.makeCurrent()) {
            Cart cart = service.create();

            span.addEvent("cart created", Attributes.of(
                    AttributeKey.stringKey("date"), DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                    AttributeKey.stringKey("id"),  cart.id().toString()
            ));
            span.setAttribute("cart", cart.id().toString());


            return ResponseEntity.created(URI.create("/cart/" + cart.id()))
                    .body(mapper.toModel(cart));

        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }



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
