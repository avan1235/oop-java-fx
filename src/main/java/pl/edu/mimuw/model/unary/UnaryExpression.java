package pl.edu.mimuw.model.unary;

import pl.edu.mimuw.model.Expression;

public abstract class UnaryExpression implements Expression {
  protected final Expression child;

  public UnaryExpression(Expression child) {
    this.child = child;
  }

  public abstract double evaluateAtPoint(double point);

  public abstract Expression derivative();

  public String getStringRepresentation() {
    String childRepresentation = this.child.getStringRepresentation();
    return this.getOperatorString() + " " + childRepresentation;
  }

  public abstract String getOperatorString();
}
