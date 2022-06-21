package pl.edu.mimuw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import pl.edu.mimuw.model.Expression;
import pl.edu.mimuw.model.argumentless.Constant;
import pl.edu.mimuw.model.argumentless.Placeholder;
import pl.edu.mimuw.model.binary.Divide;
import pl.edu.mimuw.model.binary.Minus;
import pl.edu.mimuw.model.binary.Plus;
import pl.edu.mimuw.model.binary.Times;
import pl.edu.mimuw.model.unary.Cos;
import pl.edu.mimuw.model.unary.Logarithm;
import pl.edu.mimuw.model.unary.Sin;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
  private static final double BUTTON_MIN_WIDTH = 72;
  private static final double BUTTON_MIN_HEIGHT = 72;
  private static final int BUTTON_COLUMNS = 5;
  private static final int BUTTON_ROWS = 5;
  private static final String infoText = "Usage:\n" +
    "Select an expression\n" +
    "Build child expression\n" +
    "When done, click LEFT DONE, CHILD DONE or RIGHT DONE\n" +
    "to connect new expression to the one level higher.";

  private static final List<Expression> expressionStack = new ArrayList<>();

  public static void main(String[] args) {
    Expression test = new Plus(new Constant(1.0), new Constant(2.0));
    System.out.println(test.evaluateAtPoint(0));

    launch(args);
  }

  private static Scene createScene() {
    final TextArea textArea = new TextArea(infoText);
    textArea.setEditable(false);
    textArea.setMaxSize(BUTTON_COLUMNS * BUTTON_MIN_WIDTH, 10 * BUTTON_MIN_HEIGHT);

    final Label errorLabel = new Label("Info:");
    errorLabel.setMinSize(2 * BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    final TextArea errorArea = new TextArea();
    errorArea.setEditable(false);
    errorArea.setMaxSize(3 * BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    final VBox errorBox = new VBox(new HBox(errorLabel, errorArea));

    final VBox buttons = createButtons(textArea, errorArea);

    final VBox root = new VBox(textArea, errorBox, buttons);
    return new Scene(root);
  }

  private static VBox createButtons(TextArea textArea, TextArea errorArea) {
    final HBox[] rows = new HBox[BUTTON_ROWS];

    rows[0] = createUnaryOperationButtons(textArea, errorArea);
    rows[1] = createBinaryOperationButtons(textArea);
    rows[2] = createDigitsButtons(0, textArea);
    rows[3] = createDigitsButtons(5, textArea);
    rows[4] = createUtilButtons(textArea, errorArea);

    return new VBox(rows);
  }

  private static HBox createUnaryOperationButtons(TextArea textArea, TextArea errorArea) {
    final Button[] buttons = new Button[4];

    Button button = new Button("sin");
    buttons[0] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Sin(new Placeholder()));
      updateText(textArea);
    });

    button = new Button("cos");
    buttons[1] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Cos(new Placeholder()));
      updateText(textArea);
    });

    button = new Button("ln");
    buttons[2] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Logarithm(new Placeholder()));
      updateText(textArea);
    });

    button = new Button("=");
    buttons[3] = button;
    button.setStyle("-fx-background-color: #eab676; ");
    button.setOnMouseClicked(event -> {
      if(expressionStack.size() == 0) {
        return;
      }

      try {
        double value = expressionStack.get(0).evaluateAtPoint(0);
        errorArea.setText("Value of expression: " + value);
      } catch (IllegalStateException e) {
        errorArea.setText("Before evaluating fill in all placeholders.");
      }
    });

    for(int i = 0; i < 4; i++) {
      buttons[i].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    }

    return new HBox(buttons);
  }

  private static HBox createBinaryOperationButtons(TextArea textArea) {
    final Button[] buttons = new Button[4];

    Button button = new Button("+");
    buttons[0] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Plus(new Placeholder(), new Placeholder()));
      updateText(textArea);
    });

    button = new Button("-");
    buttons[1] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Minus(new Placeholder(), new Placeholder()));
      updateText(textArea);
    });

    button = new Button("*");
    buttons[2] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Times(new Placeholder(), new Placeholder()));
      updateText(textArea);
    });

    button = new Button("/");
    buttons[3] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.add(new Divide(new Placeholder(), new Placeholder()));
      updateText(textArea);
    });

    for(int i = 0; i < 4; i++) {
      buttons[i].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    }

    return new HBox(buttons);
  }

  private static HBox createDigitsButtons(int startingDigit, TextArea textArea) {
    final Button[] buttons = new Button[BUTTON_COLUMNS];

    for(int i = startingDigit; i < startingDigit + BUTTON_COLUMNS; i++) {
      Button button = new Button(Integer.toString(i));
      buttons[i - startingDigit] = button;
      final double constantValue = i;
      button.setOnMouseClicked(event -> {
        expressionStack.add(new Constant(constantValue));
        updateText(textArea);
      });

      button.setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    }

    return new HBox(buttons);
  }

  private static HBox createUtilButtons(TextArea textArea, TextArea errorArea) {
    final Button[] buttons = new Button[BUTTON_COLUMNS];

    Button button = new Button("LEFT\nDONE");
    buttons[0] = button;
    button.setOnMouseClicked(event -> {
      if(expressionStack.size() < 2) {
        errorArea.setText("There are no expressions to connect");
        return;
      }

      try {
        Expression parent = expressionStack.get(expressionStack.size() - 2);
        Expression child = expressionStack.get(expressionStack.size() - 1);

        parent.setLeftChild(child);
        expressionStack.remove(expressionStack.size() - 1);
        updateText(textArea);
      } catch (UnsupportedOperationException e) {
        errorArea.setText(e.getMessage());
      }
    });

    button = new Button("CHILD\nDONE");
    buttons[1] = button;
    button.setOnMouseClicked(event -> {
      if(expressionStack.size() < 2) {
        errorArea.setText("There are no expressions to connect");
        return;
      }

      try {
        Expression parent = expressionStack.get(expressionStack.size() - 2);
        Expression child = expressionStack.get(expressionStack.size() - 1);

        parent.setChild(child);
        expressionStack.remove(expressionStack.size() - 1);
        updateText(textArea);
      } catch (UnsupportedOperationException e) {
        errorArea.setText(e.getMessage());
      }
    });

    button = new Button("RIGHT\nDONE");
    buttons[2] = button;
    button.setOnMouseClicked(event -> {
      if(expressionStack.size() < 2) {
        errorArea.setText("There are no expressions to connect");
        return;
      }

      try {
        Expression parent = expressionStack.get(expressionStack.size() - 2);
        Expression child = expressionStack.get(expressionStack.size() - 1);

        parent.setRightChild(child);
        expressionStack.remove(expressionStack.size() - 1);
        updateText(textArea);
      } catch (UnsupportedOperationException e) {
        errorArea.setText(e.getMessage());
      }
    });

    button = new Button("CLEAR\nLAST");
    buttons[3] = button;
    button.setOnMouseClicked(event -> {
      if(expressionStack.size() > 1) {
        expressionStack.remove(expressionStack.size() - 1);
        updateText(textArea);
      }
    });

    button = new Button("CLEAR\nALL");
    buttons[4] = button;
    button.setOnMouseClicked(event -> {
      expressionStack.clear();
      updateText(textArea);
    });

    for(int i = 0; i < BUTTON_COLUMNS; i++) {
      buttons[i].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    }

    return new HBox(buttons);
  }

  private static void updateText(TextArea textArea) {
    var sb = new StringBuilder();

    for(Expression expr : expressionStack) {
      sb.append(expr.getStringRepresentation()).append("\n");
    }

    textArea.setText(sb.toString());
  }


  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("TinyMath");
    final Scene scene = createScene();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
