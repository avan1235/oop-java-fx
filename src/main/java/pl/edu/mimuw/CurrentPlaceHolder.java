package pl.edu.mimuw;

public class CurrentPlaceHolder extends ZeroArgumentExpression {

    public CurrentPlaceHolder(Expression father) {
        this.father = father;
    }

    public Expression derivative() {
        return this;
    }

    public double value(double x) {
        return 0;
    }

    public String toString() {
        return ">.<";
    }
}