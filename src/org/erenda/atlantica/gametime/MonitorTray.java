package org.erenda.atlantica.gametime;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorTray
{
	TrayIcon trayIcon;
	Image greenImg;
	Image redImg;
	
	public MonitorTray()
	{
	}
	
	public void initialize()
	{
		if (SystemTray.isSupported())
		{
			greenImg = Toolkit.getDefaultToolkit().getImage("resources/green.png");
			redImg = Toolkit.getDefaultToolkit().getImage("resources/red.png");
			
			SystemTray tray = SystemTray.getSystemTray();
			
			ActionListener exitListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			};
			
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			
			trayIcon = new TrayIcon(redImg, "Tray Demo", popup);
			trayIcon.setImageAutoSize(true);
			
			try
			{
				tray.add(trayIcon);
			}
			catch (AWTException e)
			{
				System.err.println("TrayIcon could not be added.");
			}
		}
	}

	public void changeToGreen()
	{
		trayIcon.setImage(greenImg);
	}

	public void setTooltip(String tooltip)
	{
		trayIcon.setToolTip(tooltip);
	}

	public void displayMessage(String text)
	{
		trayIcon.displayMessage("Notice", text, MessageType.INFO);
	}

	public void changeToRed()
	{
		trayIcon.setImage(redImg);
	}
}
