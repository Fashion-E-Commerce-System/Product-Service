package com.ecommerce.backend.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String productCode;


    private String prodName;

    @Column(length = 2000)
    private String detailDesc;

    @ManyToOne
    @JoinColumn(name = "product_type_no")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "graphical_appearance_no")
    private GraphicalAppearance graphicalAppearance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "colour_group_code")
    private ColourGroup colourGroup;

    @ManyToOne
    @JoinColumn(name = "index_code")
    private IndexGroup indexGroup;

    @ManyToOne
    @JoinColumn(name = "section_no")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "garment_group_no")
    private GarmentGroup garmentGroup;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_no")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_master_id")
    private PerceivedColourMaster perceivedColourMaster;

    @ManyToOne
    @JoinColumn(name = "perceived_colour_value_id")
    private PerceivedColourValue perceivedColourValue;

}