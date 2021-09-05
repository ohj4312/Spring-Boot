package com.company;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.HTML;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        /*
        //Singleton Pattern
        Aclazz aClazz=new Aclazz();
        Bclazz bClazz=new Bclazz();

        SocketClient aClient=aClazz.getSocketClient();
        SocketClient bClient=bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));

        //Adapter Pattern
        HairDryer hairDryer =new HairDryer();
        connect(hairDryer);

        Cleaner cleaner=new Cleaner();
        //connect(cleaner);
        // cleaner는 220v를 사용하는데 110v에 꽂아서 error 발생
        // -> Adatper를 생성하여 연결
        Electronic110V adapter=new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner=new AirConditioner();
        //connect(airConditioner);
        Electronic110V airAdapter =new SocketAdapter(airConditioner);
        connect(airAdapter);
        */

        //Proxy Pattern
        //cache를 사용하지 않을때
        /*
        Browser browser=new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();


        IBrowser browser=new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        */

        //AOP 구현
        //시간측정 구현
        AtomicLong start=new AtomicLong();
        AtomicLong end=new AtomicLong();

        //before와 after는 lambda 식으로 구현
        IBrowser aopBrowser=new AopBrowser("www.naver.com",
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now=System.currentTimeMillis();
                    end.set(now-start.get());
                }
                );

        aopBrowser.show();
        System.out.println("loading time : "+end.get());

        aopBrowser.show();
        System.out.println("loading time : "+end.get());
    }



    //콘센트를 담당하는 함수
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
