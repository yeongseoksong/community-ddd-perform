package com.portfolio.community.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@AllArgsConstructor
@Getter
public class ProductRequest implements Serializable {
    private final String name;
    private final String description;
    private final Long period;
    private final Long price;
}