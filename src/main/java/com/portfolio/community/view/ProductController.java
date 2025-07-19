package com.portfolio.community.view;

import com.portfolio.community.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String productList(Model model) {
         model.addAttribute("products", productService.findAllSortByAmount());
        return "pages/product/productList";
    }
}
