package pl.edu.mimuw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import pl.edu.mimuw.model.Equation;
import static pl.edu.mimuw.model.EquationFactory.*;

class ValueAtPointTest {
	@Test
	void sineTest() {
		Equation equation = sin(plus(times(constant(3.0), variable("x")), constant(1.0)));
		
		assertEquals(equation.valueAtPoint(2.1), 0.8504366206285648);
		assertEquals(equation.valueAtPoint(3.7), -0.44964746453459986);
		assertEquals(equation.valueAtPoint(6.9), 0.28705265132772506);
	}
	
	@Test
	void cosineTest() {
		Equation equation = cos(plus(times(constant(3.0), variable("x")), constant(1.0)));
		
		assertEquals(equation.valueAtPoint(2.1), 0.5260775173811045);
		assertEquals(equation.valueAtPoint(3.7), 0.8932061115093233);
		assertEquals(equation.valueAtPoint(6.9), -0.9579148059017166);
	}
	
	@Test
	void naturalLogarithmTest() {
		Equation equation = ln(plus(times(constant(3.0), variable("x")), constant(1.0)));
		
		assertEquals(equation.valueAtPoint(2.1), 1.9878743481543455);
		assertEquals(equation.valueAtPoint(3.7), 2.4932054526026954);
		assertEquals(equation.valueAtPoint(6.9), 3.077312260546414);
	}
}
