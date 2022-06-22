package pl.edu.mimuw;

public class Addition extends TwoArgumentExpression {

    public Addition(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "+");
    }

    public Addition() {
        super("+");
    }

    public Expression derivative() {
        return new Addition(firstExpression.derivative(), secondExpression.derivative());
    }

    public double value(double x) {
        return firstExpression.value(x) + secondExpression.value(x);
    }
}