package asistenteTest;

import static org.junit.Assert.assertEquals;
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

public class CalcularTests {

	
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

	@Test // CALCULAR
	public void calculos() {


	
		assertEquals("@delucas 3", jenkins.escuchar("@jenkins cu�nto es 1 + 2",USUARIO));

		assertEquals("@delucas 3.5", jenkins.escuchar("@jenkins cu�nto es 1.5 + 2",USUARIO));

		assertEquals("@delucas 1", jenkins.escuchar("@jenkins cu�nto es 5 - 2 * 2",USUARIO));

		assertEquals("@delucas 10", jenkins.escuchar("@jenkins cu�nto es el 10% de 100",USUARIO));

		assertEquals("@delucas 42", jenkins.escuchar("@jenkins cu�nto es el 17 + 5 ^ 2",USUARIO));

	}

	@Test
	public void calculosCompuestos() {

		assertEquals("@delucas -6", jenkins.escuchar("@jenkins cu�nto es (4-8)*2 + 4 / ( 1 + 1)",USUARIO));

	}

}
