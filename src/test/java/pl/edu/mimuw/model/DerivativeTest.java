package pl.edu.mimuw.model;

import org.junit.jupiter.api.Test;
import pl.edu.mimuw.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DerivativeTest {

  @Test
  void sinussyTest() {
    var test = new Sinus();
    test.insertChild(1, new Variable());
    var der1 = new Derivative();
    der1.insertChild(1, test);
    var der2 = new Derivative();
    der2.insertChild(1, der1);
    var der3 = new Derivative();
    der3.insertChild(1, der2);
    Variable.assignedValue = 0.0;
    Double actual = der3.compute();
    Double expected = -1.0;
    assertEquals(actual, expected);
  }

  @Test
  void polynomialTest() {
    var p1 = new Addition();
    p1.insertChild(1, new Variable());
    p1.insertChild(2, new ConstantMathExpression(1.0));
    var p2 = new Addition();
    p2.insertChild(1, new Variable());
    p2.insertChild(2, new ConstantMathExpression(2.0));
    var p3 = new Addition();
    p3.insertChild(1, new Variable());
    p3.insertChild(2, new ConstantMathExpression(3.0));
    var p4 = new Addition();
    p4.insertChild(1, new Variable());
    p4.insertChild(2, new ConstantMathExpression(4.0));
    var lm = new Multiplication();
    lm.insertChild(1, p1);
    lm.insertChild(2, p2);
    var rm = new Multiplication();
    rm.insertChild(1, p3);
    rm.insertChild(2, p4);
    var test = new Multiplication();
    test.insertChild(1, lm);
    test.insertChild(2, rm);
    var testDx = new Derivative();
    testDx.insertChild(1, test);

    Variable.assignedValue = 0.0;
    Double actual = testDx.compute();
    Double expected = 50.0;
    assertEquals(actual, expected);

    Variable.assignedValue = 1.0;
    actual = testDx.compute();
    expected = 154.0;
    assertEquals(actual, expected);

    Variable.assignedValue = -1.0;
    actual = testDx.compute();
    expected = 6.0;
    assertEquals(actual, expected);
  }

  @Test
  void logTest() {
    var log = new LogN();
    var dx = new Derivative();
    dx.insertChild(1, log);
    var sin = new Sinus();
    log.insertChild(1, sin);
    sin.insertChild(1, new Variable());

    Variable.assignedValue = 0.0;
    Double actual = dx.compute();
    Double expected = 0.0;
    assertEquals(actual, expected);
  }


}
