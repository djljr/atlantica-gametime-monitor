package org.erenda.atlantica.gametime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class GameTimeMonitor
{
	public static void main(String[] args)
	{
		String timeZeroFileName = "time-zero.tz";
		
		CommandLineParser parser = new PosixParser();
		
		Options options = new Options();
		options.addOption("h", "hour", true, "Hour of day");
		options.addOption("d", "day", true, "Day of month");
		options.addOption("m", "month", true, "Month of year");
		options.addOption("y", "year", true, "Year");
		options.addOption("t", "time-zero", true, "Time-Zero file");
		
		CommandLine line = null;
		try
		{
			line = parser.parse( options, args );
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return;
		}
		
		long hour, day, month, year;
		
		File tzFile;
		
		if(!line.hasOption("time-zero"))
			tzFile = new File(timeZeroFileName); 
		else
			tzFile = new File(line.getOptionValue("time-zero"));
		
		if(!line.hasOption("hour") || !line.hasOption("day") || !line.hasOption("month") || !line.hasOption("year"))
		{			
			if(!tzFile.exists())
			{
				showHelp(options);
				return;
			}
			
			ObjectInputStream ois = null;
			try
			{
				ois = new ObjectInputStream(new FileInputStream(tzFile));
				ois.readObject();
			}
			catch (Exception e)
			{
				showHelp(options);
				return;
			}
			finally
			{
				try
				{
					if(ois != null)
						ois.close();
				}
				catch (Exception e) { }
			}
		}
		else
		{
			hour = Long.valueOf(line.getOptionValue("hour"));
			day = Long.valueOf(line.getOptionValue("day"));
			month = Long.valueOf(line.getOptionValue("month"));
			year = Long.valueOf(line.getOptionValue("year"));
			
			TimeZero.initTimeZeroFromGameTime(hour, month, day, year);
			ObjectOutputStream oos = null;
			try
			{
				oos = new ObjectOutputStream(new FileOutputStream(tzFile));
				oos.writeObject(TimeZero.getInstance());
			}
			catch(Exception e)
			{
				
			}
			finally 
			{
				try
				{
					if(oos != null)
						oos.close();
				}
				catch (Exception e) { }
			}
		}
		
		final MonitorTray tray = new MonitorTray();
		tray.initialize();
		
		final List<TimeListener> listeners = new LinkedList<TimeListener>();
		listeners.add(new ColumbusListener());
		listeners.add(new TooltipListener());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run()
			{
				AtlanticaTime time = AtlanticaTime.now();
				for(TimeListener tl : listeners)
				{
					tl.onTick(tray, time);
				}
			}
		}, 0, 120000);
	}

	private static void showHelp(Options options)
	{
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "GameTimeMonitor", options );
	}
}
