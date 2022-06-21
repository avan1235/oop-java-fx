package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.argumentless.Constant;
import pl.edu.mimuw.model.Expression;
import pl.edu.mimuw.model.binary.Times;

public class Cos extends UnaryExpression {
  private static final String COS_OPERATOR = "cos";

  public Cos(Expression child) {
    super(child, COS_OPERATOR);
  }

  @Override
  public double evaluateAtPoint(double point) {
    double childValue = this.child.evaluateAtPoint(point);
    return Math.cos(childValue);
  }

  @Override
  public Expression derivative() {
    Expression childDerivative = this.child.derivative();

    Expression baseDerivative = new Times(childDerivative, new Sin(this.child));
    return new Times(new Constant(-1), baseDerivative);
  }
}
