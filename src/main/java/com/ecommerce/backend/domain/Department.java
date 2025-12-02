package com.ecommerce.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {

    @Id
    private Integer departmentNo;

    private String departmentName;

}