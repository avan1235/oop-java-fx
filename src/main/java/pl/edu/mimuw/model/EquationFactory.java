package pl.edu.mimuw.model;

public class EquationFactory {
	private EquationFactory() {}
	
	public static Equation plus(Equation left, Equation right) {
		return new Plus(left, right);
	}
	
	public static Equation minus(Equation left, Equation right) {
		return new Minus(left, right);
	}
	
	public static Equation times(Equation left, Equation right) {
		return new Times(left, right);
	}
	
	public static Equation divide(Equation left, Equation right) {
		return new Divide(left, right);
	}
	
	public static Equation cos(Equation child) {
		return new Cosine(child);
	}
	
	public static Equation sin(Equation child) {
		return new Sine(child);
	}
	
	public static Equation ln(Equation child) {
		return new NaturalLogarithm(child);
	}
	
	public static Equation constant(double value) {
		return new Constant(value);
	}
	
	public static Equation variable(String name) {
		return new Variable(name);
	}
}
