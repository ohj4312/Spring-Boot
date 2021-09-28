package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

   //이부분은 lombok 을 사용하면 @RequiredArgsConstructor로 간단히 만들수있다.
    //@Autowired //생성자에서 주입햇으므로 필드주입 필요없다.
    private AsyncService asyncService;


    public ApiController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }
    //----------------------------

/*    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        log.info("method end");
        return "hello";
    }*/

    @GetMapping("/hello")
    public CompletableFuture hello(){
        log.info("completable future init");
        return asyncService.run();
    }

}
