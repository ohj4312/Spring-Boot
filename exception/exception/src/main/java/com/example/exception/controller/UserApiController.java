package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api/user") // 에제를 위해 만들었지만 동일한 주소를 사용하니 Error 발생!ㅎㅎ
public class UserApiController {

    @GetMapping("")
    public User get(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age){
        //required=false는 해당 request param이 없어도 동작하지만 해당 내용은 null이 된다.
        User user=new User();
        user.setName(name);
        user.setAge(age);

        int a=10+age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }
}
