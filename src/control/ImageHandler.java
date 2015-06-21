package control;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import view.Main;

public class ImageHandler {
	
	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private static Scalr.Mode mode;
	public ImageHandler() {
		
	}
	
	static{
		try{
			images.add(ImageIO.read(Main.class.getResource("/images/menu/bg_menu.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_right.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_left.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_statue_l.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_statue_r.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_buttons.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_header.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_grad.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/menu/menu_instr.png")));		
			
			images.add(ImageIO.read(Main.class.getResource("/images/Tutorial.jpg ")));
			images.add(ImageIO.read(Main.class.getResource("/images/spell_tutorial.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/boat.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/RunningGuys.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/hexagon.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/star.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spiral.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle_particle1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle_particle2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle_particle3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle_particle1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle_particle2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle_particle3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/hexagon_particle1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/hexagon_particle2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/hexagon_particle3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/star_particle1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/star_particle2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/star_particle3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spiral_particle1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spiral_particle2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spiral_particle3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Trol.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/PalaceOutside.png")));
			
			//magestate
			images.add(ImageIO.read(Main.class.getResource("/images/magestate/magestate.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/magestate/mirror_bg.png")));
			
			//poor rich state
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_vignette.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_door.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_door_left.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_door_right.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_wizard.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_djinn.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_left.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_right.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_poor.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_rich.png")));
			
			
			
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/background.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/trees.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Poorcity.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Richcity.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/rock1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/rock2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/rock3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/rock4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/heart.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/pier.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/freeze.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/cloud.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/box1.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box3.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box4.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box5.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box6.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/box7.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/palace.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/upgrade.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/coin.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/endChoiceImage.png")));
			
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//als je een plaatje toegevoegd hebt, sorteer het dan gelijk even fatsoenlijk
	
	public enum ImageType{
		//menu
		menubg,
		menu_left,
		menu_right,
		menu_statue_l,
		menu_statue_r,
		menu_buttons,
		menu_header,
		menu_grad,
		menu_instr,
		
		/** sorteer onderstaande: */
		tutorial_plate,
		tutorial_spell, 
		player_boat,
		player_run,
		spell1,
		spell2,
		spell3,
		spell4, 
		spell5,
		spell1_particle1,
		spell1_particle2,
		spell1_particle3,
		spell2_particle1,
		spell2_particle2,
		spell2_particle3,
		spell3_particle1,
		spell3_particle2,
		spell3_particle3,
		spell4_particle1,
		spell4_particle2,
		spell4_particle3,
		spell5_particle1,
		spell5_particle2,
		spell5_particle3,
		mage1, 
		mage2, 
		troll, 
		arrival, 
		
		//magestate
		magestate,
		mirror_bg,
		
		//poorich
		pr_vignette,
		pr_door,
		pr_door_left,
		pr_door_right,
		pr_wizard,
		pr_djinn,
		pr_cloud_left,
		pr_cloud_right,
		pr_poor,
		pr_rich,
		 
		grass, 
		trees,
		poorcity, 
		richcity,
		//Boat game: 
		rock1, 
		rock2, 
		rock3, 
		rock4, 
		heart, 
		pier, 
		freeze,
		cloud,
		
		box1, 
		box2, 
		box3, 
		box4, 
		box5, 
		box6, 
		box7, 
		palace,
		upgrade,
		coin, 
		
		endChoice;
	}
	
	public static BufferedImage getImage(ImageType img){
		return images.get(img.ordinal());
	}

	public static BufferedImage getScaledImage(BufferedImage image){
		getMode(image);
		int targetSize;
		if(mode == Scalr.Mode.FIT_TO_HEIGHT)
			targetSize = ControlManager.screenHeight;
		else
			targetSize = ControlManager.screenWidth;
		image = Scalr.resize(image, mode, targetSize,Scalr.OP_ANTIALIAS);
		return image;
	}
	
	private static Scalr.Mode getMode(BufferedImage image){
		if (image.getHeight() < image.getWidth()) {
			mode = Scalr.Mode.FIT_TO_WIDTH;
		} else {
			mode = Scalr.Mode.FIT_TO_HEIGHT;
		}
		return mode;
	}
	
	/**
	 * Method that draws given text in the center of the screen.
	 * @param text - The text you want to display.
	 * @param g2 - The graphics2D object.
	 * @param y - The y position of the text.
	 */
	public static void drawCenteredText(String text, Graphics2D g2, int y) {
		int x = (ControlManager.screenWidth-g2.getFontMetrics().stringWidth(text))/2;
		g2.drawString(text, x, y);
	}
}

