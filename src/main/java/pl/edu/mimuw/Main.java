package pl.edu.mimuw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Main extends Application {

  private static final double BUTTON_MIN_WIDTH = 72;
  private static final double BUTTON_MIN_HEIGHT = 72;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("TinyMath");
    final Scene scene = createSampleScene(3);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private static Scene createSampleScene(int size) {
    final TextArea textArea = new TextArea("Let's try me");
    textArea.setEditable(false);
    textArea.setMaxSize(size * BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    final TextField textField = new TextField();
    textField.setPromptText("Enter value of x");
    final VBox buttons = createButtons(size, textArea::setText, textField);
    final VBox root = new VBox(textArea, buttons, textField);
    return new Scene(root);
  }

  private static VBox createButtons(int size, Consumer<String> onButtonClicked, TextField textField) {
    final HBox[] rows = new HBox[6];
    var x = new ExpressionBuilder();
    for (int row = 0; row < 2; row++) {
      final Button[] buttons = new Button[5];
      for (int col = 0; col < 5; col++) {
        int index = col + row * 5;
        final Button button = new Button(String.valueOf(index));
        buttons[col] = button;
        buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        buttons[col].setOnMouseClicked(event -> {
          x.placeExpression(new Constant(index));
          onButtonClicked.accept(x.expression().toString());
        });
      }
      rows[row] = new HBox(buttons);
    }
    Button[] lastRow = new Button[5];
    Button button = new Button("+");
    lastRow[0] = button;
    lastRow[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[0].setOnMouseClicked(event -> {
      x.placeExpression(new Addition());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("-");
    lastRow[1] = button;
    lastRow[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[1].setOnMouseClicked(event -> {
      x.placeExpression(new Substraction());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("*");
    lastRow[2] = button;
    lastRow[2].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[2].setOnMouseClicked(event -> {
      x.placeExpression(new Multiplication());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("/");
    lastRow[3] = button;
    lastRow[3].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[3].setOnMouseClicked(event -> {
      x.placeExpression(new Division());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("x");
    lastRow[4] = button;
    lastRow[4].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[4].setOnMouseClicked(event -> {
      x.placeExpression(new Variable());
      onButtonClicked.accept(x.expression().toString());
    });
    rows[2] = new HBox(lastRow);

    lastRow = new Button[4];
    button = new Button("sin");
    lastRow[0] = button;
    lastRow[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[0].setOnMouseClicked(event -> {
      x.placeExpression(new Sine());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("cos");
    lastRow[1] = button;
    lastRow[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[1].setOnMouseClicked(event -> {
      x.placeExpression(new Cosine());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("ln");
    lastRow[2] = button;
    lastRow[2].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[2].setOnMouseClicked(event -> {
      x.placeExpression(new Ln());
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("d/dx");
    lastRow[3] = button;
    lastRow[3].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[3].setOnMouseClicked(event -> {
      x.placeExpression(new Derivative());
      onButtonClicked.accept(x.expression().toString());
    });
    rows[3] = new HBox(lastRow);

    lastRow = new Button[4];
    button = new Button("Left Child");
    lastRow[0] = button;
    lastRow[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[0].setOnMouseClicked(event -> {
      x.leftChild();
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("Only Child");
    lastRow[1] = button;
    lastRow[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[1].setOnMouseClicked(event -> {
      x.child();
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("Right Child");
    lastRow[2] = button;
    lastRow[2].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[2].setOnMouseClicked(event -> {
      x.rightChild();
      onButtonClicked.accept(x.expression().toString());
    });
    button = new Button("Child Done");
    lastRow[3] = button;
    lastRow[3].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[3].setOnMouseClicked(event -> {
      x.childDone();
      onButtonClicked.accept(x.expression().toString());
    });
    rows[4] = new HBox(lastRow);

    lastRow = new Button[2];
    button = new Button("Show Derivative");
    lastRow[0] = button;
    lastRow[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[0].setOnMouseClicked(event -> {
      onButtonClicked.accept(x.expression().derivative().toString());
    });
    button = new Button("Calculate for given x");
    lastRow[1] = button;
    lastRow[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
    lastRow[1].setOnMouseClicked(event -> {
      onButtonClicked.accept(String.valueOf(x.expression().value(Double.valueOf(textField.getText()))));
    });
    rows[5] = new HBox(lastRow);
    return new VBox(rows);
  }
}
