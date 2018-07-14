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

public class ConversorDeUnidadesTests {
	
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

	// Unidad de medicion
	@Test
	public void unidadesDeMedicion() {
		
		Assert.assertEquals("@delucas no se pueden hacer conversiones de dos unidades distintas",
				jenkins.escuchar("@jenkins cuántos gramos son 1 hora",USUARIO));

		Assert.assertEquals("@delucas 1 kilo equivale a 1000 gramos",
				jenkins.escuchar("@jenkins cuántos gramos son 1 kilo",USUARIO));

		Assert.assertEquals("@delucas 1000 gramos equivalen a 1 kilo",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 gramos",USUARIO));

		Assert.assertEquals("@delucas 1000 gramos equivalen a 35.27 onzas",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 gramos",USUARIO));

		Assert.assertEquals("@delucas 1000 metros equivalen a 100000 centimetros",
				jenkins.escuchar("@jenkins cuántos centimetros son 1000 metros",USUARIO));

		Assert.assertEquals("@delucas 1000 centimetros equivalen a 10 metros",
				jenkins.escuchar("@jenkins cuántos metros son 1000 centimetros",USUARIO));

		Assert.assertEquals("@delucas 1000 centimetros equivalen a 393.7 pulgadas",
				jenkins.escuchar("@jenkins cuántas pulgadas son 1000 centimetros",USUARIO));

		Assert.assertEquals("@delucas 1000 metros cúbicos equivalen a 1000000000 centimetros cúbicos",
				jenkins.escuchar("@jenkins cuántos centimetros cúbicos son 1000 metros cúbicos",USUARIO));

		Assert.assertEquals("@delucas 1000 centimetros cúbicos equivalen a 0.001 metros cúbicos",
				jenkins.escuchar("@jenkins cuántos metros cúbicos son 1000 centimetros cúbicos",USUARIO));

		Assert.assertEquals("@delucas 1000 pies cúbicos equivalen a 28.3168 metros cúbicos",
				jenkins.escuchar("@jenkins cuántos metros cúbicos son 1000 pies cúbicos",USUARIO));

		Assert.assertEquals("@delucas 1000 segundos equivalen a 16.6667 minutos",
				jenkins.escuchar("@jenkins cuántos minutos son 1000 segundos",USUARIO));

		Assert.assertEquals("@delucas 1333 horas equivalen a 79980 minutos",
				jenkins.escuchar("@jenkins cuántos minutos son 1333 horas",USUARIO));
	}

}
