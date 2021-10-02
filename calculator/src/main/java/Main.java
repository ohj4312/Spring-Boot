public class Main {
    public static void main(String[] args) {
        System.out.println("hello JUnit");

        Calculator calculator=new Calculator(new KrwCalculator());

        System.out.println(calculator.sum(10,10));
    }
}
