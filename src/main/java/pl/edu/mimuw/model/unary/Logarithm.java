package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.argumentless.Constant;
import pl.edu.mimuw.model.Expression;
import pl.edu.mimuw.model.binary.Divide;
import pl.edu.mimuw.model.binary.Times;

public class Logarithm extends UnaryExpression {
  private static final String LOGARITHM_OPERATOR = "ln";

  public Logarithm(Expression child) {
    super(child, LOGARITHM_OPERATOR);
  }

  @Override
  public double evaluateAtPoint(double point) {
    double childValue = this.child.evaluateAtPoint(point);
    return Math.log(childValue);
  }

  @Override
  public Expression derivative() {
    Expression childDerivative = this.child.derivative();

    return new Times(childDerivative, new Divide(new Constant(1), this.child));
  }
}
