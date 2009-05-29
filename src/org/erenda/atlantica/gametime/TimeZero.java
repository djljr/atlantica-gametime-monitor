package org.erenda.atlantica.gametime;

import java.io.Serializable;
import java.util.Date;

public final class TimeZero implements Serializable
{
	private long timeZero;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TimeZero()
	{
		
	}
	
	private static TimeZero instance = new TimeZero();
	private boolean initialized = false;
	public static TimeZero getInstance() 
	{
		if(!instance.initialized)
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
		
		instance.setTimeZero(time0);
		instance.initialized = true;
	}
	
	private void setTimeZero(long timeZero)
	{
		this.timeZero = timeZero;
	}
	
    protected Object readResolve() 
    {
    	instance.initialized = initialized;
    	instance.timeZero = timeZero;
    	return instance;
    }
}
