package view;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import model.RunawaySubscene;

public class Help extends RunawaySubscene{
	private final static int BackX = 375;
	private final static int BackY = 325;
	private static ArrayList<String> contents =new ArrayList<String>();
	
	public Help(AnchorPane superPane) {
		super(600, 400);
		Display(superPane);
	}
	public void Display(AnchorPane superPane) {
		setLayoutX(100);
		setLayoutY(100);
		superPane.getChildren().add(this);
		
		//filltext
		AddContent();
		FillText("How to play",contents);
		
		//createbackbutton
		CreateBackButton(BackX,BackY,superPane,this);
	}
	
	private static void AddContent() {
		if(!contents.isEmpty()) return ;
		contents.add("Arrow Up -> jump");
		contents.add("Arrow Left -> go backward");
		contents.add("Arrow Right -> go forward");
	}
	
}
