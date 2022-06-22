package pl.edu.mimuw;

public class Derivative extends OneArgumentExpression {

    public Derivative(Expression expression) {
        super(expression, "d/dx");
    }

    public Derivative() {
        super("d/dx");
    }

    public double value(double x) {
        return expression.derivative().value(x);
    }

    public Expression derivative() {
        return expression.derivative().derivative();
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        s.append("(" + expression + ")'");
        return s.toString();
    }
}