package pl.edu.mimuw;

public abstract class TwoArgumentMathExpression extends MathExpression {
  private MathExpression leftChild;
  private MathExpression rightChild;

  public TwoArgumentMathExpression() {
    super();
    this.leftChild = null;
    this.rightChild = null;
  }



  MathExpression getLeftChild() {
    return this.leftChild;
  }

  public MathExpression getRightChild() {
    return this.rightChild;
  }

  public String toString() {
    if (this.leftChild == null)
      return ("(..." + this.representation + "...)");
    else {
      if (this.rightChild == null)
        return ("(" + this.leftChild + this.representation + "...)");
      else
        return ("(" + this.leftChild+ this.representation + this.rightChild + ")");
    }
  }

  @Override
  public void notifyAboutCompletion() {
    this.complete = (this.leftChild != null && this.leftChild.checkCompletion()) && (this.rightChild != null && this.rightChild.checkCompletion());
    if (this.complete)
      this.getParent().notifyAboutCompletion();
  }

  @Override
  public void input(MathExpression exp) {
    if (leftChild == null) {
      this.insertChild(1, exp);
    } else {
      if (!this.leftChild.checkCompletion()) {
        this.leftChild.input(exp);
      } else {
        if (this.rightChild == null) {
          this.insertChild(2, exp);
        } else {
          this.rightChild.input(exp);
        }
      }
    }
  }

  @Override
  public boolean isConstant() {
    if (this.complete)
      return this.leftChild.isConstant() && this.rightChild.isConstant();
    return false;
  }

  @Override
  public void updateVariables() {
    this.hasVariable = (this.leftChild != null && this.leftChild.hasVariable) || (this.rightChild != null && this.rightChild.hasVariable);
    if (this.hasVariable && !this.getParent().hasVariable)
      this.getParent().updateVariables();
  }

  @Override
  public void insertChild(int num, MathExpression exp) {
    if (num == 1)
      this.leftChild = exp;
    else
      this.rightChild = exp;
    exp.setParent(this);
  }

  @Override
  public boolean checkCompletion() {
    if (this.complete)
      return true;
    if (this.rightChild == null || this.leftChild == null)
      return false;
    this.complete = this.leftChild.checkCompletion() && this.rightChild.checkCompletion();
    return this.complete;
  }

  @Override
  public boolean checkVariables() {
    if (this.hasVariable)
      return true;
    if (this.leftChild != null && this.rightChild != null) {
      this.hasVariable = this.leftChild.checkVariables() || this.rightChild.checkVariables();
      return this.hasVariable;
    }
    return true;
  }

  public MathExpression recompile() {
    if (this.complete) {
      if (!this.checkVariables())
        return new ConstantMathExpression(this.ifConstantThenValue());
      else {
          if (this.rightChild.ifConstantThenValue() != null && this.rightChild.ifConstantThenValue() == 1.0)
            return this.leftChild;
          if ((this.rightChild.ifConstantThenValue() != null && this.rightChild.ifConstantThenValue() == 0.0) || (this.leftChild.ifConstantThenValue() != null && this.leftChild.ifConstantThenValue() == 0.0))
            return new ConstantMathExpression(0.0);
           if (this.leftChild.ifConstantThenValue() != null && this.leftChild.ifConstantThenValue() == 1.0)
             return this.rightChild;
        }
    }
    return this;
  }

}
