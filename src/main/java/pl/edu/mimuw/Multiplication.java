package pl.edu.mimuw;

public class Multiplication extends TwoArgumentExpression {

    public Multiplication(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "*");
    }

    public Multiplication() {
        super("*");
    }

    public Expression derivative() {
        return new Addition(
                new Multiplication(firstExpression.derivative(), secondExpression),
                new Multiplication(firstExpression, secondExpression.derivative()));
    }

    public double value(double x) {
        return firstExpression.value(x) * secondExpression.value(x);
    }
}