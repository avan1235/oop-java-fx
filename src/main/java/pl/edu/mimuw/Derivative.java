package pl.edu.mimuw;

public class Derivative extends OneArgumentMathExpression {

  public Derivative() {
    super();
    this.representation = "dx";
  }

  @Override
  public Double ifConstantThenValue() {
    return null;
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return this.getOnlyChild().calcDx().compute();
  }

  @Override
  public MathExpression calcDx() {
    return this.getOnlyChild().calcDx().calcDx();
  }
}
