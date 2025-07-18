package com.portfolio.community.order.product;


import com.portfolio.community.common.entity.Month;
import com.portfolio.community.common.entity.Won;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product registProduct(ProductRequest productRequest  ) {
        Product product = new Product(productRequest.getName(),
                productRequest.getDescription(),
                new Month(productRequest.getPeriod()),
                new Won(productRequest.getPrice()));
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }


}
