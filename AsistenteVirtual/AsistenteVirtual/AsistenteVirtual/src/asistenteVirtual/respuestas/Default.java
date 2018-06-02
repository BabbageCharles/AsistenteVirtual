package asistenteVirtual.respuestas;

import asistenteVirtual.AsistenteEscucha;

public class Default implements AsistenteEscucha {

	public final static String USUARIO = "delucas"; // Generar nombre random en elconstructor

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		throw new RuntimeException("No se puede agregar otro comportamiento");
	}

	@Override
	public String escuchar(String pedido) {
		return "Disculpa... no entiendo el pedido, @"+ USUARIO + " ¿podrías repetirlo?";
	
	
	}
}
