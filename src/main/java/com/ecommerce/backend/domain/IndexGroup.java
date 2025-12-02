package com.ecommerce.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "index_groups")
@Getter
@Setter
public class IndexGroup {
    @Id
    private Integer indexCode;
    private Integer indexGroupNo;
    private String indexName;
    private String indexGroupName;

}