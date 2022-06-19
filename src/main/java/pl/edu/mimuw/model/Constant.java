package pl.edu.mimuw.model;

public class Constant implements Expression {
  private final double value;

  public Constant(double value) {
    this.value = value;
  }


  @Override
  public double evaluateAtPoint(double point) {
    return this.value;
  }

  @Override
  public Expression derivative() {
    return new Constant(0);
  }

  @Override
  public String getStringRepresentation() {
    return Double.toString(this.value);
  }
}
