package control;

public class InputHandler {
	
	private ArduinoHandler arduino;
	private WiiMoteHandler wiimote;
	
	//Arduino fields: 
	private boolean pressurePlate1;
	private boolean pressurePlate2;
	private boolean pressurePlate3;
	private boolean pressurePlate4;
	
	public InputHandler()
	{
		this.arduino = new ArduinoHandler("COM3",this);
		this.wiimote = new WiiMoteHandler();
		resetPressurePlates();
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
	
	public boolean getAPressed()
	{
		return wiimote.getAPressed();
	}
	
	public boolean getAReleased()
	{
		return wiimote.getAReleased();
	}
	
	//Adruino stuff:
	
	/**
	 * Recieves incomming messages from the arduino and handles them.
	 * @param message - The incomming messages from the arduino.
	 */
		public void inCommingMessage(String message) {
			String code = message.substring(0,2);
			switch(code) {
				case "PP": //Pressure plates;
					resetPressurePlates();
					for(int x = 2; x < message.length(); x++) {
						int pressurePlate = Integer.parseInt(message.substring(x, x+1));
						switch(pressurePlate) {
							case 1:
								pressurePlate1 = true;
								break;
							case 2:
								pressurePlate2 = true;
								break;
							case 3:
								pressurePlate3 = true;
								break;
							case 4:
								pressurePlate4 = true;
								break;
						}
					}
					break;
			}
		}
		
		/**
		 * Reset the state of the pressure plates.
		 */
		public void resetPressurePlates() {
			pressurePlate1 = false;
			pressurePlate2 = false;
			pressurePlate3 = false;
			pressurePlate4 = false;
		}
		
		/**
		 * Lets you turn on/off the reading of the pressure plates.
		 * @param state - If you want to read the pressure plates or not.
		 */
		public void turnPressurePlates(boolean state) {
			if(state) 
				arduino.sendCommand("EPP");
			else
				arduino.sendCommand("DPP");
		}
		
		/**
		 * Lets you turn led number 1 on or off.
		 * @param state - Turn the led on or off.
		 */
		public void setLed1(boolean state) {
			if(state) 
				arduino.sendCommand("L1E");
			else
				arduino.sendCommand("L1D");
		}
		
		public boolean getPressurePlate1() {
			return pressurePlate1;
		}
		
		public boolean getPressurePlate2() {
			return pressurePlate2;
		}
		
		public boolean getPressurePlate3() {
			return pressurePlate3;
		}
		
		public boolean getPressurePlate4() {
			return pressurePlate4;
		}
}
