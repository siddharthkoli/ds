import CalculatorModule.CalculatorPOA;

class CalculatorImpl extends CalculatorPOA {
    public CalculatorImpl() {}

    public double add(double a, double b) {
        return a + b;
    }
}