package org.erenda.atlantica.gametime;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorTray
{
	final TrayIcon trayIcon;
	final Image greenImg;
	final Image redImg;
	
	public MonitorTray()
	{
		greenImg = Toolkit.getDefaultToolkit().getImage("resources/green.png");
		redImg = Toolkit.getDefaultToolkit().getImage("resources/red.png");
		
		trayIcon = new TrayIcon(redImg, "Atlantica Monitor");
		
		ActionListener exitListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Exiting...");
				System.exit(0);
			}
		};
		
		PopupMenu popup = new PopupMenu();
		MenuItem defaultItem = new MenuItem("Exit");
		defaultItem.addActionListener(exitListener);
		popup.add(defaultItem);
	}
}
