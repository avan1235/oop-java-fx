package pl.edu.mimuw;

public class Multiplication extends TwoArgumentMathExpression {

  public Multiplication() {
    super();
    this.representation = "*";
  }

  @Override
  public Double ifConstantThenValue() {
    Double left = this.getLeftChild().ifConstantThenValue();
    Double right = this.getRightChild().ifConstantThenValue();
    if ((left != null && left == 0.0) || (right != null && right == 0.0))
      return 0.0;
    if (left != null && right != null) {
        return left * right;
    } else {
      return null;
    }
  }


  @Override
  public double compute() {
    if (this.isConstant())
      return this.ifConstantThenValue();
    return this.getLeftChild().compute()*this.getRightChild().compute();
  }

  public MathExpression calcDx() {
    if (this.checkVariables()) {
      MathExpression l = this.getLeftChild().recompile();
      MathExpression r = this.getRightChild().recompile();
      Multiplication ldxr = new Multiplication();
      ldxr.insertChild(1, l.calcDx().recompile());
      ldxr.insertChild(2, r);
      Multiplication lrdx = new Multiplication();
      lrdx.insertChild(1, l);
      lrdx.insertChild(2, r.calcDx().recompile());
      Addition res = new Addition();
      res.insertChild(1, ldxr.recompile());
      res.insertChild(2, lrdx.recompile());
      res.complete = res.checkCompletion();
      res.hasVariable = res.checkVariables();
      return res.recompile();
    } else
      return new ConstantMathExpression(0.0);
  }

  @Override
  public MathExpression recompile() {
    if (this.checkCompletion()) {
      if (!this.checkVariables())
        return new ConstantMathExpression(this.ifConstantThenValue());
      else {
        if (this.getRightChild().ifConstantThenValue() != null && this.getRightChild().ifConstantThenValue() == 1.0)
          return this.getLeftChild();
        if ((this.getRightChild().ifConstantThenValue() != null && this.getRightChild().ifConstantThenValue() == 0.0) || (this.getLeftChild().ifConstantThenValue() != null && this.getLeftChild().ifConstantThenValue() == 0.0))
          return new ConstantMathExpression(0.0);
        if (this.getLeftChild().ifConstantThenValue() != null && this.getLeftChild().ifConstantThenValue() == 1.0)
          return this.getRightChild();
      }
    }
    return this;
  }

}
