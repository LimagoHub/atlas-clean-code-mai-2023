import client.CalcClient;
import common.LoggerProxy;
import math.*;

public class Main {
    public static void main(String[] args) {

        // 1000
        Calculator calculator = new CalculatorImpl();
        // 2000                               1000
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        // 3000                             2000
        calculator = new CalculatorSecure(calculator);

        calculator = new CalculatorBenchmark(calculator);
                                            // 3000
        CalcClient client = new CalcClient(calculator);
        client.run();
    }
}