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
			
			//boat Tutorial
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/background.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/tuthelpframe.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/tutscreen.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/ppleft.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/ppoff.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/ppright.png")));
			
			//spell Tutorial
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/spelltut.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/boatTutorial/tuthelpframespell.png")));
			
			
			//arrival
			images.add(ImageIO.read(Main.class.getResource("/images/arrival/arr_fg.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/arrival/arr_stars.png")));
			
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
			
			//trollstate
			images.add(ImageIO.read(Main.class.getResource("/images/trollstate/background.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/trollstate/darken.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/trollstate/foreground.png")));
			
			//bossfightstate bg
			images.add(ImageIO.read(Main.class.getResource("/images/trollstate/bossfight.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/trollstate/bossfight_hit.png")));
			
			//poor rich state
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_bg.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_left.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_right.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_troll.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_cloud_wiz.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/pr_wiz_troll.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/poorrich/overlay.png")));
			
			//endstate
			images.add(ImageIO.read(Main.class.getResource("/images/endstate/bg.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/endstate/sign.png")));
			images.add(ImageIO.read(Main.class.getResource("/images/endstate/armen.png")));
			
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
		
		//boat Tutorial
		boat_tut_bg,
		boat_tut_help,
		boat_tut_screen,
		boat_tut_pp_left,
		boat_tut_pp_off,
		boat_tut_pp_right,
		
		//spellTutorial
		spell_tut_help,
		spell_tut_frame,
		
		//arrival
		arr_fg,
		arr_stars,
		
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
		
		//trollstate
		troll_bg,
		troll_darken,
		troll_fg,
		
		//bossfight
		bf_troll_normal,
		bf_troll_hit,
		
		//poorich
		pr_bg,
		pr_cloud_left,
		pr_cloud_right,
		pr_cloud_troll,
		pr_cloud_wiz,
		pr_wiz_troll,
		pr_overlay,
		 
		//endstate
		end_bg,
		end_sign,
		end_poor,
		
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

