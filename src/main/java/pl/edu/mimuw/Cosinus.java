package pl.edu.mimuw;

public class Cosinus extends OneArgumentMathExpression {

  public Cosinus() {
    this.parent = null;
    this.representation = "cos";
    this.onlyChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    if (this.checkVariables())
      return null;
    Double resArg = this.onlyChild.ifConstantThenValue();
    if (resArg != null)
      return Math.cos(resArg);
    return null;
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return Math.cos(this.onlyChild.compute());
  }

  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Multiplication res = new Multiplication();
      Multiplication minussinus = new Multiplication();
      minussinus.insertChild(1, new ConstantMathExpression(-1.0));
      Sinus sinussy = new Sinus();
      sinussy.insertChild(1, this.onlyChild);
      minussinus.insertChild(2, sinussy);
      res.insertChild(1, minussinus);
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
