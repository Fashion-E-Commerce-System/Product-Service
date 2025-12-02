package com.ecommerce.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "denormalized_product")
public class DenormalizedProduct {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "product_type_no")
    private String productTypeNo;

    @Column(name = "product_type_name")
    private String productTypeName;

    @Column(name = "product_group_name")
    private String productGroupName;

    @Column(name = "graphical_appearance_no")
    private String graphicalAppearanceNo;

    @Column(name = "graphical_appearance_name")
    private String graphicalAppearanceName;

    @Column(name = "colour_group_code")
    private String colourGroupCode;

    @Column(name = "colour_group_name")
    private String colourGroupName;

    @Column(name = "perceived_colour_value_id")
    private String perceivedColourValueId;

    @Column(name = "perceived_colour_value_name")
    private String perceivedColourValueName;

    @Column(name = "perceived_colour_master_id")
    private String perceivedColourMasterId;

    @Column(name = "perceived_colour_master_name")
    private String perceivedColourMasterName;

    @Column(name = "department_no")
    private String departmentNo;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "index_code")
    private String indexCode;

    @Column(name = "index_name")
    private String indexName;

    @Column(name = "index_group_no")
    private String indexGroupNo;

    @Column(name = "index_group_name")
    private String indexGroupName;

    @Column(name = "section_no")
    private String sectionNo;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name = "garment_group_no")
    private String garmentGroupNo;

    @Column(name = "garment_group_name")
    private String garmentGroupName;

    @Column(name = "detail_desc",columnDefinition = "TEXT")
    private String detailDesc;
}