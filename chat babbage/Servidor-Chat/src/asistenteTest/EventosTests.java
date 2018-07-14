package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Pedido;
import asistenteVirtual.respuestas.Agradecer;
import asistenteVirtual.respuestas.Calcular;
import asistenteVirtual.respuestas.CalcularDeudas;
import asistenteVirtual.respuestas.ChuckNorrisFacts;
import asistenteVirtual.respuestas.Default;
import asistenteVirtual.respuestas.Eventos;
import asistenteVirtual.respuestas.Fecha;
import asistenteVirtual.respuestas.GagImage;
import asistenteVirtual.respuestas.Gif;
import asistenteVirtual.respuestas.LeyesRobotica;
import asistenteVirtual.respuestas.Memes;
import asistenteVirtual.respuestas.Rss;
import asistenteVirtual.respuestas.Saludar;
import asistenteVirtual.respuestas.Wikipedia;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;
import baseDatos.ConsultasDB;

public class EventosTests {

	public final static String USUARIO = "delucas";	
	Pedido pedido = new Pedido();
	
	AsistenteEscucha jenkins = new Saludar();
	AsistenteEscucha agradecer = new Agradecer();
	AsistenteEscucha calcular = new Calcular();
	AsistenteEscucha fecha = new Fecha();
	AsistenteEscucha conversor = new ConvertorDeUnidades();
	AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
	AsistenteEscucha leyesRobotica = new LeyesRobotica();
	AsistenteEscucha gag = new GagImage();
	AsistenteEscucha deuda = new CalcularDeudas();
	AsistenteEscucha defaultOperation = new Default();
	AsistenteEscucha Wikipedia = new Wikipedia();
	AsistenteEscucha gif = new Gif();
	AsistenteEscucha rss = new Rss();
	AsistenteEscucha evento = new Eventos();
	AsistenteEscucha meme = new Memes();
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() {
		jenkins.establecerSiguiente(evento);
		evento.establecerSiguiente(Wikipedia);
		Wikipedia.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(rss);		
		rss.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(gag);		
		gag.establecerSiguiente(deuda);
		deuda.establecerSiguiente(gif);
		gif.establecerSiguiente(calcular);
		calcular.establecerSiguiente(meme);
		meme.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);		
		ConsultasDB cons = new ConsultasDB("cfgTest.xml");
		cons.limpiarEventos();
	}
	
	@Test
	public void agendarEvento() {
		
		Assert.assertEquals(
				"Evento agregado",
				jenkins.escuchar("@jenkins agrega el evento: defensa tp progra, sabado 14",USUARIO)
			);
		
		Assert.assertEquals(
				"Evento:  defensa tp progra, sabado 14",
				jenkins.escuchar("@jenkins lista de eventos",USUARIO)
			);
		
		Assert.assertEquals(
				"Evento:  defensa tp progra, sabado 14",
				jenkins.escuchar("@jenkins lista de eventos",USUARIO)
			);
		
		Assert.assertEquals(
				"Evento agregado",
				jenkins.escuchar("@jenkins agrega: cierre de actas del primer cuatrimestre",USUARIO)
			);
		
		Assert.assertEquals(
				"Evento:  defensa tp progra, sabado 14\r\n" + 
				"Evento:  cierre de actas del primer cuatrimestre",
				jenkins.escuchar("@jenkins lista de eventos",USUARIO)
			);
	}

	
	
} 
