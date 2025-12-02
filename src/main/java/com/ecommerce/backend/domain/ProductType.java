package com.ecommerce.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "product_types")
@Getter
@Setter
public class ProductType {

    @Id
    private Integer productTypeNo;

    private String productTypeName;
    private String productGroupName;


}
