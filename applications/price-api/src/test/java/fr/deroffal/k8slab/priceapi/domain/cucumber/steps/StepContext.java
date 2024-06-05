package fr.deroffal.k8slab.priceapi.domain.cucumber.steps;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StepContext {

    private final Map<String, UUID> itemsByName = new HashMap<>();

    public UUID getItemByName(final String name) {
        return itemsByName.get(name);
    }

    public void addItem(final String name, final UUID uuid) {
        itemsByName.put(name, uuid);
    }
}
