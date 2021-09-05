package com.company;

import com.company.design.adapter.*;
import com.company.design.singleton.Aclazz;
import com.company.design.singleton.Bclazz;
import com.company.design.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {
        /*
        Aclazz aClazz=new Aclazz();
        Bclazz bClazz=new Bclazz();

        SocketClient aClient=aClazz.getSocketClient();
        SocketClient bClient=bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));
        */

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

    }


    //콘센트를 담당하는 함수
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}
