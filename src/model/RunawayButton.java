package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RunawayButton extends Button{
	private static final String BUTTON_PRESSED_URL = ClassLoader.getSystemResource("buttonLong_brown_pressed.png").toString();
	private static final String BUTTON_FREE_URL = ClassLoader.getSystemResource("buttonLong_brown.png").toString();
	private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url("+ BUTTON_PRESSED_URL + ")";
	private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(" + BUTTON_FREE_URL + ")";
	
	public RunawayButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
		
	}
	
	private void setButtonFont() {
		setFont(Font.font(23));
		setTextFill(Color.WHITE);;
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	
	private void initializeButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			
			}
		});
	}
}
