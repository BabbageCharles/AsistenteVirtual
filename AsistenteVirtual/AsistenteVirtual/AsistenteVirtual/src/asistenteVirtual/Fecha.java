package asistenteVirtual;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Fecha {
	
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	
	public static String darFecha(String s) {

		Calendar c1;
		Locale locale = Locale.getDefault();
		

		if (s.contains("fue")) {
			if (s.contains("ayer")) {
				c1 = Fecha.restarDMA(Calendar.DATE, 1);
			} else {
				int i = Utilitarias.buscarEntero(s);

				if (s.contains("dias"))
					c1 = Fecha.restarDMA(Calendar.DATE, i);
				else if (s.contains("meses"))
					c1 = Fecha.restarDMA(Calendar.MONTH, i);
				else
					c1 = Fecha.restarDMA(Calendar.YEAR, i);
			}

			String mes = c1.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			String dia = c1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
			String fecha = "fue el " + dia + " " + c1.get(Calendar.DATE) + " de " + mes + " de "
					+ c1.get(Calendar.YEAR);

			return fecha;
		}

		if (s.contains("dentro de")) {
			int i = Utilitarias.buscarEntero(s);

			if (s.contains("dias"))
				c1 = Fecha.sumarDMA(Calendar.DATE, i);
			else if (s.contains("meses"))
				c1 = Fecha.sumarDMA(Calendar.MONTH, i);
			else
				c1 = Fecha.sumarDMA(Calendar.YEAR, i);

			String mes = c1.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			String dia = c1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
			String fecha ="ser� el " + dia + " " + c1.get(Calendar.DATE) + " de " + mes + " de "
					+ c1.get(Calendar.YEAR);
			return fecha;
		}

		if (s.contains("pasaron")) {
			Calendar d1 = Fecha.convertToCalendar(s);
			Calendar d2 = GregorianCalendar.getInstance();
			if(FECHA_HORA != null)
				d2.setTime(FECHA_HORA);
			
			String f ="entre el " + Fecha.convertCalendarToString(d1) + " y el "
					+ Fecha.convertCalendarToString(d2) + " pasaron " + Fecha.diasDesde(s) + " d�as";
			return f;
		}

		if (s.contains("faltan")) {
			String f = "faltan " + Fecha.tiempoHasta(s) + " dias";
			return f;
		}

		return " ";

	}

	public static Calendar convertToCalendar(String s) {

		Calendar c1 = GregorianCalendar.getInstance();
		String vec[] = s.split("[^0-9]+"); 		
		int d = Integer.parseInt(vec[1]);
		int m = 0;
		int a;
		if (vec.length > 2) {
			a = Integer.parseInt(vec[2]);
			c1.set(Calendar.YEAR, a);
		}

		String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
				"octumbre", "noviembre", "diciembre" };
		while (!s.contains(meses[m]))
			m++;

		c1.set(Calendar.DAY_OF_MONTH, d);
		c1.set(Calendar.MONTH, m);

		return c1;
	}

	

	public static String convertCalendarToString(Calendar c) {
		Locale locale = Locale.getDefault();
		String mes = c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
		String fecha = c.get(Calendar.DATE) + " de " + mes + " de " + c.get(Calendar.YEAR);

		return fecha;
	}

	
	public static Calendar sumarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		if(FECHA_HORA != null)
			c1.setTime(FECHA_HORA);
		c1.add(i, d);
		return c1;
	}

	
	public static Calendar restarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		if(FECHA_HORA != null)
			c1.setTime(FECHA_HORA);
		c1.add(i, -d);
		return c1;
	}

	
	public static  long diasDesde(String s1) {

		Calendar d1 = Fecha.convertToCalendar(s1);
		Calendar d2 = GregorianCalendar.getInstance();
		if(FECHA_HORA != null)
			d2.setTime(FECHA_HORA);
		long milisec = d2.getTimeInMillis() - d1.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24)+1;
		return days;
	}

	
	public static long tiempoHasta(String s1) {

		Calendar d1 = Fecha.convertToCalendar(s1);													
		Calendar d2 = GregorianCalendar.getInstance();
		if(FECHA_HORA != null)
			d2.setTime(FECHA_HORA);
		long milisec = d1.getTimeInMillis() - d2.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24);
		return days;
	}

}