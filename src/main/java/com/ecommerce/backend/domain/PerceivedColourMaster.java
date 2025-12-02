package com.ecommerce.backend.domain;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "perceived_colour_masters")
@Getter
@Setter
public class PerceivedColourMaster {

    @Id
    private Integer perceivedColourMasterId;

    private String perceivedColourMasterName;
}