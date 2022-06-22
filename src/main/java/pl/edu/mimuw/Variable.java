package pl.edu.mimuw;

public class Variable extends ZeroArgumentExpression {

    public Variable() {

    }

    public Expression derivative() {
        return new Constant(1);
    }

    public double value(double x) {
        return x;
    }

    public String toString() {
        return "x";
    }
}