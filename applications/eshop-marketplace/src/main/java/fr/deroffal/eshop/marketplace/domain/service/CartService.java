package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.CartPort;
import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.CartNotFoundException;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final CartPort port;
    private final PricePort pricePort;
    private final Tracer tracer;

    public CartService(CartPort port, PricePort pricePort, OpenTelemetry openTelemetry) {
        this.port = port;
        this.pricePort = pricePort;
        this.tracer = openTelemetry.getTracer("fr.deroffal.eshop.marketplace", "0.10-span");
    }

    public Cart getByUuid(UUID uuid) {
        return port.findById(uuid);
    }

    public Cart create() {
        Span span = tracer.spanBuilder("create-cart").startSpan();
        try (Scope scope = span.makeCurrent()) {
            return port.save(new Cart(null, new ArrayList<>()));
        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }

    }

    public Cart updateItem(UUID cartId, CartItem item) {
        Span span = tracer.spanBuilder("update-cart").startSpan();
        try (Scope scope = span.makeCurrent()) {

            Cart cart = port.findById(cartId);
            if (cart != null) {
                int itemIndex = -1;
                List<CartItem> items = cart.items();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).product().equals(item.product())) {
                        itemIndex = i;
                        break;
                    }
                }

                if (itemIndex == -1) {
                    items.add(item);
                } else {
                    CartItem cartItem = items.get(itemIndex);
                    items.remove(itemIndex);
                    long newQuantity = cartItem.quantity() + item.quantity();
                    if (newQuantity > 0) {
                        items.add(cartItem.withQuantity(newQuantity));
                    }
                }
                return port.save(cart);
            }
            throw new CartNotFoundException();

        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }


    }

    public void delete(UUID cart) {
        Span span = tracer.spanBuilder("update-cart").startSpan();
        try (Scope scope = span.makeCurrent()) {
            port.delete(cart);

        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }
    }

    public Price getPrice(UUID id) {
        Span span = tracer.spanBuilder("get-cart-price").startSpan();
        try (Scope scope = span.makeCurrent()) {
            Cart cart = port.findById(id);
            if (cart == null) {
                throw new CartNotFoundException();
            }
            return pricePort.getCartPrice(cart);
        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }
    }
}
