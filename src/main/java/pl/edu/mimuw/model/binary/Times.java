package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public class Times extends BinaryExpression {
  public Times(Expression leftChild, Expression rightChild) {
    super(leftChild, rightChild);
  }

  @Override
  protected double mergeValues(double leftValue, double rightValue) {
    return leftValue * rightValue;
  }

  @Override
  public Expression derivative() {
    Expression leftDerivative = this.leftChild.derivative();
    Expression rightDerivative = this.rightChild.derivative();

    return new Plus(new Times(this.leftChild, rightDerivative), new Times(leftDerivative, this.rightChild));
  }

  @Override
  public String getOperatorString() {
    return "*";
  }
}
