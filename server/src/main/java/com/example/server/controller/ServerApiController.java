package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

    //naver api 실습
    @GetMapping("/naver")
    public String naver(){



        //uri 만들기
        //https://openapi.naver.com/v1/search/local.json
        // ?query=%EC%A3%BC%EC%8B%9D
        // &display=10
        // &start=1
        // &sort=random
        //utf-8 인코딩된 문자열을 받는다고 명시되어있으므로 utf-8로 인코딩해서 전달한다!
        URI uri= UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","중국집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri : {} ",uri);
        RestTemplate restTemplate=new RestTemplate();

        //header 만들기
        RequestEntity<Void> req=RequestEntity.get(uri)
                .header("X-Naver-Client-Id","id 넣기")
                .header("X-Naver-Client-Secret","key 넣기")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        return result.getBody();
    }
}
