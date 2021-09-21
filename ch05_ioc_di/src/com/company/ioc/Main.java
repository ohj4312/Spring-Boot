package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        //encoding 예제
        String url="www.naver.com/books/it?page=10&size=20&name=spring-boot";

        //Base 64 encoding
        Encoder encoder=new Encoder();
        String result=encoder.encode(url);
        System.out.println(result);

        //url encoding
        UrlEncoder urlEncoder2=new UrlEncoder();
        String urlResult2=urlEncoder2.encode(url);
        System.out.println(urlResult2);

        //Interface 추가
        IEncoder base64Encoder=new Base64Encoder();
        String base64result=base64Encoder.encode(url);
        System.out.println(base64result);

        IEncoder urlEncoder=new UrlEncoder();
        String urlResult=urlEncoder.encode(url);
        System.out.println(urlResult);

        //DI 추가
        Encoder encoderDI=new Encoder(new Base64Encoder());
        //Encoder encoderDI=new Encoder(new UrlEncoder());
        String resultDI=encoder.encode(url);
        System.out.println(resultDI);

    }
}
