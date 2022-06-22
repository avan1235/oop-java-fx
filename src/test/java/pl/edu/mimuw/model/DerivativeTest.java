package pl.edu.mimuw.model;

import org.junit.jupiter.api.Test;

import pl.edu.mimuw.Addition;
import pl.edu.mimuw.Constant;
import pl.edu.mimuw.ExpressionBuilder;
import pl.edu.mimuw.Multiplication;
import pl.edu.mimuw.Sine;
import pl.edu.mimuw.Variable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DerivativeTest {
  @Test
  void constantDerivativeTest() {
    var x = new ExpressionBuilder();
    x.placeExpression(new Constant(187));
    for (double a = -5; a < 5; a = a + 1) {
      assert (0 == x.expression().derivative().value(a));
    }
  }

  @Test
  void polynomialDerivativeTest() {
    var x = new ExpressionBuilder();
    x.placeExpression(new Multiplication());
    x.leftChild();
    x.placeExpression(new Multiplication());
    x.leftChild();
    x.placeExpression(new Variable());
    x.childDone();
    x.rightChild();
    x.placeExpression(new Variable());
    x.childDone();
    x.childDone();
    x.rightChild();
    x.placeExpression(new Multiplication());
    x.leftChild();
    x.placeExpression(new Constant(3));
    x.childDone();
    x.rightChild();
    x.placeExpression(new Variable());
    x.childDone();
    x.childDone();
    for (double a = -5; a < 5; a = a + 1) {
      assert (9.0 * a * a == x.expression().derivative().value(a));
    }
  }

  @Test
  void complicatedDerivativeTest() {
    var x = new ExpressionBuilder();
    x.placeExpression(new Sine());
    x.child();
    x.placeExpression(new Addition());
    x.leftChild();
    x.placeExpression(new Multiplication());
    x.leftChild();
    x.placeExpression(new Variable());
    x.childDone();
    x.rightChild();
    x.placeExpression(new Variable());
    x.childDone();
    x.childDone();
    x.rightChild();
    x.placeExpression(new Constant(5));
    x.childDone();
    x.childDone();
    for (double a = -5; a < 5; a = a + 0.01) {
      assert (2 * a * Math.cos(a * a + 5) == x.expression().derivative().value(a));
    }
  }
}
