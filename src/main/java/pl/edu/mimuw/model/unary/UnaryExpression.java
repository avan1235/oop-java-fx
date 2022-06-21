package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.Expression;

public abstract class UnaryExpression implements Expression {
  protected Expression child;
  protected String operatorString;

  public UnaryExpression(Expression child, String operator) {
    this.child = child;
    this.operatorString = operator;
  }

  public abstract double evaluateAtPoint(double point);

  public abstract Expression derivative();

  public String getStringRepresentation() {
    String childRepresentation = this.child.getStringRepresentation();
    return operatorString + " " + childRepresentation;
  }

  @Override
  public void setLeftChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set left child on unary expression.");
  }

  @Override
  public void setRightChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set right child on unary expression.");
  }

  @Override
  public void setChild(Expression ex) {
    this.child = ex;
  }
}
