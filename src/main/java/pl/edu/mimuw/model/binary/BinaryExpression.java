package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public abstract class BinaryExpression implements Expression {
  protected final Expression leftChild;
  protected final Expression rightChild;

  public BinaryExpression(Expression l, Expression r) {
    this.leftChild = l;
    this.rightChild = r;
  }

  @Override
  public double evaluateAtPoint(double point) {
    double leftValue = leftChild.evaluateAtPoint(point);
    double rightValue = leftChild.evaluateAtPoint(point);

    return this.mergeValues(leftValue, rightValue);
  }

  protected abstract double mergeValues(double leftValue, double rightValue);

  public abstract Expression derivative();

  public String getStringRepresentation() {
    String leftRepresentation = this.leftChild.getStringRepresentation();
    String rightRepresentation = this.rightChild.getStringRepresentation();

    return "(" + leftRepresentation + " " + getOperatorString() + " " + rightRepresentation + ")";
  }

  public abstract String getOperatorString();
}
