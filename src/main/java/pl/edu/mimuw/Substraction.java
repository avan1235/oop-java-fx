package pl.edu.mimuw;

public class Substraction extends TwoArgumentExpression {

    public Substraction(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "-");
    }

    public Substraction() {
        super("-");
    }

    public Expression derivative() {
        return new Substraction(firstExpression.derivative(), secondExpression.derivative());
    }

    public double value(double x) {
        return firstExpression.value(x) - secondExpression.value(x);
    }
}