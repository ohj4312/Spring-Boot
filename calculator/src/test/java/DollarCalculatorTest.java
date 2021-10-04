import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) //목처리를 하기위해서 목키토를 쓴다.
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach //test가 실행되기 이전에
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        System.out.println("hello JUnit");
        MarketApi marketApi=new MarketApi();
        DollarCalculator dollarCalculator=new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator=new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10,10));

        Assertions.assertEquals(22000,calculator.sum(10,10));
        Assertions.assertEquals(0,calculator.minus(10,10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator=new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator=new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10,10));

        Assertions.assertEquals(60000,calculator.sum(10,10));
        Assertions.assertEquals(0,calculator.minus(10,10));
    }
}
