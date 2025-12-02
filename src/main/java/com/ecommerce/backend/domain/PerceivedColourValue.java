package com.ecommerce.backend.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perceived_colour_values")
@Getter
@Setter
public class PerceivedColourValue {

    @Id
    private Integer perceivedColourValueId;

    private String perceivedColourValueName;
}
