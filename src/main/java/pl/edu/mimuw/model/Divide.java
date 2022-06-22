package pl.edu.mimuw.model;

public class Divide extends BinaryOperation {
	public Divide(Equation left, Equation right) {
		super(left, right);
	}
	
	public double valueAtPoint(double x) {
		return this.left.valueAtPoint(x) / this.right.valueAtPoint(x);
	}
	
	public Equation derivative() {
		return new Divide(new Minus(new Times(this.left.derivative(), this.right),
		                            new Times(this.left, this.right.derivative())),
		                  new Times(this.right, this.right));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.left.toStringWithParenthesis());
		result.append(" / ");
		result.append(this.right.toStringWithParenthesis());
		
		return result.toString();
	}
}
