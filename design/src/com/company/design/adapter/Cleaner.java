package com.company.design.adapter;

//220v를 사용하는 청소기
public class Cleaner implements Electronic220V{
    @Override
    public void connect() {
        System.out.println("청소기 220v on");
    }
}
