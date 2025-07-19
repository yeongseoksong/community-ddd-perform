package com.portfolio.community.product;

import com.portfolio.community.common.response.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductRestController {
    private final ProductService productService;

    @PostMapping
    public Resp<Product> registProduct(@RequestBody ProductRequest productRequest) {

        return Resp.ok(productService.registProduct(productRequest));
    }


    @GetMapping
    public Resp<List<Product>> getAllProducts() {
        return Resp.ok(productService.findAllSortByAmount());
    }


}
