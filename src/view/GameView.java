package view;


import TileMap.TileMap;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.AudioClip;


public class GameView extends GameManager {
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 1200;
	private Scene GameScene;
	private AnchorPane Gamepane;

	private TileMap tileMap;

	private Player player;
	AudioClip backgroundSound = new AudioClip(ClassLoader.getSystemResource("background.mp3").toString());
	private static final String BACKGROUND_IMAGE = ClassLoader.getSystemResource("BackgroundColorFall.png").toString();

	
	AnimationTimer animation;

	public GameView() {
		
		init();
		draw();
		animation = new AnimationTimer() {
			public void handle(long now) {
				
				draw();
				update();
				createKeyListeners();
				
				

			}
		};
		animation.start();

	}
	/*public Stage getMainStage() {
		return GameManager.mainStage;
	}*/

	public void init() {

		tileMap = new TileMap(40);
		tileMap.loadTiles();
		tileMap.setPosition(0, 0);


		player = new Player(tileMap);
		player.setPosition(100, 100);
	}

	public void update() {

		// update player
		player.update();
		tileMap.setPosition(GameView.WIDTH / 2 - player.getx(), GameView.HEIGHT / 2 - player.gety());
		if(player.isWater()||player.isWin()) {
			animation.stop();
			setMenuView();
		}
		playMusic();
		
	}

	public void draw() {
		Gamepane = new AnchorPane();
		GameScene = new Scene(Gamepane, WIDTH, HEIGHT);
		GameManager.mainStage.setScene(GameScene);

		// CreateBackground
		Image backgroundImage = new Image(BACKGROUND_IMAGE, WIDTH, MenuView.HEIGHT, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		Gamepane.setBackground(new Background(background));
		tileMap.draw(Gamepane);
		player.draw(Gamepane);

	}

	private void createKeyListeners() {

		GameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					player.setJumping(true);
				} else if (event.getCode() == KeyCode.LEFT) {
					player.setLeft(true);
				} else if (event.getCode() == KeyCode.RIGHT) {
					player.setRight(true);
				}
			}

		});

		GameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					player.setJumping(false);
				} else if (event.getCode() == KeyCode.LEFT) {
					player.setLeft(false);
				} else if (event.getCode() == KeyCode.RIGHT) {
					player.setRight(false);
				}

			}
		});
	}
	
	private void playMusic() {
		while(!backgroundSound.isPlaying()) {
			backgroundSound.play();
		}
	}
}
