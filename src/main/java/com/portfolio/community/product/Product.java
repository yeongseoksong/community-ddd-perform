package com.portfolio.community.product;
import com.portfolio.community.common.entity.Month;
import com.portfolio.community.common.entity.Period;
import com.portfolio.community.common.entity.Won;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @EmbeddedId
    private ProductId id;

    @Column(name= "product_name",nullable = false, unique = true)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Embedded
    @AttributeOverride(name="value",column = @Column(name="amount"))
    private Month amount;

    @Embedded
    @AttributeOverride(name="value",column = @Column(name="price"))
    private Won price;


    public Product(String name, String description, Month amount, Won price) {
       if(name==null  || amount ==null || price==null) {
           throw new IllegalArgumentException( "Product name and description are required");
       }
        this.id=new ProductId();
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }


    public Period calcLocalDate(LocalDateTime now){
        LocalDateTime endTime = now.plusMonths(this.amount.getValue());
        return new Period(now, endTime);
    }
}
