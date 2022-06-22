package pl.edu.mimuw.model;

import java.util.Objects;

public abstract class UnaryOperation implements Equation {
	protected final Equation child;
	
	public UnaryOperation(Equation child) {
		this.child = child;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		UnaryOperation tmp = (UnaryOperation) o;
		return this.child.equals(tmp.child);
	}
	
	public String toStringWithParenthesis() {
		return this.toString();
	}
}
