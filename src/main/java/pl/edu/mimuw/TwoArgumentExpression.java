package pl.edu.mimuw;

public abstract class TwoArgumentExpression extends Expression {
    protected Expression firstExpression;
    protected Expression secondExpression;
    protected final String sign;

    public TwoArgumentExpression(Expression firstExpression, Expression secondExpression,
            String sign) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.sign = sign;
    }

    @Override
    public Expression leftChild() {
        return this.firstExpression;
    }

    @Override
    public Expression rightChild() {
        return this.secondExpression;
    }

    public void setFirstExpression(Expression expression) {
        this.firstExpression = expression;
    }

    public void setSecondExpression(Expression expression) {
        this.secondExpression = expression;
    }

    public TwoArgumentExpression(String sign) {
        this.firstExpression = new PlaceHolder(this);
        firstExpression.setChildType(ChildType.Left);
        this.secondExpression = new PlaceHolder(this);
        secondExpression.setChildType(ChildType.Right);
        this.sign = sign;
    }

    @Override
    public void setLeftChild(Expression expression) {
        this.firstExpression = expression;
        this.firstExpression.setChildType(ChildType.Left);
    }

    @Override
    public void setRightChild(Expression expression) {
        this.secondExpression = expression;
        this.secondExpression.setChildType(ChildType.Right);
    }

    public String toString() {
        var s = new StringBuilder();
        s.append("(").append(firstExpression.toString()).append(")");
        s.append(" " + sign + " ");
        s.append("(").append(secondExpression.toString()).append(")");
        return s.toString();
    }
}