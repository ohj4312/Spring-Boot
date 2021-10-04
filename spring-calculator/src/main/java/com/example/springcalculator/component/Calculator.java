package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {

    private final ICaluclator iCaluclator;

    public int sum(int x, int y){
        this.iCaluclator.init();
        return this.iCaluclator.sum(x, y);
    }

    public int minus(int x, int y){
        this.iCaluclator.init();
        return this.iCaluclator.minus(x, y);
    }
}
