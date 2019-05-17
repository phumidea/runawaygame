package entities;

import java.util.ArrayList;

import TileMap.TileMap;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Player extends MapObject {

	// player stuff
	private int health;
	private int maxHealth;

	// animations
	private ArrayList<Image[]> sprites;
	private final int[] numFrames = { 1, 2, 1, 1 };

	// animation actions static not use when use player more than 1  
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	
	// imagePath player
	private static final String alienPink_stand_path = ClassLoader.getSystemResource("alienPink_stand.png").toString();
	private static final String alienPink_walk1_path = ClassLoader.getSystemResource("alienPink_walk1.png").toString();
	private static final String alienPink_walk2_path = ClassLoader.getSystemResource("alienPink_walk2.png").toString();
	private static final String alienPink_jump_path = ClassLoader.getSystemResource("alienPink_jump.png").toString();
	private static final String alienPink_hit_path = ClassLoader.getSystemResource("alienPink_hit.png").toString();

	public Player(TileMap tm) {

		super(tm);

		width = 40;
		height = 40;
		cwidth = 20;
		cheight = 20;

		moveSpeed = 10;
		maxSpeed = 20;
		stopSpeed = 6;
		fallSpeed = 5.0;
		maxFallSpeed = 15.0;
		jumpStart = -35;
		stopJumpSpeed = 0.3;

		facingRight = true;

		health = maxHealth = 5;


		try {

			sprites = new ArrayList<Image[]>();
			for (int i = 0; i < 4; i++) {

				Image[] bi = new Image[numFrames[i]];

				switch (i) {
				case 0:
					bi[0] = new Image(alienPink_stand_path, 40, 40, false, true);
					break;
				case 1:
					bi[0] = new Image(alienPink_walk1_path, 40, 40, false, true);
					bi[1] = new Image(alienPink_walk2_path, 40, 40, false, true);
					break;
				case 2:
					bi[0] = new Image(alienPink_jump_path, 40, 40, false, true);
					break;
				case 3:
					bi[0] = new Image(alienPink_hit_path, 40, 40, false, true);
					break;


				}

				sprites.add(bi);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);


	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	
	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}


		if (jumping && !falling) {
			
			dy = jumpStart;
			falling = true;
		}

		// falling
		if (falling) {

			dy += fallSpeed;

			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}

	}

	public void update() {


		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		
		if (dy > 0) {

			if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 40;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 40;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 40;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 40;
			}
		}

		animation.update();

		if (right)
			facingRight = true;
		if (left)
			facingRight = false;

	}

	public void draw(AnchorPane g) {
		
		setMapPosition();
		
		super.draw(g);

	}

}
