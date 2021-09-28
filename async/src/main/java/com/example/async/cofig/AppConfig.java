package com.example.async.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100); //최대 몇개까지 할 것인지
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        // corepoosize 10개 -> 다쓰면 queue에 내용이 들어간다 -> queue 10개 차면 다시 poosize 10개만큼 한번 더늘어나서 20개가된다
        //-> queue에 다시 10개 이런식으로 늘어난다. MaxPoolSize까지 차고 queue에 들어가는 것이아니라 queue에 maxpoolsize까지 들어간다.
        // Thread의 사용, 환경에 따라 많이 달라지는 부분이다..
        return threadPoolTaskExecutor;
    }
}
