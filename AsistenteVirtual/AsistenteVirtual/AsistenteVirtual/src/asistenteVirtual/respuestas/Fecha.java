package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;

public class Fecha implements AsistenteEscucha {

	public final static String USUARIO = "delucas"; // Generar nombre random en
	private AsistenteEscucha siguiente;

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido) {

		String error = new String("No entiendo el mensaje");

		List<String> fechas = Arrays.asList("qué día será", "que dia sera", "que dia", "cuantos dias", "que dia será",
				"lunes", "martes", "miércoles", "jueves", "viernes", "cuántos dias", "qué día será dentro de",
				"qué dia", "cuánto falta", "qué dia", "cuántas semanas", "cuántos meses", "cuantas semanas",
				"cuantos meses", "hace cuánto", "pasaron desde", "faltan hasta", "días para", "semanas para",
				"meses para", "la hora", "hora es", "la fecha por favor", "¿qué día es", "hoy", "me decís la fecha");
		String c = fechas.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);
		if (!c.equals(error))
			return darFecha(pedido);
		return siguiente.escuchar(pedido);
	}

	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();

	public static String darFecha(String s) {

		Calendar c1 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			c1.setTime(FECHA_HORA);
		Calendar d2 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			d2.setTime(FECHA_HORA);
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

			return "@" + USUARIO + " " + fecha;
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
			String fecha = "será el " + dia + " " + c1.get(Calendar.DATE) + " de " + mes + " de "
					+ c1.get(Calendar.YEAR);
			return "@" + USUARIO + " " + fecha;
		}

		if (s.contains("pasaron")) {
			Calendar d1 = Fecha.convertToCalendar(s);
			String f = "entre el " + Fecha.convertCalendarToString(d1) + " y el " + Fecha.convertCalendarToString(d2)
					+ " pasaron " + Fecha.diasDesde(s) + " días";
			return "@" + USUARIO + " " + f;
		}

		if (s.contains("faltan")) {
			String f = "faltan " + Fecha.tiempoHasta(s) + " dias";
			return "@" + USUARIO + " " + f;
		}

		if (s.contains("hora")) {
			String f = "son las " + Fecha.getHora(c1);
			return "@" + USUARIO + " " + f;
		}

		if (s.contains("qué día es") || s.contains("la fecha")) {
			String f = "hoy es " + Fecha.convertCalendarToString(c1);
			return "@" + USUARIO + " " + f;
		}

		if (s.contains("hoy")) {
			String f = "hoy es " + c1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
			return "@" + USUARIO + " " + f;
		}
		return "@" + USUARIO + " " + " ";

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

	public static String getHora(Calendar c1) {
		int h = c1.get(Calendar.HOUR_OF_DAY);
		int hora = c1.get(Calendar.HOUR);
		String min = String.format("%02d", c1.get(Calendar.MINUTE));
		String res = "";

		if (h == 12)
			hora = 12;

		if (h == 0) {
			hora = 12;
			res = hora + ":" + min + " AM";
		}

		if (h < 12 && h > 0)
			res = hora + ":" + min + " AM";

		if (h >= 12)
			res = hora + ":" + min + " PM";

		return res;
	}

	public static String convertCalendarToString(Calendar c) {
		Locale locale = Locale.getDefault();
		String mes = c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
		String fecha = c.get(Calendar.DATE) + " de " + mes + " de " + c.get(Calendar.YEAR);

		return fecha;
	}

	public static Calendar sumarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			c1.setTime(FECHA_HORA);
		c1.add(i, d);
		return c1;
	}

	public static Calendar restarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			c1.setTime(FECHA_HORA);
		c1.add(i, -d);
		return c1;
	}

	public static long diasDesde(String s1) {

		Calendar d1 = Fecha.convertToCalendar(s1);
		Calendar d2 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			d2.setTime(FECHA_HORA);
		long milisec = d2.getTimeInMillis() - d1.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24)+1 ;
		return days;
	}

	public static long tiempoHasta(String s1) {

		Calendar d1 = Fecha.convertToCalendar(s1);
		Calendar d2 = GregorianCalendar.getInstance();
		if (FECHA_HORA != null)
			d2.setTime(FECHA_HORA);
		long milisec = d1.getTimeInMillis() - d2.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24);
		return days;
	}

}
