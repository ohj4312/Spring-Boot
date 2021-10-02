public class Calculator {

    private ICaluclator iCaluclator;

    public Calculator(ICaluclator iCaluclator){
        this.iCaluclator=iCaluclator;
    }

    public int sum(int x, int y){
        return this.iCaluclator.sum(x, y);
    }

    public int minus(int x, int y){
        return this.iCaluclator.minus(x, y);
    }
}
