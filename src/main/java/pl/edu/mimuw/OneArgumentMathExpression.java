package pl.edu.mimuw;

public abstract class OneArgumentMathExpression extends MathExpression {
  private MathExpression onlyChild;


  public OneArgumentMathExpression() {
    super();
    this.onlyChild = null;
  }

  MathExpression getOnlyChild() {
    return this.onlyChild;
  }

  public String toString() {
    if (this.getOnlyChild() == null)
      return this.representation + "(...)";
    return this.representation + "(" + this.getOnlyChild() + ")";
  }

  @Override
  public void input(MathExpression exp) {
    if (this.getOnlyChild() == null) {
      this.insertChild(1, exp);
    } else {
      if (!this.getOnlyChild().checkCompletion())
        this.getOnlyChild().input(exp);
      else
        throw new IllegalArgumentException();
    }
  }

  @Override
  public void notifyAboutCompletion() {
    this.complete = (this.getOnlyChild() != null && this.getOnlyChild().checkCompletion());
    if (this.complete)
      this.getParent().notifyAboutCompletion();
  }


  @Override
  public boolean isConstant() {
    if (this.complete)
      return this.getOnlyChild().isConstant();
    return false;
  }

  @Override
  public void updateVariables() {
    this.hasVariable = (this.getOnlyChild() != null && this.getOnlyChild().hasVariable);
    if (this.hasVariable && !this.getParent().hasVariable)
      this.getParent().updateVariables();
  }



  @Override
  public boolean isOneArg() {
    return true;
  }

  @Override
  public void insertChild(int num, MathExpression exp) {
    this.onlyChild = exp;
    exp.setParent(this);
    this.complete = exp.complete;
    this.hasVariable = exp.hasVariable;
  }

  @Override
  public boolean checkCompletion() {
    if (this.complete)
      return true;
    if (this.getOnlyChild() == null)
      return false;
    this.complete = this.getOnlyChild().checkCompletion();
    return this.complete;
  }

  @Override
  public boolean checkVariables() {
    if (this.hasVariable)
      return true;
    if (this.getOnlyChild() != null) {
      if (this.getOnlyChild().isConstant()) {
        this.hasVariable = false;
        return false;
      } else {
        this.hasVariable = this.getOnlyChild().checkVariables();
        return this.hasVariable;
      }
    }
    return true;
  }

}
