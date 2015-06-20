package control;

public class ScoreHandler {

	private static int score = 0;
	
	public static int getScore() {
		return score;
	}
	
	public static void setScore(int newScore) {
		score = newScore;
	}
}