package com.example.hello.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller로써 동작하기 위해서 어노테이션을 지정 RestController의 역할 부여
@RestController // 해당 class는 rest API를 처리하는 Controller (자동으로 Spring에서 인식)
@RequestMapping("/api") // URI를 지정해주는 Annotation(주소 매핑)
public class ApiController {

    @GetMapping("/hello") //http://localhost:8080/api/hello
    public String hello(){
        return "hello spring boot!";
    }
}
