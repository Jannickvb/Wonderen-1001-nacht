package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import control.ControlManager;

public class GameFrame extends JFrame{
	public GameFrame(){
		super("bazenspelletje");
		setPreferredSize(new Dimension(800,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setContentPane(new GamePanel(new ControlManager(this)));
		setVisible(true);
	}
}
