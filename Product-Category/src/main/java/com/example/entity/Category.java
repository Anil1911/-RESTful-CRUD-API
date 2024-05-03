package com.example.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @NotBlank
    private String name;

    private String description;

     @OneToMany(mappedBy = "category")
    private List<Product> products;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }


    public Category() {
    }

    public Category(Long category_id, String name, String description, List<Product> products) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.products = products;
    }
}
