package com.example.ch05.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.ch05.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.ch05.aop.annotation.Timer)") //Timer 어노테이션이 설정된 메소드에 적용
    private void enableTimer(){}

    @Around("cut() && enableTimer()") //두가지 조건을 같이 쓴다
    public void arround(ProceedingJoinPoint joinPoint) throws Throwable {

        //method 실행 전
        StopWatch stopWatch=new StopWatch(); //Spring에서 지원해주는 class이다
        stopWatch.start();
        //method 실행
        Object result=joinPoint.proceed();

        //method 실행 후/
        stopWatch.stop();
        System.out.println("total time: "+ stopWatch.getTotalTimeSeconds());
    }
}
