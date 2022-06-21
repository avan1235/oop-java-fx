package pl.edu.mimuw.model;

import org.junit.jupiter.api.Test;
import pl.edu.mimuw.model.argumentless.Variable;
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
    assertEquals("((x * ((x * 1.0) + (1.0 * x))) + (1.0 * (x * x)))" ,xCubed.derivative().getStringRepresentation());
    //Equivalent of 3x^2
  }

  @Test
  void derivativeSumTest() {
    final Expression x = new Variable();
    final Expression expr1 = new Plus(new Cos(x), new Sin(x));
    final Expression expr2 = new Plus(new Logarithm(x), x);
    final Expression expr = new Plus(expr1, expr2);
    final Expression derivative = expr.derivative();

    assertEquals("(((-1.0 * (1.0 * sin x)) + (1.0 * cos x)) + ((1.0 * (1.0 / x)) + 1.0))" ,derivative.getStringRepresentation());
    //Equivalent of -sin x + cos x + 1/x + 1
  }

  @Test
  void derivativeMultipleTest() {
    final Expression x = new Variable();
    assertEquals("x", x.getStringRepresentation());
    assertEquals("1.0", x.derivative().getStringRepresentation());
    assertEquals("0.0", x.derivative().derivative().getStringRepresentation());
    assertEquals("0.0", x.derivative().derivative().derivative().getStringRepresentation());
  }
}
