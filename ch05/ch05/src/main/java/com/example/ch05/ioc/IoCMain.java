package com.example.ch05.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication //내부적으로 이 annotation도 @Component를 포함하고 있고, Spring에서 Bean으로 관리되는 대상이 된다.
public class IoCMain {

    public static void main(String[] args) {

        SpringApplication.run(IoCMain.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();
        Base64Encoder base64Encoder=context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder=context.getBean(UrlEncoder.class);

        Encoder encoder=new Encoder(base64Encoder);
        String url="www.naver.com/books/it?page=10&size=20&name=spring-boot";

        String result=encoder.encode(url);
        System.out.println(result);

        encoder.setIEncoder(urlEncoder);
        result=encoder.encode(url);
        System.out.println(result);

        //Component 어노테이션 추가후
//		Encoder encoder1= context.getBean(Encoder.class);
        String url1="www.naver.com/books/it?page=10&size=20&name=spring-boot";
//
//		String result1=encoder.encode(url1);
//		System.out.println("IoC :"+result1);


        //AppConfig 추가 후
        Encoder encoder2= context.getBean("base64Encode",Encoder.class);

        String result2=encoder.encode(url1);
        System.out.println("IoC :"+result2);
    }

}

@Configuration //한개의 클래스에서 여러개의 Bean을 등록한다는 의미 (내부에 @Component를 가지고있다)
class AppConfig{

    @Bean("base64Encode") // Bean을 미리 등록해서 사용한다.
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode") //UrlEncoder에 @Component로 관리되므로 동일한 이름이라 충돌발생 -> urlEncode로 이름을 붙인다.
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
