package pl.edu.mimuw;

public class Variable extends MathExpression {
  public static Double assignedValue = null;

  public Variable() {
    super();
  }

  public String toString() {
    if (assignedValue == null)
      return "x";
    else
      return ""+assignedValue;
  }

  @Override
  public double compute() {
    return assignedValue;
  }

  @Override
  public MathExpression calcDx() {
    return new ConstantMathExpression(1.0);
  }

  @Override
  public boolean checkCompletion() {
    return true;
  }

  @Override
  public boolean checkVariables() {
    return true;
  }

}
