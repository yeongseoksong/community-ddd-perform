package com.portfolio.community.subscribe.domain.order;


import com.portfolio.community.common.entity.Won;
import com.portfolio.community.product.ProductId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @AttributeOverride(name="value",column = @Column(name="product_id"))
    private ProductId productId;

    private String productName;

    private Won amount;

    public OrderItem(ProductId productId, String productName, Won amount) {
        if(productId==null || productName==null || amount ==null)
            throw new IllegalArgumentException( "productId and productName and price are required" );

        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
    }
}
