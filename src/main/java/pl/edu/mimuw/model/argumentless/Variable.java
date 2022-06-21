package pl.edu.mimuw.model.argumentless;

import pl.edu.mimuw.model.Expression;

public class Variable extends ArgumentlessExpression {
  @Override
  public double evaluateAtPoint(double point) {
    return point;
  }

  @Override
  public Expression derivative() {
    return new Constant(1);
  }

  @Override
  public String getStringRepresentation() {
    return "x";
  }
}
