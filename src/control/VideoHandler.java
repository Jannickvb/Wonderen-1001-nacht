package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private boolean finished;
	private ControlManager cm;
	
	public VideoHandler(ControlManager control){
		this.finished = false;
		this.cm = control;
		
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        ourMediaPlayer = new EmbeddedMediaPlayerComponent(){
		    public void finished(MediaPlayer mediaPlayer) {
		        ourMediaPlayer.release(); 
		        //ourFrame.dispatchEvent(new WindowEvent(ourFrame, WindowEvent.WINDOW_CLOSING));
		        ourFrame.dispose();
		        finished = true;
		    }
		};
		
		ourMediaPlayer.addKeyListener(new KeyAdapter()
		{
		    public void keyPressed(KeyEvent e) 
		    {
		    	if(e.getKeyCode() == KeyEvent.VK_SPACE)
		    	{
		    		cm.getGameStateManager().next();
		    		
		    		ourFrame.dispose();
		    	}
		    	else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		    	{
		    		System.exit(0);
		    	}
		    }
		});
	}
	
	
	public void handleVideo(String filePath)
	{
		File file = new File(filePath);
		mediaPath = file.getAbsolutePath();
		
		ourMediaPlayer.setFocusable(true);
		ourFrame.setContentPane(ourMediaPlayer);
		ourFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ourFrame.setUndecorated(true);
		ourFrame.setVisible(true);
		ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean getFinished(){
		return finished;
	}
	
	public EmbeddedMediaPlayerComponent getMediaPlayer(){
		return ourMediaPlayer;
	}
	
	public void run(){
		ourMediaPlayer.getMediaPlayer().playMedia(mediaPath);
	}
}