package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest //이 Annotation을 붙이면 모든 Bean이 등록이 되므로 import 하지 않아도 된다. (통합테스트 방식)
//@Import({MarketApi.class,DollarCalculator.class}) //MarketApi를 주입받아서 사용하므로 이 클래스를 import어노테이션에 추가한다!!
public class DollarCalculatorTest {

    @MockBean //spring에서는 Bean으로 관리되므로 MockBean 어노테이션을 사용한다.
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000); //목으로 관리되는 marketAPi의 connect함수가 실행될때 3000을 리턴한다.

        int sum= calculator.sum(10,10);
        int minus= calculator.minus(10,10);

        Assertions.assertEquals(60000,sum);
        Assertions.assertEquals(0,minus);

    }
}
