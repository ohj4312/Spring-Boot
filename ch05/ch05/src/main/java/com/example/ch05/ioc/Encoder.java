package com.example.ch05.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;

//@Component
public class Encoder{

    private IEncoder iEncoder;

    public Encoder(){
        this.iEncoder=new Base64Encoder();
    }

    public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
