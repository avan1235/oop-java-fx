package pl.edu.mimuw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Main extends Application {

  private static final double BUTTON_MIN_WIDTH = 72;
  private static final double BUTTON_MIN_HEIGHT = 72;
  public static MainExpression expression = new MainExpression();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("TinyMath");
    final Scene scene = createSampleScene(5);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private static Scene createSampleScene(int size) {
    final TextArea textArea = new TextArea("begin input");
    textArea.setEditable(false);
    textArea.setMaxSize(size * BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);

    final VBox buttons = createSampleButtons(size, textArea::setText);
    final VBox root = new VBox(textArea, buttons);

    return new Scene(root);
  }

  private static VBox createSampleButtons(int size, Consumer<String> onButtonClicked) {
    final HBox[] rows = new HBox[size-1];

    final Button[] buttons3 = new Button[size];
    {
      final Button sin = new Button("sin");
      buttons3[0] = sin;
      buttons3[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons3[0].setOnMouseClicked(event -> {
        expression.input(new Sinus());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button cos = new Button("cos");
      buttons3[1] = cos;
      buttons3[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons3[1].setOnMouseClicked(event -> {
        expression.input(new Cosinus());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button ln = new Button("ln");
      buttons3[2] = ln;
      buttons3[2].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons3[2].setOnMouseClicked(event -> {
        expression.input(new LogN());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button dx = new Button("dx");
      buttons3[3] = dx;
      buttons3[3].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons3[3].setOnMouseClicked(event -> {
        expression.takeDerivative();
        onButtonClicked.accept(""+expression.toString());
      });

      final Button x = new Button("x");
      buttons3[4] = x;
      buttons3[4].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons3[4].setOnMouseClicked(event -> {
        expression.input(new Variable());
        onButtonClicked.accept(""+expression.toString());
      });

      rows[3] = new HBox(buttons3);
    }

    final Button[] buttons2 = new Button[size];
    {
      final Button sin = new Button("+");
      buttons2[0] = sin;
      buttons2[0].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons2[0].setOnMouseClicked(event -> {
        expression.input(new Addition());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button cos = new Button("-");
      buttons2[1] = cos;
      buttons2[1].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons2[1].setOnMouseClicked(event -> {
        expression.input(new Subtraction());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button ln = new Button("*");
      buttons2[2] = ln;
      buttons2[2].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons2[2].setOnMouseClicked(event -> {
        expression.input(new Multiplication());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button dx = new Button("/");
      buttons2[3] = dx;
      buttons2[3].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons2[3].setOnMouseClicked(event -> {
        expression.input(new Division());
        onButtonClicked.accept(""+expression.toString());
      });

      final Button x = new Button("=");
      buttons2[4] = x;
      buttons2[4].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
      buttons2[4].setOnMouseClicked(event -> {
        onButtonClicked.accept(""+expression.equalsClicked());
      });

      rows[2] = new HBox(buttons2);
    }


    for (int row = 0; row < 2; row++) {
      final Button[] buttons = new Button[size];
      for (int col = 0; col < size; col++) {
        final int index = col + row * size;
        final Button button = new Button("" + index);
        buttons[col] = button;
        buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        buttons[col].setOnMouseClicked(event -> {
          expression.input(new ConstantMathExpression(index));
          onButtonClicked.accept(""+expression.toString());
        });
      }
      rows[row] = new HBox(buttons);
    }
/*
    for (int row = 3; row < size-1; row++) {
      final Button[] buttons = new Button[size];
      for (int col = 0; col < size; col++) {
        final int index = col + row * size;
        final Button button = new Button("" + index);
        buttons[col] = button;
        if (index % 2 == 0) {
          buttons[col].setDisable(true);
        }
        buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        buttons[col].setOnMouseClicked(event -> {
          onButtonClicked.accept("You clicked " + index);
        });
      }
      rows[row] = new HBox(buttons);
    }

 */
    return new VBox(rows);
  }
}
