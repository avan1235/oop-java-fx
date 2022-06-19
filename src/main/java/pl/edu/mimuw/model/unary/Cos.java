package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.Constant;
import pl.edu.mimuw.model.Expression;
import pl.edu.mimuw.model.binary.Times;

public class Cos extends UnaryExpression {
  public Cos(Expression child) {
    super(child);
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

  @Override
  public String getOperatorString() {
    return "cos";
  }
}
