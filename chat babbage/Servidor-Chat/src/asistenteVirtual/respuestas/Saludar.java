package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;

public class Saludar implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public  static String USUARIO; 

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido,String user) {
		List<String> list = Arrays.asList("hola", "hello", "buen dia", "buen día", "buenos dias", "buenas",
				"buenas tardes", "buenas noches", "holis", "holi");
		List<String> list2 = Arrays.asList("chau", "adios", "hasta luego", "bye", "goodbye", "good bye", "saludos",
				"s2","me voy");
		String a = list.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse("No entiendo el mensaje");
		String b = list2.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse("No entiendo el mensaje");
		USUARIO=user;
		if (a != "No entiendo el mensaje")
			return saludar("Hola");
		if (b != "No entiendo el mensaje")
			return saludar("Chau");
		return siguiente.escuchar(pedido,user);

	}

	public static String saludar(String f) {
		if (f.equals("Hola"))
			return "¡Hola, @" + USUARIO + "!";
		return "Espero haberte sido de ayuda, adios!";
	}

}
