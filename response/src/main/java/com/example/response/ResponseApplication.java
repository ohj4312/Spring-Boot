package com.example.response;

import com.example.response.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class ResponseApplication {


	public static void main(String[] args) {
		SpringApplication.run(ResponseApplication.class, args);
		System.out.println("ok");
	}


}
