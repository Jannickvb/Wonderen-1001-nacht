package control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testShizzle extends JFrame {

	public testShizzle() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		InputHandler input = new InputHandler("COM3");
		JPanel mainPanel = new JPanel();
		JButton button = new JButton("Led1 on");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				input.setLed1(true);
				
			}
		});
		mainPanel.add(button);
		
		button = new JButton("led1 off");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				input.setLed1(false);
				
			}
		});
		mainPanel.add(button);
		add(mainPanel);
		pack();
		setSize(new Dimension(800,600));
		setVisible(true);
	}
}
