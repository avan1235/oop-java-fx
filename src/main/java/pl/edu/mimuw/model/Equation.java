package pl.edu.mimuw.model;

public interface Equation {
	double valueAtPoint(double x);
	
	Equation derivative();
	
	String toStringWithParenthesis();
}
