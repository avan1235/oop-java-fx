package pl.edu.mimuw;

public class ExpressionBuilder {
    private Expression builtExpression;
    private Expression currentNode;

    public ExpressionBuilder() {
        builtExpression = new PlaceHolder(new ExpressionFather());
        builtExpression.setChildType(ChildType.Only);
        currentNode = builtExpression;
        builtExpression = builtExpression.father();
    }

    public void leftChild() {
        currentNode.setLeftChild(new CurrentPlaceHolder(currentNode));
        currentNode = currentNode.leftChild();
    }

    public void rightChild() {
        currentNode.setRightChild(new CurrentPlaceHolder(currentNode));
        currentNode = currentNode.rightChild();
    }

    public void child() {
        currentNode.setChild(new CurrentPlaceHolder(currentNode));
        currentNode = currentNode.child();
    }

    public void childDone() {
        currentNode = currentNode.father();
    }

    public void placeExpression(Expression expression) {
        expression.setFather(currentNode.father);
        expression.setChildType(this.currentNode.childType);
        if (expression.childType == ChildType.Left) {
            expression.father().setLeftChild(expression);
        }
        if (expression.childType == ChildType.Right) {
            expression.father().setRightChild(expression);
        }
        if (expression.childType == ChildType.Only) {
            expression.father().setChild(expression);
        }
        currentNode = expression;
    }

    public Expression expression() {
        return builtExpression;
    }
}