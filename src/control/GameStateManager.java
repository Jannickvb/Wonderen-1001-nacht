package control;

import java.util.ArrayList;

import model.gamestates.GameState;
import model.gamestates.Bossfight.BossFightState;
import model.gamestates.Bossfight.MageTalkState2;
import model.gamestates.magepath.Mage1State;
import model.gamestates.magepath.Mage2State;
import model.gamestates.magepath.PoorGameState;
import model.gamestates.start.BoatGameState;
import model.gamestates.start.IntroMovState;
import model.gamestates.start.MenuState;
import model.gamestates.start.TutorialState;
import model.gamestates.story.ArrivalState;
import model.gamestates.story.DoorChoiceState;
import model.gamestates.story.MageTalkState;
import model.gamestates.trollpath.RichGameState;
import model.gamestates.trollpath.Troll1State;
import model.gamestates.trollpath.Troll2State;

public class GameStateManager {
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	private ArrayList<GameState> gameSequence = new ArrayList<GameState>();
	private GameState currentstate;
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
		
		//Intro
		gameStates.add(new MenuState(cm));
//		gameStates.add(new IntroMovState(cm));//Done
//		gameStates.add(new TutorialState(cm, ImageHandler.getImage(ImageHandler.ImageType.tutorial_plate),0));//Done
		gameStates.add(new BoatGameState(cm));
		
		//Story
//		gameStates.add(new MageTalkState(cm));
//		gameStates.add(new ArrivalState(cm));
//		gameStates.add(new DoorChoiceState(cm));
//		gameStates.add(new PoorGameState(cm));
//		gameStates.add(new RichGameState(cm));
		
		
		//Before Bossfight
		gameStates.add(new Mage1State(cm));//nieuwe muziek
		gameStates.add(new Troll1State(cm));
		gameStates.add(new Mage2State(cm));
		gameStates.add(new Troll2State(cm));
		gameStates.add(new MageTalkState2(cm));
		gameStates.add(new TutorialState(cm, ImageHandler.getImage(ImageHandler.ImageType.tutorial_spell),1));
		gameStates.add(new BossFightState(cm));//nieuwe muziek
	//	gameStates.add(new EndGameState(cm));
	}
	
	public void select(int i) {
		if(i >= 0 && i < gameStates.size()){
			initializeGameState();
			index = i;
			
		}
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
		gameSequence.add(gameStates.get(6));
		gameSequence.add(gameStates.get(7));
		gameSequence.add(gameStates.get(8));
//		gameSequence.add(gameStates.get(9));
//		gameSequence.add(gameStates.get(10));
		//gameSequence.add(gameStates.get(11));
		//gameSequence.add(gameStates.get(12));
		//gameSequence.add(gameStates.get(13));
	}
	
	public void next(){
		initializeGameState();
		index++;
		if(index == gameSequence.size()) {
			index = 0;
		}
		
	}
	
	public void skipNext(){
		initializeGameState();
		index=index+2;
		if(index >= gameSequence.size()) {
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
	
	public GameState getState(int i){
		return gameSequence.get(i);
	}
	
	public int getIndex()
	{
		return index;
	}
	
	
	public GameState getCurrentState(){
		currentstate = gameSequence.get(index);
		return currentstate;
	}
	
	public void printGameSequence(){
		System.out.println(gameSequence.toString());
	}
}
