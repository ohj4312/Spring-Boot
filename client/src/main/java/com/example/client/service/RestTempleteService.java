package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTempleteService {

    //http://localhost:9090/api/server/hello 요청읋해서 response를 받아 올 것이다.
    public UserResponse hello(){
        //요청 URI 만들기
        URI uri= UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","aaa")
                .queryParam("age",99)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        //RESTTemplet 만들기
        RestTemplate restTemplate=new RestTemplate();

        //String result=restTemplate.getForObject(uri,String.class);
        //ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);

        //JSON 받기
        //UserResponse result=restTemplate.getForObject(uri,UserResponse.class);
        ResponseEntity<UserResponse> result=restTemplate.getForEntity(uri,UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        //getForObject가 실행되는 순간이 Client에서 http server로 붙는 순간
        //원래는 RestTemplete pool을 만들고 사용한다.

        //restTemplate는 http mehtod (get, post, put, delete)을 기본적으로 제공한다.
        // restTemplate는 getForObject restTemplate.getForEntity()는 리턴 타입이 다른 차이점이 있다.

        //return result;
        return result.getBody();
    }

    public UserResponse post(){
        //URI 만들기
        //http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri=UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        //expand 에 들어가는 값이 중괄호를 사용한 {userId},{userName}에 순차적으로 (,)로 구분되어 맞춰 들어간다.
        //buildAndExpand() 메소드도 있다.
        System.out.println(uri);

        // http body -> object -> object mapper -> json ->rest tempalte ->http body json
        UserRequest req=new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        //ResponseEntity<String> response = restTemplate.postForEntity(uri,req,String.class);
        // 응답이 무엇인지 모를 때 String으로 확인하는 것도 가능하다! 응답을 확인하고 class를 디자인해도 좋다.

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }



}
