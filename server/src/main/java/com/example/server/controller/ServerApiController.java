package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        //return "hello server";
        return user;
    }

    /*@PostMapping("user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName,
                        @RequestHeader("x-authorization") String authorization, @RequestHeader("custom-header") String costomHeader){

        log.info("userId : {} , userName : {} ",userId,userName);
        log.info("authorization : {} , customheader : {} ",authorization,costomHeader);
        log.info("client req : {}",user);

        return user;
    }*/

    //다양한 JSON 타입 연습
    @PostMapping("user/{userId}/name/{userName}")
    public Req<User> post( HttpEntity<String> entity, //null값이 나왔기 때문에 확인을 하기위해 순수한 HttpEntuty를 확인해볼수있다.
                           @RequestBody Req<User> user,
                           @PathVariable int userId, @PathVariable String userName,
                          @RequestHeader("x-authorization") String authorization, @RequestHeader("custom-header") String costomHeader){

        //log.info("req : {} ", entity.getBody());
        log.info("userId : {} , userName : {} ",userId,userName);
        log.info("authorization : {} , customheader : {} ",authorization,costomHeader);
        log.info("client req : {}",user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header() //빈 객체 테스트를 위함
        );

//        response.setResBody(null);
        response.setResBody(user.getResBody());
        return response;
    }
}
