package com.ecommerce.backend.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "sections")
@Getter
@Setter
public class Section {

    @Id
    private Integer sectionNo;

    private String sectionName;

}