package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTempleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    //@Autowired 보다 생성자를 통해 주입하는 경우가 요즘 더 많다.
    private final RestTempleteService restTempleteService;

    public ApiController(RestTempleteService restTempleteService) {
        this.restTempleteService = restTempleteService;
    }

    /*@GetMapping("/hello")
    public UserResponse hello(){
        //return restTempleteService.hello();
        //return restTempleteService.post();
        restTempleteService.exchange();
        return new UserResponse();
    }*/

    @GetMapping("/hello")
    public Req<UserResponse> hello(){
        return restTempleteService.genericExchage();

    }
}
