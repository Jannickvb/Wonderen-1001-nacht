package control;

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
			
			images.add(ImageIO.read(Main.class.getResource("/images/Tutorial.jpg")));
			images.add(ImageIO.read(Main.class.getResource("/images/spell_tutorial.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatgame/boat.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/RunningGuys.png")));
			
			images.add(ImageIO.read(Main.class.getResource("/images/spells/circle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/triangle.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/hexagon.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/star.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/spells/spiral.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Tovenaar2.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/Trol.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/PalaceOutside.png")));
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
			
			images.add(ImageIO.read(Main.class.getResource("/images/treasureRoom.jpg")));
			
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
		mage1, 
		mage2, 
		troll, 
		arrival, 
		
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
		
		treasure_room, 
		grass, 
		trees,
		poorcity, 
		richcity,
		rock1, 
		rock2, 
		rock3, 
		rock4, 
		heart, 
		pier, 
		box1, 
		box2, 
		box3, 
		box4, 
		box5, 
		box6, 
		box7, 
		palace,
		upgrade,
		coin; 
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
}

