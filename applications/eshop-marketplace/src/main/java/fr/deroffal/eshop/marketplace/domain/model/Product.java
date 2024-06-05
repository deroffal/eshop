package fr.deroffal.eshop.marketplace.domain.model;


import java.util.UUID;

public record Product(UUID id, String name, String description) {

}
