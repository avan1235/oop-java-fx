package pl.edu.mimuw;

public class MainExpression extends MathExpression {
  public MathExpression expression;
  public static boolean corrupted = false;
  public static boolean equalsClicked = false;

  public MainExpression() {
    this.complete = false;
    this.parent = null;
    this.representation = "";
    this.hasVariable = false;
    this.expression = null;
  }

  @Override
  public String toString() {
    if (this.expression == null)
      return "begin input";
    if (corrupted) {
      return "YOU HAVE ANGERED THE GODS OF MATHEMATICS\n REPENT AT ONCE!!!!!";
    } else {
      if (this.checkCompletion())
        return "(C)  " + this.expression;
      return this.expression.toString();
    }
  }

  @Override
  public double compute() {
    return this.expression.compute();
  }

  public void clear() {
    this.expression = null;
    this.complete = false;
    this.hasVariable = false;
    equalsClicked = false;
    corrupted = false;
  }


  @Override
  public void input(MathExpression exp) {
    equalsClicked = false;
    if (this.expression == null || !this.expression.checkCompletion()) {
      if (this.expression == null) {
        this.expression = exp;
        exp.parent = this;
        if (exp.complete)
          this.complete = true;
      } else {
        this.expression.input(exp);
      }
    } else {
        if (exp.isConstant() && this.checkVariables()) {
          Variable.assignedValue = exp.ifConstantThenValue();
        } else {
          if (exp.isOneArg()) {
            ((OneArgumentMathExpression) exp).onlyChild = this.expression;
            exp.complete = this.expression.complete;
            this.expression.parent = exp;
            this.expression = exp;
          } else {
            this.clear();
            this.input(exp);
          }
        }
    }
  }

  public void takeDerivative() {
    if (this.expression.checkCompletion()) {
      this.expression = this.expression.calcDx().recompile();
      this.expression.parent = this;
    } else {
      this.input(new Derivative());
    }
  }

  @Override
  public void notifyAboutCompletion() {
    this.complete = this.expression.checkCompletion();
  }

  @Override
  public void updateVariables() {
    this.hasVariable = this.expression.checkVariables();
  }


  public String equalsClicked() {
    if (equalsClicked) {
      this.clear();
      return this.toString();
    } else {
      equalsClicked = true;
      String res = this.toString();
      res += "\n= ";
      if (this.checkCompletion() && !this.checkVariables()) {
        this.expression = this.expression.recompile();
        this.expression.parent = this;
        res += this.compute();
        if (corrupted) {
          res = this.toString();
          this.clear();
        }
      } else {
        if (!this.expression.checkCompletion()) {
          res = "why would you even click this now?\nlet's start from the beginning";
          this.clear();
        } else {
          if (Variable.assignedValue == null) {
            res = "to compute enter the value of x";
            equalsClicked = false;
          } else
            res += this.compute();
        }
      }
      return res;
    }
  }

  @Override
  public boolean checkCompletion() {
    this.complete = (this.expression != null && this.expression.checkCompletion());
    return this.complete;
  }

  @Override
  public boolean checkVariables() {
    this.hasVariable = (this.expression == null || this.expression.checkVariables());
    return this.hasVariable;
  }
}
