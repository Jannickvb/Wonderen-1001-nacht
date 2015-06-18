package model.entities;

	import java.awt.Graphics2D;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.Timer;

	import control.ControlManager;
	import control.ImageHandler;
	import control.ImageHandler.ImageType;

public class Palace extends Entity {
				
		/**
		 * Constructor of the Palace object.
		 * @param cm - The control manager of the game.
		 * @param positionY - The y start position of the palace.
		 */
		public Palace(ControlManager cm, int positionY) {
			super(cm,ImageHandler.getImage(ImageType.palace));
			this.positionY = positionY-getSprite().getHeight();
			this.positionX = (ControlManager.screenWidth/2)-(getSprite().getWidth()/2);
		}

		/**
		 * Draws the palace on the screen.
		 */
		@Override
		public void draw(Graphics2D g2) {
			g2.drawImage(getSprite(),positionX,positionY,null);	
		}

		/**
		 * Update method for the Palace object.
		 * The palace stops moving when its fully displayed.
		 */
		@Override
		public void update() {
			if(positionY >= -20 && positionY <= 35) {
				setDead(true);
				setTimer(false);
			}
		}

		/**
		 * Init method for the Palace object.
		 */
		@Override
		public void init() {
		}
}