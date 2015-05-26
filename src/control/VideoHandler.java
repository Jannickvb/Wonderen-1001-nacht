package control;

import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;
	
	
public class VideoHandler {

	private JFrame ourFrame = new JFrame();
	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private String mediaPath = "";
	
	
	public VideoHandler(String filePath){
		File file = new File(filePath);
		mediaPath = file.getAbsolutePath();
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        ourMediaPlayer = new EmbeddedMediaPlayerComponent(){
		    public void finished(MediaPlayer mediaPlayer) {
		        ourMediaPlayer.release(); 
		        //ourFrame.dispatchEvent(new WindowEvent(ourFrame, WindowEvent.WINDOW_CLOSING));
		        ourFrame.dispose();
		    }
		};
		ourFrame.setContentPane(ourMediaPlayer);
		ourFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ourFrame.setUndecorated(true);
		ourFrame.setVisible(true);
		ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public EmbeddedMediaPlayerComponent getMediaPlayer(){
		return ourMediaPlayer;
	}
	
	public void run(){
		ourMediaPlayer.getMediaPlayer().playMedia(mediaPath);
	}
}