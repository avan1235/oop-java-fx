package pl.edu.mimuw.model.argumentless;

import pl.edu.mimuw.model.Expression;

public class Placeholder extends ArgumentlessExpression {

  @Override
  public double evaluateAtPoint(double point) {
    throw new IllegalStateException();
  }

  @Override
  public Expression derivative() {
    throw new IllegalStateException();
  }

  @Override
  public String getStringRepresentation() {
    return "?";
  }
}
