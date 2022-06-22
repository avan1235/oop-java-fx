package pl.edu.mimuw;

public abstract class OneArgumentExpression extends Expression {
    protected Expression expression;
    protected final String name;

    @Override
    public Expression child() {
        return this.expression;
    }

    @Override
    public void setChild(Expression expression) {
        this.expression = expression;
        this.expression.setChildType(ChildType.Only);
    }

    public OneArgumentExpression(Expression expression, String name) {
        this.expression = expression;
        this.expression.setChildType(ChildType.Only);
        this.name = name;
    }

    public OneArgumentExpression(String name) {
        this.expression = new PlaceHolder(this);
        this.expression.setChildType(ChildType.Only);
        this.name = name;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public String toString() {
        var s = new StringBuilder();
        s.append(name + "(");
        s.append(expression);
        s.append(")");
        return s.toString();
    }
}