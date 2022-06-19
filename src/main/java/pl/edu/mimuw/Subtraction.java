package pl.edu.mimuw;

public class Subtraction extends TwoArgumentMathExpression {
  public Subtraction() {
    super();
    this.representation = "-";
  }

  @Override
  public Double ifConstantThenValue() {
    if (this.checkVariables())
      return null;
    Double l = this.getLeftChild().ifConstantThenValue();
    Double r = this.getRightChild().ifConstantThenValue();
    if (l != null && r != null)
      return l-r;
    return null;
  }




  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return this.getLeftChild().compute()-this.getRightChild().compute();
  }

  @Override
  public MathExpression calcDx() {
    if (this.checkVariables()) {
      Subtraction res = new Subtraction();
      res.insertChild(1, this.getLeftChild().calcDx());
      res.insertChild(2, this.getRightChild().calcDx());
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
        if (this.getRightChild().checkVariables() && this.getRightChild().ifConstantThenValue() == 0.0)
          return this.getLeftChild();
      }
    }
    return this;
  }


}
