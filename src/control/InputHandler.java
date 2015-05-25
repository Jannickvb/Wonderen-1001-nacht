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
	
	public int getY1()
	{
		return wiimote.getp1aY();
	}
	
	public int getX1()
	{
		return wiimote.getp1aX();
	}
	
	public int getY2()
	{
		return wiimote.getP2aY();
	}
	
	public int getX2()
	{
		return wiimote.getP2aX();
	}
}
