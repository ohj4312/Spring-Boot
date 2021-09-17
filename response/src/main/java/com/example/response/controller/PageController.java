package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    //ResponseEntity

    @ResponseBody //responsebody를 만들어 응답한다는 Annotation이다.
    @GetMapping("/user")
    public User user(){
        var user=new User(); //java v11 이후 추가된 타입추론
        user.setName("steve");
        user.setAddress("Korea");
        return user;
    }

}
