package com.portfolio.community.subscribe.order.application;

import com.portfolio.community.product.ProductId;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public class InitOrderRequest {
    @NotNull
    @Schema(description = "상품 ID",example = "cvblj1234czx_Ab")
    private ProductId productId;

}
