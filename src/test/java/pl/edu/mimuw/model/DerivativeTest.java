package pl.edu.mimuw.model;

import org.junit.jupiter.api.Test;
import pl.edu.mimuw.model.binary.Divide;
import pl.edu.mimuw.model.binary.Plus;
import pl.edu.mimuw.model.binary.Times;
import pl.edu.mimuw.model.unary.Cos;
import pl.edu.mimuw.model.unary.Logarithm;
import pl.edu.mimuw.model.unary.Sin;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DerivativeTest {

  @Test
  void derivativeBasicTest() {
    final Expression xCubed = new Times(new Variable(), new Times(new Variable(), new Variable()));
    assertEquals(xCubed.derivative().getStringRepresentation(), "((x * ((x * 1.0) + (1.0 * x))) + (1.0 * (x * x))))");
    //Equivalent of 3x^2
  }

  @Test
  void derivativeSumTest() {
    final Expression x = new Variable();
    final Expression expr1 = new Plus(new Cos(x), new Sin(x));
    final Expression expr2 = new Plus(new Logarithm(x), x);
    final Expression expr = new Plus(expr1, expr2);
    final Expression derivative = expr.derivative();

    assertEquals(derivative.getStringRepresentation(), "(((-1.0 * (1.0 * sin x)) + (1.0 * cos x)) + ((1.0 * (1.0 / x)) + 1.0))");
    //Equivalent of -sin x + cos x + 1/x + 1
  }

  @Test
  void derivativeMultipleTest() {
    final Expression x = new Variable();
    assertEquals(x.getStringRepresentation(), "x");
    assertEquals(x.derivative().getStringRepresentation(), "1.0");
    assertEquals(x.derivative().derivative().getStringRepresentation(), "0.0");
    assertEquals(x.derivative().derivative().derivative().getStringRepresentation(), "0.0");
  }
}
