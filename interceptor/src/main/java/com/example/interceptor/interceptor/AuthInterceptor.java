package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url=request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toUri();

        log.info("request url : {}",url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}",hasAnnotation);

        //나의 서버는 모두 public으로 동작 한다.
        // 단, Auth 권한을 가진 요청에 대해서는 세션, 쿠키, RequestParam 등을 확인하는 정책을 사용하겠다.
        if(hasAnnotation){
            //권한체크
            String query=uri.getQuery();
            log.info("query : {}",query);
            if(query.equals("name=steve")){
                return true;
            }
            //return false;
            throw new AuthException(); //Interceptor를 통과못할 때 예외처리를 위함.
        }

        return true; //false로 면 Interceptor 통과하지 못해서 Controller까지 들어갈 수 없다.
    }

    public boolean checkAnnotation(Object handler,Class clazz){

        //resource 에 대한 요청 : javascript, html etc..
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //anntation check
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(clazz)!=null || handlerMethod.getBeanType().getAnnotation(clazz)!=null){
            //Auth annotation이 있을 때는 true
            return true;
        }
        return false;
    }
}
