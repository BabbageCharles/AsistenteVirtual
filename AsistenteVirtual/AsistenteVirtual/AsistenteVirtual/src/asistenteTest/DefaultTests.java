package asistenteTest;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
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

public class DefaultTests {
	String salida;
	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	Pedido pedido = new Pedido();

	@Before
	public void setUp() {

	}

	@Test // MSJES_SIN SENTIDO
	public void sinsentido() {
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
		String[] mensajes = { "Este mensaje no tiene sentido @jenkins" };
		for (String mensaje : mensajes) {
			Assert.assertEquals("Disculpa... no entiendo el pedido, @delucas ¿podrías repetirlo?",
					jenkins.escuchar(mensaje));
		}
	}

}
