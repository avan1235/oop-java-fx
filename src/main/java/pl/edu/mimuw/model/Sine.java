package pl.edu.mimuw.model;

public class Sine extends UnaryOperation {
	public Sine(Equation child) {
		super(child);
	}
	
	public double valueAtPoint(double x) {
		return Math.sin(this.child.valueAtPoint(x));
	}
	
	public Equation derivative() {
		return new Times(this.child.derivative(), new Cosine(this.child));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("sin(");
		result.append(this.child.toString());
		result.append(")");
		
		return result.toString();
	}
}
