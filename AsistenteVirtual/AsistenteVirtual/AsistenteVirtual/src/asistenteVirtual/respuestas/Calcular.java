package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;

public class Calcular implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public final static String USUARIO = "delucas"; // Generar nombre random en

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;

	}

	@Override
	public String escuchar(String pedido) {
		String error = new String("No entiendo el mensaje");

		List<String> calculos = Arrays.asList("Cuánto es", "%", "+", "-", "elevado a", "raiz de", "menos", "*", "5/5",
				":", "^", "dividido", "sqrt", "porciento", "resultado de", "al cuadrado", "al cubo", "a la cuarta",
				"raiz cuadrada", "raíz cúbica");
		String d = calculos.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);

		if (!d.equals(error)) {
			if (pedido.contains("%"))
				return hacerPorcentaje(pedido);
			else {
				if (pedido.contains("(")) {
					int pos = pedido.indexOf("(");
					String s = pedido.substring(pos);
					return calcular(s);
				} else {
					String n = Integer.toString(Utilitarias.buscarEntero(pedido));
					int pos = pedido.indexOf(n);
					String s = pedido.substring(pos);
					return calcular(s);
				}
			}
		}
		return siguiente.escuchar(pedido);

	}

	public static String hacerPorcentaje(String cadena) {
		String[] parts = cadena.split("%");
		String part1 = parts[0];
		String part2 = parts[1];
		String respuesta = "";

		Pattern pattern = Pattern.compile("([0-9.]+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(part1);

		if (matcher.find()) {

			String numero1 = matcher.group(1);
			double porcentaje = Double.parseDouble(numero1);

			Pattern pattern1 = Pattern.compile("([0-9.]+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher1 = pattern1.matcher(part2);

			if (matcher1.find()) {

				String numero2 = matcher1.group(1);
				double num = Double.parseDouble(numero2);

				double rpta = num * porcentaje / 100.0;
				if (rpta % 1 == 0) {
					int n = (int) rpta;
					respuesta = String.valueOf(n);
				} else
					respuesta = String.valueOf(rpta);

			}
		}
		return "@" + USUARIO + " "+ respuesta;
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

			double num = Double.parseDouble(result.toString());
			if (num % 1 == 0) {
				int numero = (int) num;
				res_final = "" + numero;
			} else
				res_final = "" + num;

			return "@" + USUARIO + " "+ res_final;
		} catch (ScriptException se) {

		}

		return null;

	}

}
