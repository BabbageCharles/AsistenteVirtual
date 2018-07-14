package asistenteTest;

import static org.junit.Assert.assertEquals;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Pedido;
import asistenteVirtual.respuestas.Agradecer;
import asistenteVirtual.respuestas.Calcular;
import asistenteVirtual.respuestas.ChuckNorrisFacts;
import asistenteVirtual.respuestas.Default;
import asistenteVirtual.respuestas.Fecha;
import asistenteVirtual.respuestas.LeyesRobotica;
import asistenteVirtual.respuestas.Saludar;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class SaludarTests {

	public final static String USUARIO = "delucas";
	Pedido pedido = new Pedido();

	AsistenteEscucha jenkins = new Saludar();
	AsistenteEscucha agradecer = new Agradecer();
	AsistenteEscucha calcular = new Calcular();
	AsistenteEscucha fecha = new Fecha();
	AsistenteEscucha conversor = new ConvertorDeUnidades();
	AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
	AsistenteEscucha leyesRobotica = new LeyesRobotica();
	AsistenteEscucha defaultOperation = new Default();
	
	@Before
	public void setUp() {

		jenkins.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);
	}

	@Test // CON SALUDO
	public void saludo_1() {


		String[] mensajes = { "¡Hola, @jenkins!", "@jenkins hola!", "buen día @jenkins", "@jenkins, buenas tardes" };
		for (String mensaje : mensajes) {
			Assert.assertEquals("¡Hola, @delucas!", jenkins.escuchar(mensaje,USUARIO));
		}
	}

	@Test
	public void saludo_2() { // despedida

		
		assertEquals("Espero haberte sido de ayuda, adios!", jenkins.escuchar("chau",USUARIO));
	}

}
