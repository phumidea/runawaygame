package model;

import java.util.ArrayList;

import javafx.scene.SubScene;
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



public class RunawaySubscene extends SubScene {
	
	private static final String BACKGROUND_IMAGE = ClassLoader.getSystemResource("panel_brown.png").toString();;
	private AnchorPane root2;
	private int width;
	private int height;
	public RunawaySubscene(int width,int height) {
		super(new AnchorPane(),width,height);
		this.width= width;
		this.height=height;
		
		prefWidth(width);
		prefWidth(height);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,width,height,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
	}
	public void CreateBackButton(int BackX,int BackY,AnchorPane superpane,RunawaySubscene subscene) {
		RunawayButton BackButton= new RunawayButton("BACK");
		BackButton.setLayoutX(BackX);
		BackButton.setLayoutY(BackY);
		root2.getChildren().add(BackButton);
		BackButton.setOnAction(e->superpane.getChildren().remove(subscene));
	}

	public void FillText(String Title,ArrayList<String> contents) {
		
		//add title
		Canvas canvas = new Canvas(width,height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root2.getChildren().add(canvas);
		gc.setLineWidth(2);
		gc.setFill(Color.WHITE);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 50);
		gc.setFont(theFont);
		gc.fillText(Title, 30, 60);
		
		//add contents
		gc.setLineWidth(1);
		gc.setFont(Font.font(30));
		int y=110;
		for(int i=0;i<contents.size();i++) {
			gc.fillText(contents.get(i), 50, y+ i*40);
		}
	}
}
