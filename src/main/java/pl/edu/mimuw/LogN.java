package pl.edu.mimuw;

public class LogN extends OneArgumentMathExpression {

  public LogN() {
    this.parent = null;
    this.representation = "ln";
    this.onlyChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    Double acc = this.onlyChild.ifConstantThenValue();
    if (acc != null) {
      if (acc <= 0) {
        MainExpression.corrupted = true;
        return 0.0;
      }
      return Math.log(acc);
    } else {
      return null;
    }
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    double acc = this.onlyChild.compute();
    if (acc <= 0)
      throw new IllegalArgumentException();
    return Math.log(this.onlyChild.compute());
  }


  @Override
  public MathExpression calcDx() {
    if (!this.checkVariables())
      return new ConstantMathExpression(0.0);
    else {
      Division res = new Division();
      res.insertChild(1, this.onlyChild);
      res.insertChild(2, this.onlyChild.calcDx());
      res.complete = res.checkCompletion();
      res.hasVariable = res.checkVariables();
      return res;
    }
  }

}
