package com.example.ch05.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder") // Spring이 Bean으로 만들어 관리해준다. (가로안은 명칭을 붙이는것이다. 74라는 이름으로 바꾸어 붙여보았다)
public class Base64Encoder implements IEncoder{
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
