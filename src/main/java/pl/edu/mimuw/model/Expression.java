package pl.edu.mimuw.model;

public interface Expression {
  double evaluateAtPoint(double point);

  Expression derivative();

  String getStringRepresentation();
}
