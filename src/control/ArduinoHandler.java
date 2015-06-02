package control;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class ArduinoHandler implements SerialPortEventListener {

	private InputStream serialInput;
	private OutputStream serialOutput;
	private BufferedReader serialReader;
	private InputHandler superClass;
	
	public ArduinoHandler(String commPortNumber, InputHandler superClass) {
		this.superClass = superClass;
		try {
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(commPortNumber);
			CommPort commPort = port.open(this.getClass().getName(),2000);
			SerialPort serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialInput = serialPort.getInputStream();
			serialOutput = serialPort.getOutputStream();
			serialReader = new BufferedReader(new InputStreamReader(serialInput));
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			System.out.println("Connected");
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		} catch (PortInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads if a command is send from the adruino to the pc
	 */
	@Override
	public void serialEvent(SerialPortEvent e) {
		if(e.getEventType() == e.DATA_AVAILABLE) {
			String incommingMessage = "";
			try {
				if(serialReader.ready()) {
					incommingMessage = serialReader.readLine();
					//System.out.println("read from serial: " + incommingMessage);
					superClass.inCommingMessage(incommingMessage);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Send a command in the form of a String to the arduino.
	 * @param command - The command you want to send to the adruino.
	 */
//	public void sendCommand(String command) {
//		try {
//			serialOutput.write(command.getBytes());
//			System.out.println("send");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
