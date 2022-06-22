package pl.edu.mimuw;

public class Division extends TwoArgumentExpression {

    public Division(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression, "/");
    }

    public Division() {
        super("/");
    }

    public Expression derivative() {
        return new Division(
                new Substraction(
                        new Multiplication(firstExpression.derivative(), secondExpression),
                        new Multiplication(firstExpression, secondExpression.derivative())),
                new Multiplication(secondExpression, secondExpression));
    }

    public double value(double x) {
        return firstExpression.value(x) / secondExpression.value(x);
    }
}