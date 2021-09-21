package com.example.ch05;

import com.example.ch05.ioc.ApplicationContextProvider;
import com.example.ch05.ioc.Base64Encoder;
import com.example.ch05.ioc.Encoder;
import com.example.ch05.ioc.UrlEncoder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;

@SpringBootApplication //내부적으로 이 annotation도 @Component를 포함하고 있고, Spring에서 Bean으로 관리되는 대상이 된다.
public class Ch05Application {
/*
	public static void main(String[] args) {

		SpringApplication.run(Ch05Application.class, args);
	}*/

}

