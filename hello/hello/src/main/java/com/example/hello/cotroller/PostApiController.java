package com.example.hello.cotroller;

import com.example.hello.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    /*
    @PostMapping("/post")
    public void post(@RequestBody Map<String,Object> requestData){
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }

     */
    @PostMapping("/post")
    public void post(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);

        //POST로 들어오는 request 데이터를 사용하기 위해서는 @RequestBody를 사용해야 한다.
        //key 값과 DTO의 변수의 값이 같아야한다.
        //json은 스네이크 케이스, 변수로는 카멜 케이스로 설정되었다면
        //DTO 객체를 통해 각각의 값에 접근한다.
        //requestData.getAccount();
    }
}
