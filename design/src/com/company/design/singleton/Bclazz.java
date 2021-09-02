package com.company.design.singleton;

public class Bclazz {
    private SocketClient socketClient;

    public Bclazz(){
        this.socketClient=SocketClient.getInstance();
        //this.socketClient=new SocketClient();
    }

    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
