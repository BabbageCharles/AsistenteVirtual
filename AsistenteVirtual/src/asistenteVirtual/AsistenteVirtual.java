package asistenteVirtual;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AsistenteVirtual {

	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();

	// CONSTRUCTORES
	public AsistenteVirtual(String s) {
	}

	public AsistenteVirtual() {
	}

	// SALUDAR/*************************************************************************/
	public static String saludar(String f) {
		List<String> list = Arrays.asList("hola", "buen d�a", "buenas tardes", "buenas noches", "hello", "hi", "hey",
				"buenos dias");
		List<String> list2 = Arrays.asList("chau", "adios", "hasta luego", "bye", "goodbye", "good bye", "saludos",
				"s2");
		String a = list.stream().filter(x -> f.toLowerCase().contains(x)).findFirst().orElse("No entiendo el mensaje");
		String b = list2.stream().filter(x -> f.toLowerCase().contains(x)).findFirst().orElse("No entiendo el mensaje");
		if (a == "No entiendo el mensaje" && b == "No entiendo el mensaje")
			return a;
		if (a != "No entiendo el mensaje")
			return "�Hola, @" + USUARIO + "!";
		return "Espero haberte sido de ayuda, adios!";
	}

	// AGRADECER/*************************************************************************/
	public static String agradecer(String f) {
		return "No es nada, @" + USUARIO;
	}

	// FECHA/*************************************************************************/
	public static String darFecha(String s) {

		Calendar c1;
		Locale locale = Locale.getDefault();
		AsistenteVirtual nuevo = new AsistenteVirtual();

		if (s.contains("fue")) {
			if (s.contains("ayer")) {
				c1 = nuevo.restarDMA(Calendar.DATE, 1);
			} else {
				int i = AsistenteVirtual.buscarEntero(s);

				if (s.contains("dias"))
					c1 = nuevo.restarDMA(Calendar.DATE, i);
				else if (s.contains("meses"))
					c1 = nuevo.restarDMA(Calendar.MONTH, i);
				else
					c1 = nuevo.restarDMA(Calendar.YEAR, i);
			}

			String mes = c1.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			String dia = c1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
			String fecha = "@" + USUARIO + " fue el " + dia + " " + c1.get(Calendar.DATE) + " de " + mes + " de "
					+ c1.get(Calendar.YEAR);

			return fecha;
		}

		if (s.contains("dentro de")) {
			int i = AsistenteVirtual.buscarEntero(s);

			if (s.contains("dias"))
				c1 = nuevo.sumarDMA(Calendar.DATE, i);
			else if (s.contains("meses"))
				c1 = nuevo.sumarDMA(Calendar.MONTH, i);
			else
				c1 = nuevo.sumarDMA(Calendar.YEAR, i);

			String mes = c1.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
			String dia = c1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
			String fecha = "@" + USUARIO + " ser� el " + dia + " " + c1.get(Calendar.DATE) + " de " + mes + " de "
					+ c1.get(Calendar.YEAR);
			return fecha;
		}

		if (s.contains("pasaron")) {
			Calendar d1 = nuevo.convertToCalendar(s);
			Calendar d2 = GregorianCalendar.getInstance();
			d2.setTime(FECHA_HORA);
			String f = "@" + USUARIO + " entre el " + nuevo.convertCalendarToString(d1) + " y el "
					+ nuevo.convertCalendarToString(d2) + " pasaron " + nuevo.diasDesde(s) + " d�as";
			return f;
		}

		if (s.contains("faltan")) {
			String f = "@" + USUARIO + " faltan " + nuevo.tiempoHasta(s) + " dias";
			return f;
		}

		return " ";

	}

	public Calendar convertToCalendar(String s) {

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

	public static int buscarEntero(String s) {
		String regexp = "([0-9]+)";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		int i = Integer.parseInt(matcher.group());

		return i;
	}

	public String convertCalendarToString(Calendar c) {
		Locale locale = Locale.getDefault();
		String mes = c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
		String fecha = c.get(Calendar.DATE) + " de " + mes + " de " + c.get(Calendar.YEAR);

		return fecha;
	}

	
	public Calendar sumarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTime(FECHA_HORA);
		c1.add(i, d);
		return c1;
	}

	
	public Calendar restarDMA(int i, int d) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTime(FECHA_HORA);
		c1.add(i, -d);
		return c1;
	}

	
	public long diasDesde(String s1) {

		Calendar d1 = this.convertToCalendar(s1);
		Calendar d2 = GregorianCalendar.getInstance();
		d2.setTime(FECHA_HORA);
		long milisec = d2.getTimeInMillis() - d1.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24)+1;
		return days;
	}

	
	public long tiempoHasta(String s1) {

		Calendar d1 = this.convertToCalendar(s1);													
		Calendar d2 = GregorianCalendar.getInstance();
		d2.setTime(FECHA_HORA);
		long milisec = d1.getTimeInMillis() - d2.getTimeInMillis();
		long days = milisec / (1000 * 60 * 60 * 24);
		return days;
	}

	// CALCULAR/*************************************************************************/
	public static String hacerPorcentaje(String cadena) {
		String[] parts = cadena.split("%");
		String part1 = parts[0];
		String part2 = parts[1];
		String respuesta = "";

		Pattern pattern = Pattern.compile("([0-9.]+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(part1);

		if (matcher.find()) {

			String numero1 = matcher.group(1);			
			double porcentaje = Integer.parseInt(numero1);

			Pattern pattern1 = Pattern.compile("([0-9]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher1 = pattern1.matcher(part2);

			if (matcher1.find()) {

				String numero2 = matcher1.group(1);
				double num = Integer.parseInt(numero2);
				double rpta = num * porcentaje / 100.0;
				if(rpta % 1 == 0) {
					int n = (int) rpta;
					respuesta = "@" + USUARIO +" "+ n;
				}
				else
					respuesta = "@" + USUARIO +" "+ rpta;			

			}
		}
		return respuesta;
	}

	public static String calcular(String expression) {
		String res_final;
		String expressionfinal = "";
		double num1, num2, resultado;
		int decremento = 0;
		int indexini = 0;
		int indexbegin = 0;
		String n;
		int indexend = 0;
		int principio;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");

		try {

			char[] expressionarray = expression.toCharArray();

			for (int i = 0; i < expressionarray.length; i++) {

				if (expressionarray[i] == '^') {

					// AGREGAR RECURSIVIDAD
					while (expressionarray[i - 1 - decremento] == ' ')
						decremento++;
					decremento++;
					
					if (expressionarray[i - decremento] == ')') {
						int aux;
						aux = decremento;
						while (expressionarray[i - decremento] != '(')
							decremento++;
						String operacion = expression.substring(i - decremento, i - aux + 1);
						
						operacion = calcular(operacion);
						
						num1 = Double.parseDouble(operacion);
						indexbegin = i - decremento;

					} else {

						indexend = i - decremento + 1;
						

						principio = decremento;
						try {
							while ((expressionarray[i - 1 - principio] >= '0'
									&& expressionarray[i - 1 - principio] <= '9')
									|| expressionarray[i - 1 - principio] == '.')
								principio++;
						} catch (Exception a) {
						}

						indexbegin = i - principio;
						
						n = expression.substring(indexbegin, indexend);
						num1 = Double.parseDouble(n);
					}

					
					decremento = 0;
					principio = 0;

					// AGREGAR RECURSIVIDAD
					int aumento = 0;

					while (expressionarray[i + 1 + aumento] == ' ')
						aumento++;
					int indexbegin2 = i + 1 + aumento;
					
					if (expressionarray[i + aumento + 1] == '(') {
						int aux;
						aux = aumento;

						while (expressionarray[i + aumento] != ')')
							aumento++;

						
						String operacion = expression.substring(i + aux + 1, i + aumento + 1);
						
						operacion = calcular(operacion);
						
						num2 = Double.parseDouble(operacion);
						indexend = i + aumento + 1;

					} else {

						try {
							while ((expressionarray[i + 1 + aumento] >= '0' && expressionarray[i + 1 + aumento] <= '9')
									|| expressionarray[i + 1 + aumento] == '.')
								aumento++;
						} catch (Exception a) {
						}

						indexend = i + aumento + 1;
						
						String m = expression.substring(indexbegin2, indexend);
						num2 = Double.parseDouble(m);
						
					}

					resultado = Math.pow(num1, num2);
					

					String res = String.valueOf(resultado);
					String a = expression.substring(indexini, indexbegin);
					expressionfinal = expressionfinal + a + res;
					indexini = indexend;

				}

				// AGREGAR RECURSIVIDAD
				if (expressionarray[i] == 'r' || expressionarray[i] == 'R') {
					int aumento = 0;
					indexbegin = 0;
					indexend = 0;

					double raiz = expressionarray[i + 1] - '0';
					
					double numero;
					while (expressionarray[i + 2 + aumento] == ' ')
						aumento++;
					indexbegin = i + 2 + aumento;

					try {
						while ((expressionarray[i + 2 + aumento] >= '0' && expressionarray[i + 2 + aumento] <= '9')
								|| expressionarray[i + 2 + aumento] == '.')
							aumento++;
					} catch (Exception e) {
					}

					indexend = i + aumento + 2;

					String num = expression.substring(indexbegin, indexend);
					

					numero = Double.parseDouble(num);
					numero = Math.pow(numero, 1.0 / raiz);

					String res = String.valueOf(numero);
					String a = expression.substring(indexini, i);

					expressionfinal = expressionfinal + a + res;
					indexini = indexend;

					

				}

			}

			String resto = expression.substring(indexini, expression.length());
			expressionfinal = expressionfinal + resto;

			Object result = engine.eval(expressionfinal);
			
			double num= Double.parseDouble(result.toString());
			if(num %1 == 0) {
				int numero = (int) num;
				res_final = "@" + USUARIO +" "+ numero;
			}
			else
				res_final = "@" + USUARIO +" "+ num;

			return res_final;

			

		} catch (ScriptException se) {
			
		}

		return null;

	}
}