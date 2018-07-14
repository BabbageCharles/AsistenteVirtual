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
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class LeyesRoboticaTests {
	
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

	// Leyes de la robotica
	@Test
	public void leyesRobotica() {


		String[] mensajes = { "¿@jenkins cuales son las leyes de la robótica?",
				"me decias las leyes de la robótica @jenkins?", "@jenkins,las leyes de la robotica por favor" };

		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"1-Un robot no hará daño a un ser humano, ni permitirá con su inacción que sufra daño.\n"
							+ "2-Un robot debe cumplir las órdenes dadas por los seres humanos, a excepción de aquellas que entrasen en conflicto con la primera ley.\n"
							+ "3-Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la primera o con la segunda ley.",
					jenkins.escuchar(mensaje,USUARIO));

		}

	}
}
