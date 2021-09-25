package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){
        //@Valid 객체에 Validation이 필요할때 붙여서 사용, 헤당 객체 안의 Validation에 해당하는 Annotation을 검사한다.
        //BindingResult : valid의 결과를 받는다.
        if(bindingResult.hasErrors()){
            StringBuilder sb=new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field=(FieldError) objectError;
                String message=objectError.getDefaultMessage();

                System.out.println("field : "+field.getField());
                System.out.println(message);

                sb.append("filed : "+field.getField()+"\n");
                sb.append("message : "+message+"\n");

            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);

        /*if(user.getAge()>=90){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }*/
        return ResponseEntity.ok(user);
    }
}
