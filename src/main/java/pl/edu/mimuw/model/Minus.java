package pl.edu.mimuw.model;

public class Minus extends BinaryOperation {
	public Minus(Equation left, Equation right) {
		super(left, right);
	}
	
	public double valueAtPoint(double x) {
		return this.left.valueAtPoint(x) - this.right.valueAtPoint(x);
	}
	
	public Equation derivative() {
		return new Minus(this.left.derivative(), this.right.derivative());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.left.toStringWithParenthesis());
		result.append(" - ");
		result.append(this.right.toStringWithParenthesis());
		
		return result.toString();
	}
}
