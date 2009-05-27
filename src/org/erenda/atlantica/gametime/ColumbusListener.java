package org.erenda.atlantica.gametime;


public class ColumbusListener implements TimeListener
{
	boolean inColumbusTime = false;
	
	@Override
	public void onTick(MonitorTray tray, AtlanticaTime time)
	{
		if(time.getHour() >= 9 && time.getHour() < 18 && !inColumbusTime)
		{
			inColumbusTime = true;
			tray.changeToGreen();
			tray.displayMessage("Columbus Time Started");
		}
	}

}
