package control;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class WiiMoteHandler implements WiimoteListener{
	
	private Wiimote[] wiimotes;
	private int aX;
	private int aY;
	
	public WiiMoteHandler()
	{
		wiimotes = WiiUseApiManager.getWiimotes(2, true);
		for(int i = 0;i<wiimotes.length;i++){
			wiimotes[i].addWiiMoteEventListeners((WiimoteListener) this);
			wiimotes[i].activateMotionSensing();
			wiimotes[i].activateIRTRacking();
			wiimotes[i].setSensorBarBelowScreen();
			}
	}
	
	public void reconnect()
	{
		wiimotes = WiiUseApiManager.getWiimotes(2, true);
		for(int i = 0;i<wiimotes.length;i++){
			wiimotes[i].addWiiMoteEventListeners((WiimoteListener) this);
			wiimotes[i].activateMotionSensing();
			wiimotes[i].activateIRTRacking();
			wiimotes[i].setSensorBarBelowScreen();
			}
	}
	
	public boolean isConnected()
	{
		boolean connected = true;
		if(wiimotes.length < 2)
		{
			connected = false;
		}
		return connected;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e) {
		// TODO Auto-generated method stub
		if(e.isButtonAJustPressed())
		{
			//start game
		}
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIrEvent(IREvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Ax:" + e.getAx() + "Ay:" + e.getAy());
		//System.out.println("X:" + e.getX() + "Y:" + e.getY());
		setaX(e.getAx());
		setaY(e.getAy());
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusEvent(StatusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getaY() {
		return aY;
	}

	public void setaY(int aY) {
		this.aY = aY;
	}

	public int getaX() {
		return aX;
	}

	public void setaX(int aX) {
		this.aX = aX;
	}
}
