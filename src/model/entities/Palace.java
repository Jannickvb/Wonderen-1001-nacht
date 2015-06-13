package model.entities;

	import java.awt.Graphics2D;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.Timer;

	import control.ControlManager;
	import control.ImageHandler;
	import control.ImageHandler.ImageType;

public class Palace extends Entity {
		
		public Palace(ControlManager cm, int positionY) {
			super(cm,ImageHandler.getImage(ImageType.palace));
			this.positionY = positionY-getSprite().getHeight();
			positionX = (ControlManager.screenWidth/2)-(getSprite().getWidth()/2);
		}

		@Override
		public void draw(Graphics2D g2) {
			g2.drawImage(getSprite(),positionX,positionY,null);	
		}

		@Override
		public void update() {
			if(positionY >= -20 && positionY <= 35) {
				setDead(true);
				setTimer(false);
			}
		}

		@Override
		public void init() {
		}
}
