package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;

public class Agradecer implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public static String USUARIO;
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido,String user) {
		String error = new String("No entiendo el mensaje");
		List<String> agradecimientos = Arrays.asList("agradezco", "gracias", "thanks", "ty", "thank you", "arigato");
		String b = agradecimientos.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse(error);
		USUARIO=user;
		if (!b.equals(error))
			return agradecer();
		return siguiente.escuchar(pedido,USUARIO);
	}

//	public final static String USUARIO = "delucas"; // Generar nombre random en

	public static String agradecer() {
		return "No es nada, @" + USUARIO;
	}

}
