package control;

import java.util.ArrayList;

import model.gamestates.GameState;
import model.gamestates.MenuState;
import model.gamestates.PlayState;

public class GameStateManager {
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	public GameState currentstate;
	private ControlManager cm;
	public GameStateManager(ControlManager cm){
		this.cm = cm;
		gameStates.add(new MenuState(cm));
		gameStates.add(new PlayState(cm));
		currentstate = gameStates.get(0);
	}
	
	public void select(int i) {
		if(i >= 0 && i < gameStates.size())
			currentstate = gameStates.get(i);
	}
	
	public void initializeGameState(){
		currentstate.init();
	}
}
