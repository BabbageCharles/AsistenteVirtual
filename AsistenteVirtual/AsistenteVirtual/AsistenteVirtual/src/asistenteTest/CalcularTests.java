package asistenteTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.jupiter.api.Test;

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

public class CalcularTests {

	String salida;
	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	Pedido pedido = new Pedido();

	@Before
	public void setUp() {

	}

	@Test // CALCULAR
	public void calculos() {
		AsistenteEscucha jenkins = new Saludar();
		AsistenteEscucha agradecer = new Agradecer();
		AsistenteEscucha calcular = new Calcular();
		AsistenteEscucha fecha = new Fecha();
		AsistenteEscucha conversor = new ConvertorDeUnidades();
		AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
		AsistenteEscucha leyesRobotica = new LeyesRobotica();
		AsistenteEscucha defaultOperation = new Default();

		jenkins.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);
		assertEquals("@delucas 3", jenkins.escuchar("@jenkins cuánto es 1 + 2"));

		assertEquals("@delucas 3.5", jenkins.escuchar("@jenkins cuánto es 1.5 + 2"));

		assertEquals("@delucas 1", jenkins.escuchar("@jenkins cuánto es 5 - 2 * 2"));

		assertEquals("@delucas 10", jenkins.escuchar("@jenkins cuánto es el 10% de 100"));

		assertEquals("@delucas 42", jenkins.escuchar("@jenkins cuánto es el 17 + 5 ^ 2"));

	}

	@Test
	public void calculosCompuestos() {
		AsistenteEscucha jenkins = new Saludar();
		AsistenteEscucha agradecer = new Agradecer();
		AsistenteEscucha calcular = new Calcular();
		AsistenteEscucha fecha = new Fecha();
		AsistenteEscucha conversor = new ConvertorDeUnidades();
		AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
		AsistenteEscucha leyesRobotica = new LeyesRobotica();
		AsistenteEscucha defaultOperation = new Default();

		jenkins.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);
		assertEquals("@delucas -6", jenkins.escuchar("@jenkins cuánto es (4-8)*2 + 4 / ( 1 + 1)"));

	}

}
