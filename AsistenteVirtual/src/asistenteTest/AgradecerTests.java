package asistenteTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import asistenteVirtual.AsistenteEscucha;

class AgradecerTests {

	String salida;
	AsistenteEscucha jenkins;

	@Before
	public void setUp() {
		salida = new String();
		jenkins = new AsistenteEscucha();
	}

	@Test
	public void agradecimiento() {
		String[] mensajes = { "�Muchas gracias, @jenkins!", "@jenkins gracias", "gracias @jenkins" };
		for (String mensaje : mensajes) {
			assertEquals("No es nada, @delucas", jenkins.agradecer(mensaje));
		}
	}
}