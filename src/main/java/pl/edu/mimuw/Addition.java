package pl.edu.mimuw;

public class Addition extends TwoArgumentMathExpression {

  public Addition() {
    this.parent = null;
    this.representation = "+";
    this.rightChild = null;
    this.leftChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    if (this.checkVariables())
      return null;
    Double l = this.leftChild.ifConstantThenValue();
    Double r = this.rightChild.ifConstantThenValue();
    if (l != null && r != null)
      return l+r;
    return null;
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return this.leftChild.compute()+this.rightChild.compute();
  }

  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Addition res = new Addition();
      res.insertChild(1, this.leftChild.calcDx());
      res.insertChild(2, this.rightChild.calcDx());
      res.complete = res.checkCompletion();
      res.hasVariable = res.checkVariables();
      return res.recompile();
    } else
      return new ConstantMathExpression(0.0);
  }

  @Override
  public MathExpression recompile() {
    if (this.complete) {
      if (!this.checkVariables())
        return new ConstantMathExpression(this.ifConstantThenValue());
      else {
        if (!this.rightChild.checkVariables() && this.rightChild.ifConstantThenValue() == 0.0)
          return this.leftChild;
        else {
          if (!this.leftChild.checkVariables() && this.leftChild.ifConstantThenValue() == 0.0)
            return this.rightChild;
        }
      }
    }
    return this;
  }


}
