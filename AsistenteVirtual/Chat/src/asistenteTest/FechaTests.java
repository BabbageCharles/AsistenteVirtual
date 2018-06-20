package asistenteTest;

import java.util.Date;
import java.util.GregorianCalendar;

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

public class FechaTests {
	String salida;
	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	Pedido pedido = new Pedido();

	AsistenteEscucha jenkins = new Saludar();
	AsistenteEscucha agradecer = new Agradecer();
	AsistenteEscucha calcular = new Calcular();
	AsistenteEscucha fecha = new Fecha(FECHA_HORA);
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
	
	// CON FECHA

		@Test
		public void hora() {

			String[] mensajes = { "¿qué hora es, @jenkins?", "@jenkins, la hora por favor", "me decís la hora @jenkins?" };
			for (String mensaje : mensajes) {
				Assert.assertEquals("@delucas son las 3:15 PM", jenkins.escuchar(mensaje,USUARIO));
			}
		}

		@Test
		public void fecha() {

			String[] mensajes = { "¿qué día es, @jenkins?", "@jenkins, la fecha por favor", "me decís la fecha @jenkins?" };
			for (String mensaje : mensajes) {
				Assert.assertEquals("@delucas hoy es 1 de abril de 2018", jenkins.escuchar(mensaje,USUARIO));
			}
		}

		@Test
		public void diaDeLaSemana() {

			String[] mensajes = { "¿qué día de la semana es hoy, @jenkins?" };
			for (String mensaje : mensajes) {
				Assert.assertEquals("@delucas hoy es domingo", jenkins.escuchar(mensaje,USUARIO));
			}
		}

		@Test
		public void diaDentroDe() {


			Assert.assertEquals("@delucas será el martes 3 de abril de 2018",
					jenkins.escuchar("@jenkins qué día será dentro de 2 dias?",USUARIO));

			Assert.assertEquals("@delucas será el viernes 1 de junio de 2018",
					jenkins.escuchar("@jenkins qué día será dentro de 2 meses?",USUARIO));

			Assert.assertEquals("@delucas será el miércoles 1 de abril de 2020",
					jenkins.escuchar("@jenkins qué día será dentro de 2 años?",USUARIO));
		}

		@Test
		public void diaHace() {


			Assert.assertEquals("@delucas fue el sábado 31 de marzo de 2018",
					jenkins.escuchar("@jenkins que dia fue ayer?",USUARIO));

			Assert.assertEquals("@delucas fue el jueves 29 de marzo de 2018",
					jenkins.escuchar("@jenkins que dia fue hace 3 dias?",USUARIO));

			Assert.assertEquals("@delucas fue el jueves 1 de febrero de 2018",
					jenkins.escuchar("@jenkins que dia fue hace 2 meses?",USUARIO));

			Assert.assertEquals("@delucas fue el viernes 1 de abril de 2016",
					jenkins.escuchar("@jenkins que dia fue hace 2 años?",USUARIO));
		}

		@Test
		public void tiempoDesde() {


			Assert.assertEquals("@delucas entre el 1 de abril de 2017 y el 1 de abril de 2018 pasaron 365 días",
					jenkins.escuchar("@jenkins cuantos dias pasaron desde el 1 de abril de 2017?",USUARIO));

			Assert.assertEquals("@delucas entre el 1 de marzo de 2018 y el 1 de abril de 2018 pasaron 31 días",
					jenkins.escuchar("@jenkins cuantos dias pasaron desde el 1 de marzo de 2018?",USUARIO));

		}

		@Test
		public void tiempoHasta() {

			Assert.assertEquals("@delucas faltan 9 días",
					jenkins.escuchar("@jenkins cuantos dias faltan para el 10 de abril?",USUARIO));
			Assert.assertEquals("@delucas faltan 365 días",
					jenkins.escuchar("@jenkins cuantos dias faltan para el 1 de abril de 2019?",USUARIO));
		}
}
