package pl.edu.mimuw;

public class Sine extends OneArgumentExpression {

    public Sine(Expression expression) {
        super(expression, "sin");
    }

    public Sine() {
        super("sin");
    }

    public double value(double x) {
        return Math.sin(expression.value(x));
    }

    public Expression derivative() {
        return new Multiplication(
                expression.derivative(),
                new Cosine(expression));
    }
}