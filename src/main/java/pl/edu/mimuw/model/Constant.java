package pl.edu.mimuw.model;

import java.util.Objects;

public class Constant implements Equation {
	private final double value;
	
	public Constant(double value) {
		this.value = value;
	}
	
	public double valueAtPoint(double x) {
		return this.value;
	}
	
	public Equation derivative() {
		return new Constant(0.0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		Constant tmp = (Constant) o;
		return this.value == tmp.value;
	}
	
	public String toStringWithParenthesis() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.value);
		
		return result.toString();
	}
}
