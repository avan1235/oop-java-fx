package pl.edu.mimuw;

public class Division extends TwoArgumentMathExpression{

  public Division() {
    this.parent = null;
    this.representation = "/";
    this.rightChild = null;
    this.leftChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    Double left = this.leftChild.ifConstantThenValue();
    Double right = this.rightChild.ifConstantThenValue();
    if (left != null && right != null) {
      if (right == 0.0) {
        MainExpression.corrupted = true;
        return 0.0;
      }
      if (left == 0.0)
        return 0.0;
      return left/right;
    } else {
      return null;
    }
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    double left = this.leftChild.compute();
    double right = this.rightChild.compute();
    if (right == 0)
      throw new IllegalArgumentException();
    return left/right;
  }


  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Division res = new Division();
      Multiplication denominator = new Multiplication();
      denominator.insertChild(1, this.rightChild);
      denominator.insertChild(2, this.rightChild);
      res.insertChild(2, denominator.recompile());
      Addition addition = new Addition();
      Multiplication fdg = new Multiplication();
      Multiplication fgd = new Multiplication();
      fdg.insertChild(1, this.leftChild.calcDx());
      fdg.insertChild(2, this.rightChild);
      fgd.insertChild(1, this.leftChild);
      fgd.insertChild(2, this.rightChild.calcDx());
      addition.insertChild(1, fdg.recompile());
      addition.insertChild(2, fgd.recompile());
      res.insertChild(1, addition.recompile());
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
        if (!this.rightChild.checkVariables() && this.rightChild.ifConstantThenValue() == 1.0)
          return this.leftChild;
        else {
          if (!this.leftChild.checkVariables() && this.leftChild.ifConstantThenValue() == 0.0)
            return new ConstantMathExpression(0.0);
        }
      }
    }
    return this;
  }



}
