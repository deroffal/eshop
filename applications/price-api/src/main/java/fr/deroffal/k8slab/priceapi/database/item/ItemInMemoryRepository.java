package fr.deroffal.k8slab.priceapi.database.item;

import fr.deroffal.k8slab.priceapi.domain.ItemPort;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ItemInMemoryRepository implements InitializingBean, ItemPort {

    private final List<ItemPrice> items = new ArrayList<>();

    @Override
    public Optional<ItemPrice> loadItem(final String name) {
        return items.stream().filter(item -> item.name().equals(name)).findFirst();
    }

    @Override
    public Mono<ItemPrice> getPrice(UUID product) {
        return items.stream()
                .filter(item -> item.product().equals(product))
                .findAny()
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public void afterPropertiesSet() {
        //@formatter:off
       items.add(new ItemPrice(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), "product 1", new BigDecimal(1200), "EUR"));
       items.add(new ItemPrice(UUID.fromString("e207a162-610b-4edf-953f-271b3adfbef7"), "product 2", new BigDecimal(12398), "EUR"));
       items.add(new ItemPrice(UUID.fromString("9f84da1e-415b-4e3e-aad4-8012234f629d"), "product 3", new BigDecimal(500), "USD"));
       items.add(new ItemPrice(UUID.fromString("ee31ce9b-661d-4ef5-a8f8-913215c59368"), "product 4", new BigDecimal(1390), "EUR"));
       items.add(new ItemPrice(UUID.fromString("c3faabbc-1035-4ed4-93d5-af5af715013b"), "product 5", new BigDecimal(2000), "EUR"));
       items.add(new ItemPrice(UUID.fromString("bc7254fd-6ab7-436f-ad79-1362af6f0497"), "product 6", new BigDecimal(5499), "EUR"));
       items.add(new ItemPrice(UUID.fromString("d8f69d6c-d334-4de9-b406-3da5b3669bc2"), "product 7", new BigDecimal(3500), "USD"));
       items.add(new ItemPrice(UUID.fromString("cb0cb429-eac3-4a92-891c-9bb174b22076"), "product 8", new BigDecimal(9999), "EUR"));
        //@formatter:on
    }
}
