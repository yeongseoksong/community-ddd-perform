package com.portfolio.community.view;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {


    @GetMapping("payments/toss")
    public String tossPayment(){
        return "pages/payment/toss";
    }
}
