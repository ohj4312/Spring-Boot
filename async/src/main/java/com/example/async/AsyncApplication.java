package com.example.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 비동기 처리를 위한 설정
public class AsyncApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(AsyncApplication.class, args);
	}

}
