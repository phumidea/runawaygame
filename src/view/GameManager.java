package view;

import javafx.stage.Stage;

public class GameManager {
	public static Stage mainStage = new Stage();
	MenuView menu;
	GameView Game;
	
	public void setMenuView() {
		try{
			
			menu= new MenuView();
			Game=null;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public GameView setGameView() {
		try{
			menu.Game =new GameView();
			menu=null;
			return Game;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Stage getStage() {
		return mainStage;
	}

}
