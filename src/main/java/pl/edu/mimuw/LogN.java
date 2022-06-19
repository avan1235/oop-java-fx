package pl.edu.mimuw;

public class LogN extends OneArgumentMathExpression {

  public LogN() {
    super();
    this.representation = "ln";
  }

  @Override
  public Double ifConstantThenValue() {
    Double acc = this.getOnlyChild().ifConstantThenValue();
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
    double acc = this.getOnlyChild().compute();
    if (acc <= 0)
      throw new IllegalArgumentException();
    return Math.log(this.getOnlyChild().compute());
  }


  @Override
  public MathExpression calcDx() {
    if (!this.checkVariables())
      return new ConstantMathExpression(0.0);
    else {
      Division res = new Division();
      res.insertChild(1, this.getOnlyChild());
      res.insertChild(2, this.getOnlyChild().calcDx());
      res.complete = res.checkCompletion();
      res.hasVariable = res.checkVariables();
      return res;
    }
  }

}
