import client.CalcClient;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;

public class Main {
    public static void main(String[] args) {

        // 1000
        Calculator calculator = new CalculatorImpl();
        // 2000                               1000
        //calculator = new CalculatorLogger(calculator);
        // 3000                             2000
        calculator = new CalculatorSecure(calculator);

                                            // 3000
        CalcClient client = new CalcClient(calculator);
        client.run();
    }
}