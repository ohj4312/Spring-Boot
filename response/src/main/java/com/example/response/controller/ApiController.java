package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    //TEXT Response
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    //JSON Response
    // 동작 : request -> object mapper를 통해 object로 바뀐다 -> object -> @PostMapping method를 탄다. -> object mapper를 통해 json으로 변환 -> json ->response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
