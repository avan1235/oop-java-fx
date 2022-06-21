package pl.edu.mimuw.model;

public interface Expression {
  double evaluateAtPoint(double point);

  Expression derivative();

  String getStringRepresentation();

  void setLeftChild(Expression ex);
  void setRightChild(Expression ex);
  void setChild(Expression ex);
}
