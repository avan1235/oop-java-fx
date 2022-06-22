package pl.edu.mimuw.model;

public class Cosine extends UnaryOperation {
	public Cosine(Equation child) {
		super(child);
	}
	
	public double valueAtPoint(double x) {
		return Math.cos(this.child.valueAtPoint(x));
	}
	
	public Equation derivative() {
		return new Minus(new Constant(0.0), new Times(this.child.derivative(),
		                                              new Sine(this.child)));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("cos(");
		result.append(this.child.toString());
		result.append(")");
		
		return result.toString();
	}
}
