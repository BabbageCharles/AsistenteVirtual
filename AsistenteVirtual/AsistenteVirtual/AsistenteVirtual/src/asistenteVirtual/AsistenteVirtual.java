package asistenteVirtual;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AsistenteVirtual {

	public final static String USUARIO = "delucas"; //Generar nombre random en el constructor
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();//se generaria en el constructor

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
	public static String darFecha(String cadena) {
		String res =Fecha.darFecha(cadena);
		return "@"+USUARIO+" "+res;
	}
	
	// Calcular/*************************************************************************/
	public static String calculo(String cadena) {
		String res =Calcular.calcular(cadena);
		return "@"+USUARIO+" "+res;
	}
	
	public static String porcentaje(String cadena) {
		String res=Calcular.hacerPorcentaje(cadena);
		return "@"+USUARIO+" "+res;
	}

	
	
}