package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;

public class Saludar implements AsistenteEscucha {

	private AsistenteEscucha siguiente;

	public final static String USUARIO = "delucas"; // Generar nombre random en elconstructor

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido) {
		List<String> list = Arrays.asList("hola", "hello", "buen dia", "buen día", "hey", "buenos dias", "buenas",
				"buenas tardes", "buenas noches", "alo", "holis", "holi", "hi");
		List<String> list2 = Arrays.asList("chau", "adios", "hasta luego", "bye", "goodbye", "good bye", "saludos",
				"s2");
		String a = list.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse("No entiendo el mensaje");
		String b = list2.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse("No entiendo el mensaje");

		if (a != "No entiendo el mensaje")
			return saludar("Hola");
		if (b != "No entiendo el mensaje")
			return saludar("Chau");
		return siguiente.escuchar(pedido);

	}

	public static String saludar(String f) {
		if (f.equals("Hola"))
			return "¡Hola, @" + USUARIO + "!";
		return "Espero haberte sido de ayuda, adios!";
	}

}
