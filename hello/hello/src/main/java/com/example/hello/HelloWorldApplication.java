package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

// error 처리 : jdk 설정 변경(cannot find string)
// error 처리 : gradle-wrapper.properties - maven 뭐 다운로드 함 event log에 뜬거 (gradle 설정에 추가됨)
@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloWorldApplication.class, args);
		System.out.println("HEllo");
	}

}
