package control;

import java.util.ArrayList;

import model.gamestates.BossFightState;
import model.gamestates.City1State;
import model.gamestates.City2State;
import model.gamestates.GameState;
import model.gamestates.IntroMovState;
import model.gamestates.MageTalkState;
import model.gamestates.MageTalkState2;
import model.gamestates.MenuState;
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
		gameStates.add(new TutorialState(cm, ImageHandler.getImage(ImageHandler.ImageType.tutorial_plate)));
		gameStates.add(new MageTalkState(cm));
		gameStates.add(new City1State(cm));
		gameStates.add(new City2State(cm));
		gameStates.add(new MageTalkState2(cm));
		gameStates.add(new TutorialState(cm, ImageHandler.getImage(ImageHandler.ImageType.tutorial_spell)));
		gameStates.add(new BossFightState(cm));
	}
	
	public void select(int i) {
		if(i >= 0 && i < gameStates.size())
			currentstate = gameStates.get(i);
	}
	
	public void initializeGameState(){
		GameState state = gameStates.get(0);
		if(index >= 0 && index < gameStates.size())
		{
			state = gameStates.get(index+1);
		} else {
			state = gameStates.get(0);
		}
		
		state.init();
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
		gameSequence.add(gameStates.get(4));
		gameSequence.add(gameStates.get(5));
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
