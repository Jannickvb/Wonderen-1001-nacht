package control;

public class ScoreHandler {

	private int score = 500;
	private boolean armGekozen;
	private boolean keptMoney;
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isArmGekozen() {
		return armGekozen;
	}
	
	public  void setArmGekozen(boolean armGekozen) {
		this.armGekozen = armGekozen;
	}

	public boolean isKeptMoney() {
		return keptMoney;
	}

	public void setKeptMoney(boolean keptMoney) {
		this.keptMoney = keptMoney;
	}
}