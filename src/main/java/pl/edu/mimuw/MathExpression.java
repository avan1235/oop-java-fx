package pl.edu.mimuw;

public abstract class MathExpression {
  private MathExpression parent;
  protected String representation;
  protected boolean complete;
  protected boolean hasVariable;

  public MathExpression() {
    this.parent = null;
    this.complete = false;
    this.hasVariable = false;
  }

  protected MathExpression getParent() {
    return this.parent;
  }

  protected void setParent(MathExpression parent) {
    this.parent = parent;
  }

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
