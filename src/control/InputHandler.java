package control;

public class InputHandler {
	
	private ArduinoHandler arduino;
	private WiiMoteHandler wiimote;
	
	public InputHandler()
	{
		this.arduino = new ArduinoHandler("COM3",this);
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
