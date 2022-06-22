package pl.edu.mimuw;

public class ExpressionFather extends OneArgumentExpression {

    public ExpressionFather(Expression expression) {
        super(expression, "");
    }

    public ExpressionFather() {
        super("");
    }

    public double value(double x) {
        return expression.value(x);
    }

    public Expression derivative() {
        return expression.derivative();
    }

    @Override
    public String toString() {
        return expression.toString();
    }
}