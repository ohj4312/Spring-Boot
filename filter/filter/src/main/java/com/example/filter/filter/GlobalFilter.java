package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@Component //Spring에 의해 Bean으로 관리
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        ContentCachingRequestWrapper httpServletRequest=new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse=new ContentCachingResponseWrapper((HttpServletResponse)response);
        //길이는 캐시했지만 안의 내용은 복사해놓지 않은 상황이다. -> 후처리에서 처리

        /*BufferedReader br = httpServletRequest.getReader();
        br.lines().forEach(line->{
            log.info("url : {} , line : {} ",url,line);
        });*/
        //기준
        chain.doFilter(httpServletRequest,httpServletResponse);

        //후처리
        String url=httpServletRequest.getRequestURI();

        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("reqeust");

        String resContent=new String(httpServletResponse.getContentAsByteArray());
        int httpStatus=httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); // body에 내용을 다시 채워넣는다.

        log.info("response status : {} , responseBody : {}",httpStatus,reqContent);

    }
}
