package entities;

import java.awt.Rectangle;

import TileMap.TileMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import view.GameView;

public class MapObject {

		// tile stuff
		protected TileMap tileMap;
		protected int tileSize;
		protected double xmap;
		protected double ymap;
		
		// position and vector
		protected double x;
		protected double y;
		protected double dx;
		protected double dy;
		
		// dimensions
		protected int width;
		protected int height;
		
		// collision box
		protected int cwidth;
		protected int cheight;
		
		// collision
		protected int currRow;
		protected int currCol;
		protected double xdest;
		protected double ydest;
		protected double xtemp;
		protected double ytemp;
		protected boolean topLeft;
		protected boolean topRight;
		protected boolean bottomLeft;
		protected boolean bottomRight;
		
		// animation
		protected Animation animation;
		protected int currentAction;
		protected int previousAction;
		protected boolean facingRight;
		
		// movement
		protected boolean left;
		protected boolean right;
		protected boolean up;
		protected boolean down;
		protected boolean jumping;
		protected boolean falling;
		
		// movement attributes
		protected double moveSpeed;
		protected double maxSpeed;
		protected double stopSpeed;
		protected double fallSpeed;
		protected double maxFallSpeed;
		protected double jumpStart;
		protected double stopJumpSpeed;
		
		// constructor
		public MapObject(TileMap tm) {
			tileMap = tm;
			tileSize = tm.getTileSize(); 
		}
		
		public boolean intersects(MapObject o) {
			Rectangle r1 = getRectangle();
			Rectangle r2 = o.getRectangle();
			return r1.intersects(r2);
		}
		
		public Rectangle getRectangle() {
			return new Rectangle(
					(int)x - cwidth,
					(int)y - cheight,
					cwidth,
					cheight
			);
		}
		
		public void calculateCorners(double x, double y) {
			
			int leftTile = (int)(x - cwidth / 2) / tileSize;
			int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
			int topTile = (int)(y - cheight / 2) / tileSize;
			int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
			
			boolean tl = tileMap.type[topTile][leftTile]==1;
			boolean tr = tileMap.type[topTile][rightTile]==1;
			boolean bl = tileMap.type[bottomTile][leftTile]==1;
			boolean br = tileMap.type[bottomTile][rightTile]==1;
			
			topLeft = tl;
			topRight = tr;
			bottomLeft = bl;
			bottomRight = br;

			
		}
		public boolean isWin() {
			int leftTile = (int)(x - cwidth / 2) / tileSize;
			int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
			int topTile = (int)(y - cheight / 2) / tileSize;
			int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
			return tileMap.type[topTile][leftTile]==2||tileMap.type[bottomTile][rightTile]==2;
		}
		public boolean isWater() { return y + ymap + height >= GameView.HEIGHT;}
		public void checkTileMapCollision() {
			
			currCol = (int)x / tileSize;
			currRow = (int)y / tileSize;
			
			xdest = x + dx;
			ydest = y + dy;
			
			xtemp = x;
			ytemp = y;
			
			calculateCorners(x, ydest);

			if(dy < 0) {
				if(topLeft || topRight) {
					dy = 0;
					ytemp = currRow * tileSize + cheight / 2;
				}
				else {
					ytemp += dy;
				}
			}
			if(dy > 0) {
				if(bottomLeft || bottomRight) {
					dy = 0;
					falling = false;
					ytemp = (currRow + 1) * tileSize - cheight / 2;
				}
				else {
					ytemp += dy;
				}
			}
			
			calculateCorners(xdest, y);
			if(dx < 0) {
				if(topLeft || bottomLeft) {
					dx = 0;
					xtemp = currCol * tileSize + cwidth / 2;
				}
				else {
					xtemp += dx;
				}
			}
			if(dx > 0) {
				if(topRight || bottomRight) {
					dx = 0;
					xtemp = (currCol + 1) * tileSize - cwidth / 2;
				}
				else {
					xtemp += dx;
				}
			}
			
			if(!falling) {
				calculateCorners(x, ydest + 1);
				if(!bottomLeft && !bottomRight) {
					falling = true;
				}
			}
			
		}
		
		public int getx() { return (int)x; }
		public int gety() { return (int)y; }
		public int getWidth() { return width; }
		public int getHeight() { return height; }
		public int getCWidth() { return cwidth; }
		public int getCHeight() { return cheight; }
		
		public void setPosition(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public void setVector(double dx, double dy) {
			this.dx = dx;
			this.dy = dy;
		}
		
		public void setMapPosition() {
			xmap = tileMap.getx();
			ymap = tileMap.gety();
		}
		
		public void setLeft(boolean b) { left = b; }
		public void setRight(boolean b) { right = b; }
		public void setUp(boolean b) { up = b; }
		public void setDown(boolean b) { down = b; }
		public void setJumping(boolean b) { jumping = b; }
		
		public boolean notOnScreen() {
			return x + xmap + width < 0 ||
				x + xmap - width > GameView.WIDTH ||
				y + ymap + height < 0 ||
				y + ymap - height > GameView.HEIGHT;
		}
		public void draw(AnchorPane gamepane) {
			
			Canvas canvas = new Canvas( GameView.WIDTH, GameView.HEIGHT);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gamepane.getChildren().add(canvas);
			if(facingRight) {
				gc.drawImage(animation.getImage(),(int)(x + xmap - width / 2),(int)(y + ymap - height / 2));
			}
			else {
				gc.drawImage(animation.getImage(),(int)(x + xmap - width / 2 + width),(int)(y + ymap - height / 2),-width,height);
			}
		}

}
