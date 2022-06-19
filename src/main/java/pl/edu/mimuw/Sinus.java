package pl.edu.mimuw;

public class Sinus extends OneArgumentMathExpression {

  public Sinus() {
    super();
    this.representation = "sin";
  }

  @Override
  public Double ifConstantThenValue() {
    if (this.checkVariables())
      return null;
    Double resArg = this.getOnlyChild().ifConstantThenValue();
    if (resArg != null)
      return Math.sin(resArg);
    return null;
  }

  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return Math.sin(this.getOnlyChild().compute());
  }

  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Cosinus cos = new Cosinus();
      cos.insertChild(1, this.getOnlyChild());
      Multiplication res = new Multiplication();
      res.insertChild(1, cos);
      res.insertChild(2, this.getOnlyChild().calcDx());
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
