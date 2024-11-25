package fr.deroffal.eshop.price.application;

import org.mapstruct.MapperConfig;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

@MapperConfig(componentModel = SPRING, unmappedTargetPolicy = ERROR)
public interface MapperConfiguration {
}
