package com.example.client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTempleteService {

    //http://localhost:9090/api/server/hello 요청읋해서 response를 받아 올 것이다.
    public String hello(){
        //요청 URI 만들기
        URI uri= UriComponentsBuilder.fromUriString("http://localhost:9090").path("/api/server/hello").encode().build().toUri();
        System.out.println(uri.toString());

        //RESTTemplet 만들기
        RestTemplate restTemplate=new RestTemplate();
        String result=restTemplate.getForObject(uri,String.class);
        //getForObject가 실행되는 순간이 Client에서 http server로 붙는 순간
        //원래는 RestTemplete pool을 만들고 사용한다.

        //restTemplate는 http mehtod (get, post, put, delete)을 기본적으로 제공한다.
        // restTemplate는 getForObject restTemplate.getForEntity()는 리턴 타입이 다른 차이점이 있다.

        return result;
    }
}
