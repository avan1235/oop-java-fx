package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public class Minus extends BinaryExpression {
  private static final String MINUS_OPERATOR = "-";

  public Minus(Expression leftChild, Expression rightChild) {
    super(leftChild, rightChild, MINUS_OPERATOR);
  }

  @Override
  protected double mergeValues(double leftValue, double rightValue) {
    return leftValue - rightValue;
  }

  @Override
  public Expression derivative() {
    Expression leftDerivative = this.leftChild.derivative();
    Expression rightDerivative = this.rightChild.derivative();

    return new Minus(leftDerivative, rightDerivative);
  }
}
