package pl.edu.mimuw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import pl.edu.mimuw.model.Equation;
import static pl.edu.mimuw.model.EquationFactory.*;

class DerivativeTest {
	@Test
	void sineTest() {
		Equation equation = sin(plus(times(constant(3.0), variable("x")), constant(1.0)));
		Equation expectedResult = times(plus(plus(times(constant(0.0), variable("x")),
		                                          times(constant(3.0), constant(1.0))),
		                                     constant(0.0)),
		                                cos(plus(times(constant(3.0), variable("x")),
		                                         constant(1.0))));
		
		assertEquals(equation.derivative(), expectedResult);
	}
	
	@Test
	void cosineTest() {
		Equation equation = cos(plus(times(constant(3.0), variable("x")), constant(1.0)));
		Equation expectedResult = minus(constant(0.0),
		                                times(plus(plus(times(constant(0.0), variable("x")),
		                                                times(constant(3.0), constant(1.0))),
		                                           constant(0.0)),
		                                      sin(plus(times(constant(3.0), variable("x")),
		                                               constant(1.0)))));
		
		assertEquals(equation.derivative(), expectedResult);
	}
	
	@Test
	void naturalLogarithmTest() {
		Equation equation = ln(plus(times(constant(3.0), variable("x")), constant(1.0)));
		Equation expectedResult = divide(plus(plus(times(constant(0.0), variable("x")),
		                                           times(constant(3.0), constant(1.0))),
		                                      constant(0.0)),
		                                 plus(times(constant(3.0), variable("x")),
		                                      constant(1.0)));
		
		assertEquals(equation.derivative(), expectedResult);
	}
}
