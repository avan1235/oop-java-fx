package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public class Plus extends BinaryExpression {
  public Plus(Expression leftChild, Expression rightChild) {
    super(leftChild, rightChild);
  }

  @Override
  protected double mergeValues(double leftValue, double rightValue) {
    return leftValue + rightValue;
  }

  @Override
  public Expression derivative() {
    Expression leftDerivative = this.leftChild.derivative();
    Expression rightDerivative = this.rightChild.derivative();

    return new Plus(leftDerivative, rightDerivative);
  }

  @Override
  public String getOperatorString() {
    return "+";
  }
}
