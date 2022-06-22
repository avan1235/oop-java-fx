package pl.edu.mimuw;

public abstract class Expression {
    protected Expression father;
    protected ChildType childType;

    public void setFather(Expression father) {
        this.father = father;
    }

    public ChildType childType() {
        return childType;
    }

    public void setChildType(ChildType childType) {
        this.childType = childType;
    }

    public Expression father() {
        return father;
    }

    public abstract Expression derivative();

    public Expression rightChild() {
        return this;
    }

    public Expression leftChild() {
        return this;
    }

    public Expression child() {
        return this;
    }

    public void setChild(Expression expression) {

    }

    public void setRightChild(Expression expression) {

    }

    public void setLeftChild(Expression expression) {

    }

    public abstract double value(double x);
}