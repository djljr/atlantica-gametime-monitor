package org.erenda.atlantica.gametime;

public class TooltipListener implements TimeListener
{

	@Override
	public void onTick(MonitorTray tray, AtlanticaTime time)
	{
		tray.setTooltip(time.toString());
	}
}
