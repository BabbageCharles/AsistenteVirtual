package asistenteVirtual.unidadesMedicion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;


public class ConvertorDeUnidades implements AsistenteEscucha {
	private AsistenteEscucha siguiente;
	public static String USUARIO; 

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido,String user) {
		String error = new String("No entiendo el mensaje");

		List<String> unidadDeMasa = Arrays.asList("miligramo", "miligramos", "gramo", "gramos", "kilo", "kilos",
				"dracma", "dracmas", "onza", "onzas", "libra", "libras", "centimetros", "centimetro", "milimetro",
				"milimetros", "Kilometros", "Kilometro", "metros", "metro", "yardas", "yarda", "pulgadas", "pulgada",
				"pies", "pie", "segundo", "segundos", "minuto", "minutos");
		String e = unidadDeMasa.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);
		USUARIO=user;
		if (!e.equals(error))
			return darConversion(pedido);
		return siguiente.escuchar(pedido,user);
	}

	public static String darConversion(String cadena) {

		double resultado = 0;
		Integer cantidad = Utilitarias.buscarEntero(cadena);
		if(cantidad == (float)-1)
			return "Lo siento, ingresa un numero para poder realizar tu pedido";
		String paquete = ConvertorDeUnidades.class.getPackage().getName();
		String unidadOriginal = ConvertorDeUnidades.buscarUnidad(cadena.substring(cadena.indexOf(cantidad.toString())));
		String unidadNueva = ConvertorDeUnidades.buscarUnidad(cadena.substring(0, cadena.indexOf(cantidad.toString())));

		try {
			Class<?> UnidadaOriginalClass = Class.forName(paquete + "." + unidadOriginal);
			Class<?> UnidadaNuevaClass = Class.forName(paquete + "." + unidadNueva);

			Constructor<?> constructor = UnidadaOriginalClass.getConstructor(double.class);
			Object uOri = constructor.newInstance(cantidad);

			Constructor<?> constructor2 = UnidadaNuevaClass.getConstructor(double.class);
			Object uNue = constructor2.newInstance(0);

			Method methodConvert = UnidadaOriginalClass.getDeclaredMethod("convertirA", uNue.getClass());
			uNue = methodConvert.invoke(uOri, uNue);

			Method methodGetCant = uNue.getClass().getDeclaredMethod("getCantidad");
			resultado = (double) methodGetCant.invoke(uNue);

		} catch (Exception e) {
			return "@"+ USUARIO+" "+ "no se pueden hacer conversiones de dos unidades distintas";
		}

		return "@" + USUARIO + " "
				+ ConvertorDeUnidades.darFormatoSalida(unidadOriginal, unidadNueva, cantidad, resultado);

	}

	public static String buscarUnidad(String cadena) {
		String uni[] = { "Miligramo", "Gramo", "Kilo", "Dracma", "Onza", "Libra", "Milimetro", "Centimetro", "Metro",
				"Kilometro", "Pie", "Pulgada", "Yarda", "Segundo", "Minuto", "Hora" };

		String unidad;
		int i = 0;

		while (!cadena.contains(uni[i].toLowerCase()))
			i++;

		unidad = uni[i];

		if (cadena.contains("cúbico") || cadena.contains("cúbica"))
			unidad = unidad + "Cubico";

		return unidad;

	}

	public static String darFormatoSalida(String uniOri, String uniNue, Integer cantOri, double cantNue) {

		DecimalFormat df = new DecimalFormat("#.#######");
		String aux = " equivale a ";

		if (cantNue != 1) {
			if (uniNue.contains("Cubico"))
				uniNue = uniNue.substring(0, uniNue.indexOf("Cubico")).toLowerCase() + "s" + " cúbicos";
			else
				uniNue = uniNue.toLowerCase() + "s";
		} else {
			if (uniNue.contains("Cubico"))
				uniNue = uniNue.substring(0, uniNue.indexOf("Cubico")).toLowerCase() + " cúbico";
		}

		if (cantOri != 1) {
			if (uniOri.contains("Cubico"))
				uniOri = uniOri.substring(0, uniOri.indexOf("Cubico")).toLowerCase() + "s" + " cúbicos";
			else
				uniOri = uniOri.toLowerCase() + "s";
			aux = " equivalen a ";
		} else {
			if (uniOri.contains("cúbico"))
				uniOri = uniOri.substring(0, uniOri.indexOf("Cubico")).toLowerCase() + " cúbico";
		}

		String res = cantOri + " " + uniOri.toLowerCase() + aux + df.format(cantNue).replace(",", ".") + " "
				+ uniNue.toLowerCase();

		return res;

	}

}
