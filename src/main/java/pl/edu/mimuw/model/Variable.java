package pl.edu.mimuw.model;

public class Variable implements Expression {
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
