package org.erenda.atlantica.gametime;

import java.util.Date;

public class AtlanticaTime
{
	
	public AtlanticaTime(int hour, int day, int month, int year)
	{
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public static AtlanticaTime now()
	{
		return fromDate(new Date());
	}
	public static AtlanticaTime fromDate(Date d)
	{
		TimeZero tz = TimeZero.getInstance();
		long timeZero = tz.getTimeZero();
		
		long atlantica_ts = d.getTime() / 1000 - timeZero;
		long hours = atlantica_ts / 120;
		long days = hours / 24;
		hours = hours - days * 24;
		long months = days / 30;
		days = days - months * 30;
		if (days == 0)
		{
			days = 30;
			months = months - 1;
		}
		
		long years = months / 12;
		months = months - years * 12;
		if(months == 0)
		{
			months = 12;
			years = years - 1;
		}
		
		return new AtlanticaTime((int)hours, (int)days, (int)months, (int)years);		
	}
	
	
	private int hour;
	private int month;
	private int day;
	private int year;
	
	public int getHour()
	{
		return hour;
	}
	public int getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	public int getYear()
	{
		return year;
	}
	@Override
	public String toString()
	{
		return hour + " hrs " + month + "/" + day + "/" + year;
	}
	
}
