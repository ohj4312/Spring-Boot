package com.company.design.strategy;

public class Encoder {

    private EncodingStrategy encodingStrategy;

    public String getMessage(String messege){
        return this.encodingStrategy.encode(messege);
    }

    public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }
}
