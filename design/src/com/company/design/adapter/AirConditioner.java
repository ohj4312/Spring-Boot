package com.company.design.adapter;

//220v를 사용하는 에어컨
public class AirConditioner implements Electronic220V{ //implement 자동완서 : alt+enter

    @Override
    public void connect() {
        System.out.println("에어컨 220v on");
    }
}
