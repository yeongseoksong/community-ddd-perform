package com.portfolio.community.product;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductService {
    private final ProductRepository productRepository;

    public Product getById(ProductId productId){
        return productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product with id " + productId + " not found"));
    }
}
