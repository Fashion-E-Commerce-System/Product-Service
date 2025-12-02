package com.ecommerce.backend.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "garment_groups")
@Getter
@Setter
public class GarmentGroup {

    @Id
    private Integer garmentGroupNo;

    private String garmentGroupName;
}

