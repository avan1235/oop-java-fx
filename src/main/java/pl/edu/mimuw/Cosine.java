package pl.edu.mimuw;

public class Cosine extends OneArgumentExpression {

    public Cosine(Expression expression) {
        super(expression, "cos");
    }

    public Cosine() {
        super("cos");
    }

    public double value(double x) {
        return Math.cos(expression.value(x));
    }

    public Expression derivative() {
        return new Multiplication(
                expression.derivative(),
                new Multiplication(new Constant(-1), new Sine(expression)));
    }
}