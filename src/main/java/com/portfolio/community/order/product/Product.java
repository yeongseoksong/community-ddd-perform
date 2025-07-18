package com.portfolio.community.order.product;

import com.portfolio.community.common.entity.Month;
import com.portfolio.community.common.entity.Won;
import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {


    @Id
    @Column(name="product_id")
    private String id;

    @Column(name= "product_name",nullable = false, unique = true)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Embedded
    private Month period;

    @Embedded
    private Won price;


    public Product(String name, String description, Month period, Won price) {
       if(name==null  || period==null || price==null) {
           throw new IllegalArgumentException( "Product name and description are required");
       }

        this.id= RandomIdGenerator.generate(24);
        this.name = name;
        this.description = description;
        this.period = period;
        this.price = price;
    }

}
