package com.example.ch05.aop.aop;

import com.example.ch05.aop.dto.User;
import com.example.ch05.ioc.Base64Encoder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.ch05.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.ch05.aop.annotation.Decode)") //Decode 어노테이션이 설정된 메소드에 적용
    private void enableDecoder(){}

    @Before("cut() && enableDecoder()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args=joinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof User){
                User user=User.class.cast(arg); //형변환
                String base64Email=user.getEmail();
                String email=new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecoder()",returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if(returnObj instanceof User){
            User user=User.class.cast(returnObj);
            String email=user.getEmail();
            String base64Email=Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }
}
