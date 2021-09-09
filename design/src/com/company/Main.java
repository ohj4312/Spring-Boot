package com.company;

import com.company.design.adapter.*;
import com.company.design.aop.AopBrowser;
import com.company.design.decorator.*;
import com.company.design.facade.Ftp;
import com.company.design.facade.Reader;
import com.company.design.facade.SftpClient;
import com.company.design.facade.Writer;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListener;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.HTML;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;

import java.sql.SQLOutput;
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

        //Decorator 패턴
        ICar audi=new Audi(1000);
        audi.showPrice();

        //a3
        ICar audi3=new A3(audi,"A3");
        audi3.showPrice();
        //a4
        ICar audi4=new A4(audi,"A4");
        audi4.showPrice();
        //a5
        ICar audi5=new A5(audi,"A5");
        audi5.showPrice();

        //Obeserver 패턴
        Button button=new Button("버튼");

        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });

        button.click("메세지 전달 : click1");
        button.click("메세지 전달 : click2");
        button.click("메세지 전달 : click3");
        button.click("메세지 전달 : click4");*/

        //Facade 패턴
        Ftp ftpClient=new Ftp("www.foo.co.kr",22,"home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();

        Writer writer=new Writer("text.tmp");
        writer.fileConnect();
        writer.write();

        Reader reader=new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisconnect();
        writer.fileDisConnect();
        ftpClient.disConnect();

        //facade 적용
        SftpClient sftpClient=new SftpClient("www.foo.co.kr",22,"/home/etc","text.tmp");
        sftpClient.connect();
        sftpClient.write();
        sftpClient.read();
        sftpClient.disConnect();

        // -> 이전 코드처럼 각각의 객체에 의존하는 거보다 facade 를 적용하여 전면만 바라볼수있는 객체를 통해 안에 있는 복잡한 여러 의존성을 갖은 것들은 새로운 인터페이스 형태로 제공한다.
        //-> 바깥쪽에서는 간략하게 코드를 줄일수있고 기능 자체는 sftClient가 온전히 지원해준다.
        //-> 여러가지 객체의존성을 안쪽으로 숨겨주는 것을 facade 패턴이라고 한다.
    }



    //콘센트를 담당하는 함수
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
