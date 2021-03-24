package com.jdo.poc.uti;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final SimpleDateFormat SDF_DDMMYYYHHMMSS = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss");

	public static Date add(Date dataInicio, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataInicio);
		c.add(Calendar.MINUTE, amount);
		return c.getTime();
	}

	public static Date parseDDMMYYYHHMMSS(String dataHora) throws Exception {
		return SDF_DDMMYYYHHMMSS.parse(dataHora);
	}

	public static Date getInicioDia(Date dataInicio) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataInicio);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getFimDia(Date dataInicio) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataInicio);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Date addHour(Date dataHora, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataHora);
		c.add(Calendar.HOUR_OF_DAY, amount);
		return c.getTime();
	}

}
