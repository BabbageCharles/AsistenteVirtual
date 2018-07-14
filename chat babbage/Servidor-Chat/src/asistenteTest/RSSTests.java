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

public class RSSTests {

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
		cons.limpiarRss();
	}
	
	@Test
	public void traerRss() {
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("Titulo: Clarin.com - Home\r\n" + 
						"Link: http://www.clarin.com/\r\n" + 
						"Titulo: Clarin.com - Home\r\n" + 
						"Link: http://www.clarin.com/\r\n" + 
						"Titulo: Íbamos a ser \"amigas para siempre\". No fue así: nunca nos peleamos pero crecimos de manera diferente\r\n" + 
						"Link: http://www.clarin.com/sociedad/mundos-intimos-ibamos-amigas-siempre-peleamos-crecimos-manera-diferente_0_Syrl-FImQ.html\r\n" + 
						"Titulo: El biodiesel argentino emite un 70 % menos de GEI\r\n" + 
						"Link: http://www.clarin.com/rural/biodiesel-argentino-emite-70-gei_0_SJdtvBLmX.html\r\n" + 
						"Titulo: Un tenista fue sancionado por promocionar una casa de apuestas en Twitter\r\n" + 
						"Link: http://www.clarin.com/deportes/tenis/tenista-sancionado-promocionar-casa-apuestas-twitter_0_ByNbKewm7.html\r\n" + 
						"Titulo: Científicos consiguen la imagen más clara del centro de la Vía Láctea\r\n" + 
						"Link: http://www.clarin.com/sociedad/muestran-imagen-clara-centro-via-lactea_0_H1eYZlDXm.html\r\n" + 
						"Titulo: Fin de semana en el Conurbano: JAF, Don Vilanova gratis y más consagrados\r\n" + 
						"Link: http://www.clarin.com/zonales/fin-semana-conurbano-rock-blues-teatro_0_BkBkIBHX7.html\r\n" + 
						"Titulo: Transgénicos: dos décadas  ganadas\r\n" + 
						"Link: http://www.clarin.com/rural/transgenicos-decadas-ganadas_0_r1i5sF8mX.html\r\n" + 
						"Titulo: Entre la soja y el FMI...\r\n" + 
						"Link: http://www.clarin.com/rural/soja-fmi_0_SJ-jpFUXm.html\r\n" + 
						"Titulo: Biogás: avanza la normativa que regula el uso y la venta del digerido\r\n" + 
						"Link: http://www.clarin.com/rural/biogas-avanza-normativa-regula-uso-venta-digerido_0_rJ7zS5LXX.html\r\n" + 
						"Titulo: En Chicago, la soja cayó esta semana a su nivel más bajo en nueve años\r\n" + 
						"Link: http://www.clarin.com/rural/chicago-soja-cayo-semana-nivel-anos_0_SJ2DqcLXQ.html\r\n" + 
						"Titulo: La ANSeS no administra decentemente los derechos de los jubilados\r\n" + 
						"Link: http://www.clarin.com/cartas-al-pais/anses-administra-decentemente-derechos-jubilados_0_S1qkoiL7Q.html\r\n" + 
						"",USUARIO)
			);
		
	}

	@Test
	public void guardarRss() {
		Assert.assertEquals(
				"Se a guardado tu rss",
				jenkins.escuchar("@jenkins guardame el rss https://www.clarin.com/rss/lo-ultimo/",USUARIO)
			);
		Assert.assertEquals(
				"Rss NR1: https://www.clarin.com/rss/lo-ultimo/",
				jenkins.escuchar("@jenkins traeme la lista de rss",USUARIO)
			);
		Assert.assertEquals(
				"Se a guardado tu rss",
				jenkins.escuchar("@jenkins guardame el rss https://www.clarin.com/rss/politica/",USUARIO)
			);
		Assert.assertEquals(
				"Rss NR1: https://www.clarin.com/rss/lo-ultimo/\r\n" + 
				"Rss NR2: https://www.clarin.com/rss/politica/\r\n" + 
				"",
				jenkins.escuchar("@jenkins traeme la lista de rss",USUARIO)
			);
	}
	
} 
 