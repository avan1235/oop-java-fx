package pl.edu.mimuw;

public class Ln extends OneArgumentExpression {

    public Ln(Expression expression) {
        super(expression, "ln");
    }

    public Ln() {
        super("ln");
    }

    public double value(double x) {
        return Math.log(expression.value(x));
    }

    public Expression derivative() {
        return new Division(
                expression.derivative(),
                expression);
    }
}