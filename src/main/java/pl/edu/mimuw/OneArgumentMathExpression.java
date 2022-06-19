package pl.edu.mimuw;

public abstract class OneArgumentMathExpression extends MathExpression {
  public MathExpression onlyChild;


  public String toString() {
    if (this.onlyChild == null)
      return this.representation + "(...)";
    return this.representation + "(" + this.onlyChild + ")";
  }

  @Override
  public void input(MathExpression exp) {
    if (this.onlyChild == null) {
      this.insertChild(1, exp);
    } else {
      if (!this.onlyChild.checkCompletion())
        this.onlyChild.input(exp);
      else
        throw new IllegalArgumentException();
    }
  }

  @Override
  public void notifyAboutCompletion() {
    this.complete = (this.onlyChild != null && this.onlyChild.complete);
    if (this.complete)
      this.parent.notifyAboutCompletion();
  }


  @Override
  public boolean isConstant() {
    if (this.complete)
      return this.onlyChild.isConstant();
    return false;
  }

  @Override
  public void updateVariables() {
    this.hasVariable = (this.onlyChild != null && this.onlyChild.hasVariable);
    if (this.hasVariable && !this.parent.hasVariable)
      this.parent.updateVariables();
  }



  @Override
  public boolean isOneArg() {
    return true;
  }

  @Override
  public void insertChild(int num, MathExpression exp) {
    this.onlyChild = exp;
    exp.parent = this;
    this.complete = exp.complete;
    this.hasVariable = exp.hasVariable;
  }

  @Override
  public boolean checkCompletion() {
    if (this.complete)
      return true;
    if (this.onlyChild == null)
      return false;
    this.complete = this.onlyChild.checkCompletion();
    return this.complete;
  }

  @Override
  public boolean checkVariables() {
    if (this.hasVariable)
      return true;
    if (this.onlyChild != null) {
      if (this.onlyChild.isConstant()) {
        this.hasVariable = false;
        return false;
      } else {
        this.hasVariable = this.onlyChild.checkVariables();
        return this.hasVariable;
      }
    }
    return true;
  }

}
