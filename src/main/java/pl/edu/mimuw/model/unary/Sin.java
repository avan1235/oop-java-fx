package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.Expression;
import pl.edu.mimuw.model.binary.Times;

public class Sin extends UnaryExpression {
  private static final String SIN_OPERATOR = "sin";

  public Sin(Expression child) {
    super(child, SIN_OPERATOR);
  }

  @Override
  public double evaluateAtPoint(double point) {
    double childValue = this.child.evaluateAtPoint(point);
    return Math.sin(childValue);
  }

  @Override
  public Expression derivative() {
    Expression childDerivative = this.child.derivative();
    return new Times(childDerivative, new Cos(this.child));
  }
}
