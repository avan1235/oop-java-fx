package pl.edu.mimuw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
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
    final Scene scene = createSampleScene(5);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private static Scene createSampleScene(int size) {
    final TextArea textArea = new TextArea("Let's try me");
    textArea.setEditable(false);
    textArea.setMaxSize(size * BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);

    final VBox buttons = createSampleButtons(size, textArea::setText);
    final VBox root = new VBox(textArea, buttons);

    return new Scene(root);
  }

  private static VBox createSampleButtons(int size, Consumer<String> onButtonClicked) {
    final HBox[] rows = new HBox[size + 1];
    int i = 0;
    StringBuilder sb = new StringBuilder();
    ArrayList<String> actions = new ArrayList<>();
    AtomicReference<String> action = new AtomicReference<>(new String());

    for (int row = 0; row < size + 1; row++) {
      final Button[] buttons = new Button[size];
      for (int col = 0; col < size; col++) {
        i++;
        int finalI = i;

        switch (i) {
          case 1:
            final Button button1 = new Button();
            button1.setText("Build\nLeft");
            buttons[col] = button1;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              onButtonClicked.accept(sb.toString());});
            continue;
          case 2:
            final Button button2 = new Button();
            button2.setText("Build\nChild");
            buttons[col] = button2;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("( ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 3:
            final Button button3 = new Button();
            button3.setText("Build\nRight");
            buttons[col] = button3;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              onButtonClicked.accept(sb.toString());});
            continue;
          case 4:
            final Button button4 = new Button();
            buttons[col] = button4;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setDisable(true);
            continue;
          case 5:
            final Button button5 = new Button();
            buttons[col] = button5;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setDisable(true);
            continue;
          case 6:
            final Button button6 = new Button();
            button6.setText("Left\nDone");
            buttons[col] = button6;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setWrapText(true);
            buttons[col].setOnMouseClicked(event -> {
              sb.append(actions.remove(actions.size() - 1));
              onButtonClicked.accept(sb.toString());});
            continue;
          case 13:
            final Button button13 = new Button();
            buttons[col] = button13;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setDisable(true);
            continue;
          case 11:
            final Button button11 = new Button();
            buttons[col] = button11;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setDisable(true);
            continue;
          case 8:
            final Button button8 = new Button();
            button8.setText("Right\nDone");
            buttons[col] = button8;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              onButtonClicked.accept(sb.toString());});
            continue;
          case 7:
            final Button button7 = new Button();
            button7.setText("Child\nDone");
            buttons[col] = button7;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append(") ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 9:
            final Button button9 = new Button("x");
            buttons[col] = button9;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("x ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 10:
            final Button button10 = new Button("f(x)");
            buttons[col] = button10;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              onButtonClicked.accept("f(x) = " + sb.toString());});
            continue;
          case 12:
            final Button button12 = new Button("0 ");
            buttons[col] = button12;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("0 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 14:
            final Button button14 = new Button("+");
            buttons[col] = button14;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              actions.add("+ ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 15:
            final Button button15 = new Button("-");
            buttons[col] = button15;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              actions.add("- ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 16:
            final Button button16 = new Button("1");
            buttons[col] = button16;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("1 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 17:
            final Button button17 = new Button("2");
            buttons[col] = button17;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("2 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 18:
            final Button button18 = new Button("3");
            buttons[col] = button18;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("3 ");
              onButtonClicked.accept(sb.toString());});

            continue;
          case 19:
            final Button button19 = new Button("*");
            buttons[col] = button19;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              actions.add("* ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 20:
            final Button button20 = new Button("/");
            buttons[col] = button20;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              actions.add("/ ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 21:
            final Button button21 = new Button("4");
            buttons[col] = button21;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("4 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 22:
            final Button button22 = new Button("5");
            buttons[col] = button22;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("5 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 23:
            final Button button23 = new Button("6");
            buttons[col] = button23;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("6 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 24:
            final Button button24 = new Button("sin");
            buttons[col] = button24;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("sin ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 25:
            final Button button25 = new Button("cos");
            buttons[col] = button25;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("cos ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 26:
            final Button button26 = new Button("7");
            buttons[col] = button26;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("7 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 27:
            final Button button27 = new Button("8");
            buttons[col] = button27;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("8 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 28:
            final Button button28 = new Button("9");
            buttons[col] = button28;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("9 ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 29:
            final Button button29 = new Button("ln");
            buttons[col] = button29;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("ln ");
              onButtonClicked.accept(sb.toString());});
            continue;
          case 30:
            final Button button30 = new Button("der");
            buttons[col] = button30;
            buttons[col].setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
            buttons[col].setOnMouseClicked(event -> {
              sb.append("der ");
              onButtonClicked.accept(sb.toString());});
        }
      }
      rows[row] = new HBox(buttons);
    }
//    for (int row = 0; row < size; row++) {
//      final Button[] buttons = new Button[size];
//      for (int col = 0; col < size; col++) {
//        final int index = col + row * size; //zmienić na col row wyifowaćv

//        if (index % 2 == 0) {
//          buttons[col].setDisable(true);
//        }
//
//      }
//      rows[row] = new HBox(buttons);
//    }
    return new VBox(rows);
  }
}
