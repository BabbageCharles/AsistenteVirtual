package asistenteVirtual.respuestas;

import asistenteVirtual.AsistenteEscucha;

public class Default implements AsistenteEscucha {

	public static String USUARIO = "delucas"; // Generar nombre random en elconstructor

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		throw new RuntimeException("No se puede agregar otro comportamiento");
	}

	@Override
	public String escuchar(String pedido,String user) {
		USUARIO=user;
		return "Disculpa... no entiendo el pedido, @"+ USUARIO + " ¿podrías repetirlo?";
	
	
	}
}
