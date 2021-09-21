package com.example.ch05.aop.aop;

import nonapi.io.github.classgraph.utils.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //AOP임을 알려준다.
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.ch05.aop.controller..*.*(..))")
    private void cut(){

    }

    @Before("cut()") //cut()이 실행되기 전에 실행
    public void before(JoinPoint joinPoint){ // JoinPoint : 들어가는 지점에 대한 정보를 가지고 있는 객체

        //메소드 이름 출력
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        Method method=methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args=joinPoint.getArgs(); //메소드의 매개변수 배열을 리턴한다.

        for(Object obj:args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    @AfterReturning(value = "cut()",returning = "returnObj") //cut이 정상 실행 후 반환했을 때 실행
    public void afterReturn(JoinPoint joinPoint,Object returnObj){ //AfterReturning은 Object 객체를 받을수있다. (returning 이름과 매칭되야 한다.)
        System.out.println("return obj");
        System.out.println(returnObj);
    }
}
