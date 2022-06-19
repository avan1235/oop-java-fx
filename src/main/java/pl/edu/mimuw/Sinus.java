package pl.edu.mimuw;

public class Sinus extends OneArgumentMathExpression {

  public Sinus() {
    this.parent = null;
    this.representation = "sin";
    this.onlyChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    if (this.checkVariables())
      return null;
    Double resArg = this.onlyChild.ifConstantThenValue();
    if (resArg != null)
      return Math.sin(resArg);
    return null;
  }

  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return Math.sin(this.onlyChild.compute());
  }

  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Cosinus cos = new Cosinus();
      cos.insertChild(1, this.onlyChild);
      Multiplication res = new Multiplication();
      res.insertChild(1, cos);
      res.insertChild(2, this.onlyChild.calcDx());
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
    }
    return this;
  }

}
