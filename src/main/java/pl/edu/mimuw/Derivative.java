package pl.edu.mimuw;

public class Derivative extends OneArgumentMathExpression {

  public Derivative() {
    this.parent = null;
    this.representation = "dx";
    this.onlyChild = null;
    this.hasVariable = false;
  }

  @Override
  public Double ifConstantThenValue() {
    return null;
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return this.onlyChild.calcDx().compute();
  }

  @Override
  public MathExpression calcDx() {
    return this.onlyChild.calcDx().calcDx();
  }
}
