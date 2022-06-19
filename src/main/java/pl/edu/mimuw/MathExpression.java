package pl.edu.mimuw;

public abstract class MathExpression {
  public MathExpression parent;
  public String representation;
  public boolean complete;
  public boolean hasVariable;

  public Double ifConstantThenValue() {
    return null;
  }

  public void input(MathExpression exp) {

  }

  public boolean isConstant() {
    return false;
  }

  public void notifyAboutCompletion() {
  }

  public double compute() {
    return -1.0;
  }

  public void updateVariables() {
  }

  public MathExpression calcDx() {
    return null;
  }

  public boolean isOneArg() {
    return false;
  }

  public void insertChild(int num, MathExpression exp) {

  }

  public boolean checkCompletion() {
    return false;
  }

  public boolean checkVariables() {
    return false;
  }

  public MathExpression recompile() {
    return this;
  }

}
