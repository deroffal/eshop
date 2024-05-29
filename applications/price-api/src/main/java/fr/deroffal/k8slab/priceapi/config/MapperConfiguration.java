package fr.deroffal.k8slab.priceapi.config;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = SPRING, unmappedTargetPolicy = ERROR)
public interface MapperConfiguration {
}
