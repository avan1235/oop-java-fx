package pl.edu.mimuw;

public class Constant extends ZeroArgumentExpression {
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    public Expression derivative() {
        return new Constant(0);
    }

    public double value(double x) {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}