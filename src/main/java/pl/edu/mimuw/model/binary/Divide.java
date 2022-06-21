package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public class Divide extends BinaryExpression {
  private static final String DIVIDE_OPERATOR = "/";

  public Divide(Expression leftChild, Expression rightChild) {
    super(leftChild, rightChild, DIVIDE_OPERATOR);
  }

  @Override
  protected double mergeValues(double leftValue, double rightValue) {
    return leftValue / rightValue;
  }

  @Override
  public Expression derivative() {
    Expression leftDerivative = this.leftChild.derivative();
    Expression rightDerivative = this.rightChild.derivative();

    Expression numerator = new Minus(new Times(leftDerivative, this.rightChild), new Times(this.leftChild, rightDerivative));

    return new Divide(numerator, new Times(this.rightChild, this.rightChild));
  }
}