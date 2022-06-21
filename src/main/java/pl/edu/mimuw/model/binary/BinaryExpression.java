package pl.edu.mimuw.model.binary;

import pl.edu.mimuw.model.Expression;

public abstract class BinaryExpression implements Expression {
  protected Expression leftChild;
  protected Expression rightChild;
  protected String operatorString;

  public BinaryExpression(Expression l, Expression r, String operator) {
    this.leftChild = l;
    this.rightChild = r;
    this.operatorString = operator;
  }

  @Override
  public double evaluateAtPoint(double point) {
    double leftValue = leftChild.evaluateAtPoint(point);
    double rightValue = rightChild.evaluateAtPoint(point);

    return this.mergeValues(leftValue, rightValue);
  }

  protected abstract double mergeValues(double leftValue, double rightValue);

  public abstract Expression derivative();

  public String getStringRepresentation() {
    String leftRepresentation = this.leftChild.getStringRepresentation();
    String rightRepresentation = this.rightChild.getStringRepresentation();

    return "(" + leftRepresentation + " " + operatorString + " " + rightRepresentation + ")";
  }

  @Override
  public void setLeftChild(Expression ex) {
    this.leftChild = ex;
  }

  @Override
  public void setRightChild(Expression ex) {
    this.rightChild = ex;
  }

  @Override
  public void setChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set child on binary expression.");
  }
}
