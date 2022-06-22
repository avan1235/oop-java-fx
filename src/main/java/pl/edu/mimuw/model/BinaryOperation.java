package pl.edu.mimuw.model;

import java.util.Objects;

public abstract class BinaryOperation implements Equation {
	protected final Equation left;
	protected final Equation right;
	
	public BinaryOperation(Equation left, Equation right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		BinaryOperation tmp = (BinaryOperation) o;
		return this.left.equals(tmp.left) && this.right.equals(tmp.right);
	}
	
	public String toStringWithParenthesis() {
		StringBuilder result = new StringBuilder();
		
		result.append("(");
		result.append(this.toString());
		result.append(")");
		
		return result.toString();
	}
}
