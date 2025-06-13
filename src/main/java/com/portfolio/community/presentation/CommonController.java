package com.portfolio.community.presentation;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping("/")
    public String indexPage(){
        return "pages/index";
    }

    @RequestMapping("/mypage")
    public String myPage(){
        return "pages/member/myPage";
    }
}
