package asistenteTest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import asistenteVirtual.AsistenteVirtual;

class SaludarTests {

	public final static String USUARIO = "delucas";

	AsistenteVirtual jenkins;

	@Before
	public void setup() {
		jenkins = new AsistenteVirtual("jenkins");
	}

	@Test
	public void saludo() {
		String[] mensajes = { "¡Hola, @jenkins!", "@jenkins hola!",
				"buen día @jenkins", 
				"@jenkins, buenas tardes",
				"hey @jenkins" };
		for (String mensaje : mensajes) {
			assertEquals("¡Hola, @delucas!", jenkins.saludar(mensaje));
		}

	}
}