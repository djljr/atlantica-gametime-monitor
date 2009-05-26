package org.erenda.atlantica.gametime;

import java.io.Serializable;
import java.util.Date;

public class TimeZero implements Serializable
{
	long timeZero;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TimeZero()
	{
		
	}
	
	private static TimeZero instance;
	public static TimeZero getInstance() 
	{
		if(instance == null)
			throw new RuntimeException("time zero not properly initialized");
		
		return instance;
	}
	
	public long getTimeZero()
	{
		return timeZero;
	}
	
	public static void initTimeZeroFromGameTime(long hour, long month, long day, long year)
	{
		Date now = new Date();
		
		long time0 = now.getTime() / 1000;
		time0 = time0 - hour * 120;
		time0 = time0 - day * 24 * 120;
		time0 = time0 - month * 30 * 24 * 120;
		time0 = time0 - year * 12 * 30 * 24 * 120;
		
		instance = new TimeZero();
		instance.setTimeZero(time0);
	}
	
	private void setTimeZero(long timeZero)
	{
		this.timeZero = timeZero;
	}
}
