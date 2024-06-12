package fr.deroffal.eshop.marketplace.clients.price;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.service.PricePort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
class PriceService implements PricePort {

    private final PriceClient client;
    private final PriceMapper mapper;

    public PriceService(PriceClient client, PriceMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    @Async
    public CompletableFuture<Price> getPriceByProduct(UUID product) {
        var response = client.getPriceByProduct(product);
        return completedFuture(mapper.map(response));
    }

    @Override
    public Price getCartPrice(Cart cart) {
        var items = cart.items().stream().map(mapper::map).toList();
        var response = client.getCartPrice(items);
        return mapper.map(response);
    }
}
