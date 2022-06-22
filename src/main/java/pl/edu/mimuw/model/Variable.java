package pl.edu.mimuw.model;

import java.util.Objects;

public class Variable implements Equation {
	private final String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public double valueAtPoint(double x) {
		return x;
	}
	
	public Equation derivative() {
		return new Constant(1.0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		
		Variable tmp = (Variable) o;
		return this.name == tmp.name;
	}
	
	public String toStringWithParenthesis() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(this.name);
		
		return result.toString();
	}
}
