package com.company.design.singleton;

public class Aclazz {
    private SocketClient socketClient;

    public Aclazz(){
        //private 생성자이기 때문에 new로 새로 생성하는 것이 아니라 getInstance()를 통해 객체에 접근
       // this.socketClient=SocketClient.getInstance();
        this.socketClient=new SocketClient();
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
