package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//inteceptor 등록하기
@Configuration
@RequiredArgsConstructor //생성된 field를 사용하여 생성자를 만들어준다. (lombok)
public class MVCConfig implements WebMvcConfigurer {

    //@Autowired : 순환 참조의 위험이 있기 때문에 생성자로 받는다.
     private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
    }
}
