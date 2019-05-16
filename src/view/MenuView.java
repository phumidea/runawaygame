package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.RunawayButton;

public class MenuView extends GameManager{

	public static final int HEIGHT = 800;
	public static final int WIDTH = 1200;
	private AnchorPane mainPane;
	private Scene mainScene;

	private final static int MENU_BUTTON_STARTX = 500;
	private final static int MENU_BUTTON_STARTY = 350;
	private static final String BACKGROUND_IMAGE = ClassLoader.getSystemResource("Background.jpg").toString();


	List<RunawayButton> menuButton;
	private RunawayButton helpButton;
	private RunawayButton startButton;
	private RunawayButton exitButton;

	public MenuView() {
		
		menuButton = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		GameManager.mainStage.setScene(mainScene);
		mainStage.setTitle("Runaway");
		mainStage.setResizable(false);
		createButtons();
		createBackground();
		drawRunaway();
		helpButton.setOnAction(e->new Help(mainPane));
		startButton.setOnAction(e->new GameView());
		exitButton.setOnAction(e-> mainStage.close());

	}

	public Stage getMainStage() {
		return GameManager.mainStage;
	}

	private void addMenuButton(RunawayButton button) {
		button.setLayoutX(MENU_BUTTON_STARTX);
		button.setLayoutY(MENU_BUTTON_STARTY + menuButton.size() * 100);
		menuButton.add(button);
		mainPane.getChildren().add(button);
	}

	private void createButtons() {
		createStartButton();

		createHelpButton();
		createExitButton();
	}

	private void createStartButton() {
		startButton = new RunawayButton("PLAY");
		addMenuButton(startButton);
	}
	
	
	private void createHelpButton() {
		helpButton = new RunawayButton("HELP");
		addMenuButton(helpButton);
	}
	
	private void createExitButton() {
		exitButton = new RunawayButton("EXIT");
		addMenuButton(exitButton);
	}

	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_IMAGE, WIDTH, HEIGHT, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void drawRunaway() {
		Canvas canvas = new Canvas(1500,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainPane.getChildren().add(canvas);
		drawText(gc);
	}
	
	private void drawText(GraphicsContext gc) {
		gc.setLineWidth(3);
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 150);
		gc.setFont(theFont);
		gc.fillText("RUNAWAY", 200, 200);
		gc.strokeText("RUNAWAY", 200, 200);
	}
	
}
