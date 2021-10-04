package com.example.springcalculator.component;

import org.springframework.stereotype.Component;

@Component
public class MarketApi {

    public int connect(){
        //실제로 dollar는 실시간으로 항상 변할 것이다.
        //이때 목킹 처리를 한다고 한다!
        return 1100;
    }
}
