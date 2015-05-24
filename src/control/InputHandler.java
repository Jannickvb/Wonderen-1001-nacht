package control;

public class InputHandler {
	
	private ArduinoHandler arduino;
	private WiiMoteHandler wiimote;
	//Pressure plate iets;
	
	public InputHandler()
	{
		this.arduino = new ArduinoHandler("COM3",this);
		this.wiimote = new WiiMoteHandler();
	}
	
	//Wii stuff:
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
	
	
	//Adruino stuff:
	
	public void inCommingMessage(String message) {
		String code = message.substring(0,1);
		switch(code) {
			case "PP": //Pressure plates;
				
				break;
		}
	}
	
	public void getPressurePlates() {
		arduino.sendCommand("GPP");
		
	}
	
	public void setLed1(boolean state) {
		if(state) 
			arduino.sendCommand("L1E");
		else
			arduino.sendCommand("L1D");
	}
	
	
	
}
