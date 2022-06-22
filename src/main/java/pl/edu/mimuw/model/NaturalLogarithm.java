package pl.edu.mimuw.model;

public class NaturalLogarithm extends UnaryOperation {
	public NaturalLogarithm(Equation child) {
		super(child);
	}
	
	public double valueAtPoint(double x) {
		return Math.log(this.child.valueAtPoint(x));
	}
	
	public Equation derivative() {
		return new Divide(this.child.derivative(), this.child);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("ln(");
		result.append(this.child.toString());
		result.append(")");
		
		return result.toString();
	}
}
