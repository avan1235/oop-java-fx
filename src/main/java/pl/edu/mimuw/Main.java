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
		final HBox[] rows = new HBox[size];
		
		for (int row = 0; row < size; row++) {
			final Button[] buttons = new Button[size];
			for (int col = 0; col < size; col++) {
				final int index = col + row * size;
				final Button button = new Button("Click " + index);
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
		return new VBox(rows);
	}
}
