package pl.edu.mimuw.model.argumentless;

import pl.edu.mimuw.model.Expression;

public abstract class ArgumentlessExpression implements Expression {
  public abstract double evaluateAtPoint(double point);
  public abstract Expression derivative();
  public abstract String getStringRepresentation();

  @Override
  public void setLeftChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set left child on expression without arguments.");
  }

  @Override
  public void setRightChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set right child on expression without arguments.");
  }

  @Override
  public void setChild(Expression ex) {
    throw new UnsupportedOperationException("Cannot set child on expression without arguments.");
  }
}
