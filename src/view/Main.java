package view;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setFullScreenEnabled(GameFrame.WindowMode.fullscreenWindow.ordinal());
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
