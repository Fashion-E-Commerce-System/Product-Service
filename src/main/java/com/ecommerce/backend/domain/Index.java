package com.ecommerce.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "indexes")
public class Index {

    @Id
    @Column(name = "index_code")
    private Character indexCode;

    @Column(name = "index_name")
    private String indexName;
}
