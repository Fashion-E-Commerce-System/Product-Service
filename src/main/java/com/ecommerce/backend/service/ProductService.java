package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ProductEvent;

import com.ecommerce.backend.domain.Product;

import com.ecommerce.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import java.util.List;

import java.util.Optional;



@Service

@RequiredArgsConstructor

public class ProductService {



    private final ProductRepository productRepository;

    private final OutboxEventService outboxEventService;



    public List<Product> findAll() {

        return productRepository.findAll();

    }



    public Optional<Product> findById(Integer id) {

        return productRepository.findById(id);

    }



    @Transactional

    public Product save(Product product) {

        Product savedProduct = productRepository.save(product);

        String eventType = product.getProductId() == null ? "PRODUCT_CREATED" : "PRODUCT_UPDATED";

        ProductEvent productEvent = new ProductEvent(savedProduct.getProductId(), savedProduct.getProdName());

        outboxEventService.createOutboxEvent("product", savedProduct.getProductId().toString(), eventType, productEvent);

        return savedProduct;

    }



    @Transactional

    public void deleteById(Integer id) {

        productRepository.deleteById(id);

        outboxEventService.createOutboxEvent("product", id.toString(), "PRODUCT_DELETED", id);

    }

}