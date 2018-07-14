package asistenteTest;

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
import asistenteVirtual.respuestas.Wikipedia;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class WikipediaTest {
	
	public final static String USUARIO = "delucas";
	Pedido pedido = new Pedido();

	AsistenteEscucha jenkins = new Saludar();
	AsistenteEscucha agradecer = new Agradecer();
	AsistenteEscucha calcular = new Calcular();
	AsistenteEscucha fecha = new Fecha();
	AsistenteEscucha conversor = new ConvertorDeUnidades();
	AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
	AsistenteEscucha leyesRobotica = new LeyesRobotica();
	AsistenteEscucha wikipedia = new Wikipedia();
	AsistenteEscucha defaultOperation = new Default();
	
	@Before
	public void setUp() {
		jenkins.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(wikipedia);
		wikipedia.establecerSiguiente(defaultOperation);
	}

	// Wikipedia
	@Test
	public void wikipedia() {


		String[] mensajes = { "¿@jenkins quien es lionel messi?",
				"@jenkins informacion de lionel messi"};

		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"Fuente: https://es.wikipedia.org/wiki/Lionel_Messi\nLionel Andrés Messi Cuccittini (Rosario, 24 de junio de 1987), conocido como Leo Messi, es un futbolista argentino que juega como delantero o centrocampista, y ha desarrollado toda su carrera en el F. C. Barcelona de la Primera División de España y en la selección argentina, de la que es capitán. ",
					jenkins.escuchar(mensaje,USUARIO));

		}

	}
}