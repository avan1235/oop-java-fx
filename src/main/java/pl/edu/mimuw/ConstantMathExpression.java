package pl.edu.mimuw;

public class ConstantMathExpression extends MathExpression {
  public final double value;

  public ConstantMathExpression(double value) {
    this.representation = "" + value;
    this.value = value;
    this.parent = null;
    this.complete = true;
    this.hasVariable = false;
  }

  public String toString() {
    return ("" + this.value);
  }

  @Override
  public Double ifConstantThenValue() {
    return this.value;
  }

  @Override
  public void input(MathExpression exp) {
    throw new IllegalArgumentException();
  }

  @Override
  public double compute() {
    return this.value;
  }

  @Override
  public boolean isConstant() {
    return true;
  }

  @Override
  public MathExpression calcDx() {
    return new ConstantMathExpression(0.0);
  }

  @Override
  public boolean checkCompletion() {
    return true;
  }
  @Override
  public boolean checkVariables() {
    return false;
  }


}
