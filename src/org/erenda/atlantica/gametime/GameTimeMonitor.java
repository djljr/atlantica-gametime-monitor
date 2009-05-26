package org.erenda.atlantica.gametime;

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
		CommandLineParser parser = new PosixParser();
		
		Options options = new Options();
		options.addOption("h", "hour", true, "Hour of day");
		options.addOption("d", "day", true, "Day of month");
		options.addOption("m", "month", true, "Month of year");
		options.addOption("y", "year", true, "Year");
		
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
		
		if(!line.hasOption("hour") || !line.hasOption("day") || !line.hasOption("month") || !line.hasOption("year"))
		{
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "GameTimeMonitor", options );
			return;
		}
			
		hour = Long.valueOf(line.getOptionValue("hour"));
		day = Long.valueOf(line.getOptionValue("day"));
		month = Long.valueOf(line.getOptionValue("month"));
		year = Long.valueOf(line.getOptionValue("year"));
		
		TimeZero.initTimeZeroFromGameTime(hour, month, day, year);
	}
}
