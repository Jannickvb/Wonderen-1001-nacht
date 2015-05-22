package control;

import javax.swing.JFrame;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
	
	
public class VideoHandler {

	private JFrame ourFrame = new JFrame();
	private EmbeddedMediaPlayerComponent ourMediaPlayer;
	private String mediaPath = "";
	
	VideoHandler(String mediaURL){
		this.mediaPath = mediaURL;
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
		ourMediaPlayer = new EmbeddedMediaPlayerComponent();
		ourFrame.setContentPane(ourMediaPlayer);
		ourFrame.setSize(1200,800);
		ourFrame.setVisible(true);
		ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(mediaPath);
	}
	
	public void run(){
		ourMediaPlayer.getMediaPlayer().playMedia(mediaPath);
	}
	
	
}