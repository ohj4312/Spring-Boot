package com.example.hello.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") //http://localhost:8080/api/get/hello
    public String hello(){
        return "get Hello";
    }

    //@RequestMapping("hi") //get/post/put/delete 모든 메소드가 매핑되어 동작한다. (옛날 방식)
    //@RequestMapping(method=RequstMethod.GET)  == @GetMapping()
    @RequestMapping(path="/hi",method = RequestMethod.GET) //http://localhost:8080/api/get/hi Get으로 지정한다.
    public String hi(){
        return "hi";
    }

    //변화하는 구간은 path valiable로 받는다.
    //http://localhost:8080/api/get/path-variable
    @GetMapping("/path-variable")
    public String pathVariable(){
        return "";
    }
}
