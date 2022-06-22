package pl.edu.mimuw.model;

public class Times extends BinaryOperation {
	public Times(Equation left, Equation right) {
		super(left, right);
	}
	
	public double valueAtPoint(double x) {
		return this.left.valueAtPoint(x) * this.right.valueAtPoint(x);
	}
	
	public Equation derivative() {
		return new Plus(new Times(this.left.derivative(), this.right),
		                new Times(this.left, this.right.derivative()));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.left.toStringWithParenthesis());
		result.append(" * ");
		result.append(this.right.toStringWithParenthesis());
		
		return result.toString();
	}
}
