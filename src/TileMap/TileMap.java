package TileMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import view.GameView;

public class TileMap {
	//mapTiles URL
	private static final String maptile_1 = ClassLoader.getSystemResource("1.png").toString();
	private static final String maptile_2 = ClassLoader.getSystemResource("2.png").toString();
	private static final String maptile_3 = ClassLoader.getSystemResource("3.png").toString();
	private static final String maptile_4 = ClassLoader.getSystemResource("4.png").toString();
	private static final String maptile_5 = ClassLoader.getSystemResource("5.png").toString();
	private static final String maptile_6 = ClassLoader.getSystemResource("6.png").toString();
	private static final String maptile_7 = ClassLoader.getSystemResource("7.png").toString();
	private static final String maptile_8 = ClassLoader.getSystemResource("8.png").toString();
	private static final String maptile_9 = ClassLoader.getSystemResource("9.png").toString();
	
	
	// position
	private double x;
	private double y;

	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public int type[][];
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GameView.HEIGHT / tileSize;
		numColsToDraw = GameView.WIDTH / tileSize;
	}


	public void loadTiles() {
		try {
			numCols = Map.mapData[0].length();
			numRows = Map.mapData.length;
			map = new int[numRows][numCols];
			type = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = GameView.WIDTH - width;
			xmax = 0;
			ymin = GameView.HEIGHT - height;
			ymax = 0;
			//System.out.println(GameView.WIDTH+" "+width);
			//System.out.println("x "+xmin+" "+xmax);
			for (int row = 0; row < numRows; row++) {
				String line = Map.mapData[row];
				for (int col = 0; col < numCols; col++) {
					map[row][col] = line.charAt(col) - 48;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTileSize() {
		return tileSize;
	}

	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPosition(double x, double y) {

		this.x = x ;
		this.y = y ;
		System.out.println("setposition"+x+" "+y+" "+this.x+" "+this.y);

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;
		System.out.println("coloffset "+ colOffset+" "+this.x);

	}

	private void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (y < ymin)
			y = ymin;
		if (x > xmax)
			x = xmax;
		if (y > ymax)
			y = ymax;
	}

	public void draw(AnchorPane gamepane) {

		Canvas canvas = new Canvas(numCols * tileSize, numRows * tileSize);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gamepane.getChildren().add(canvas);


		String picpath = "";
		for (int i = colOffset; i < numColsToDraw + colOffset; i++) {
			for (int j = rowOffset; j < numRowsToDraw + rowOffset; j++) {
				if (map[j][i] == 0) {
					type[j][i]=0;
					continue;
				}
					
				switch (map[j][i]) {
				case 1:
					picpath = maptile_1;
					type[j][i] = 1;
					break;
				case 2:
					picpath = maptile_2;
					type[j][i] = 1;
					break;
				case 3:
					picpath = maptile_3;
					type[j][i] = 1;
					break;
				case 4:
					picpath = maptile_4;
					type[j][i] = 1;
					break;
				case 5:
					picpath = maptile_5;
					type[j][i] = 1;
					break;
				case 6:
					picpath = maptile_6;
					type[j][i] = 2;
					break;
				case 7:
					picpath = maptile_7;
					type[j][i] = 1;
					break;
				case 8:
					picpath = maptile_8;
					type[j][i] = 1;
					break;
				case 9:
					picpath = maptile_9;
					type[j][i] = 2;
					break;
				}
				Image pic = new Image(picpath, tileSize, tileSize, false, true);
				
				gc.drawImage(pic, tileSize *( i-colOffset)-0.5, tileSize * (j-rowOffset));
			}
		}
	}

}
