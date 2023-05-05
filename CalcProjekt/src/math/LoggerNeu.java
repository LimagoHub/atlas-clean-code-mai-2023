package math;

public class LoggerNeu extends CalculatorImpl{

    @Override
    public double add(final double a, final double b) {
        System.out.println("Add");
        return super.add(a, b);
    }

    @Override
    public double sub(final double a, final double b) {
        return super.sub(a, b);
    }
}
