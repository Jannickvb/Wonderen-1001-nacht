package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.gamestates.Bossfight.BossFightState;

public class DrawThread implements Runnable, ActionListener
	{
		private BossFightState boss;
		private Timer timer;

		public DrawThread(BossFightState boss)
		{
			this.boss = boss;
			this.timer = new Timer(1000/60, this);
		}

		@Override
		public void run() {
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boss.refresh();
		}
	}