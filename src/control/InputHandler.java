package control;

public class InputHandler {
	
	private ArduinoHandler arduino;
	private WiiMoteHandler wiimote;
	
	public InputHandler()
	{
		this.arduino = new ArduinoHandler();
		this.wiimote = new WiiMoteHandler();
	}
	
	public void reconnectWii()
	{
		wiimote.reconnect();
	}
	
	public int getY()
	{
		return wiimote.getaY();
	}
	
	public int getX()
	{
		return wiimote.getaX();
	}
}
