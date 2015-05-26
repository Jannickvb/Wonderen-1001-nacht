package control;

import java.util.ArrayList;

import model.gamestates.GameState;
import model.gamestates.IntroMovState;
import model.gamestates.MenuState;
import model.gamestates.PlayState;
import model.gamestates.TutorialState;

public class GameStateManager {
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	private ArrayList<GameState> gameSequence = new ArrayList<GameState>();
	public GameState currentstate;
	private int index = 0;
	private ControlManager cm;
	
	
	public GameStateManager(ControlManager cm){
		this.cm = cm;
		reloadGameStates();
		currentstate = gameStates.get(0);
		initializeSequence();
	}
	
	public void reloadGameStates() { 
		gameStates.clear();
		gameStates.add(new MenuState(cm));
		gameStates.add(new IntroMovState(cm));
		gameStates.add(new TutorialState(cm));
		gameStates.add(new PlayState(cm));
	}
	
	public void select(int i) {
		if(i >= 0 && i < gameStates.size())
			currentstate = gameStates.get(i);
	}
	
	public void initializeGameState(){
		currentstate.init();
	}
	
	  
	/**
	 * Use this method to hardcode the game sequence
	 * the methods to run the sequence aren't implemented yet
	 */
	
	public void initializeSequence(){
		gameSequence.add(gameStates.get(0));
		gameSequence.add(gameStates.get(1));
		gameSequence.add(gameStates.get(2));
		gameSequence.add(gameStates.get(3));
	}
	
	public void next(){
		initializeGameState();
		index++;
		if(index == gameSequence.size()) {
			index = 0;
		}
		
	}
	
	public void back(){
		initializeGameState();
		index--;
		if(index == -1) {
			index = gameSequence.size() - 1;
		}
		
	}
	
	
	public GameState getCurrentState(){
		currentstate = gameSequence.get(index);
		return currentstate;
	}
	
	public void printGameSequence(){
		System.out.println(gameSequence.toString());
	}
}
