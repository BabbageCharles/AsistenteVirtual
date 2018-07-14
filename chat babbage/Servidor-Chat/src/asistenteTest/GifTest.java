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
import asistenteVirtual.respuestas.Fecha;
import asistenteVirtual.respuestas.GagImage;
import asistenteVirtual.respuestas.Gif;
import asistenteVirtual.respuestas.LeyesRobotica;
import asistenteVirtual.respuestas.Saludar;
import asistenteVirtual.respuestas.Wikipedia;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class GifTest {
	
	
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
	AsistenteEscucha Wikipedia = new Wikipedia();
	AsistenteEscucha gif = new Gif();
	AsistenteEscucha defaultOperation = new Default();
	


	
	@Before
	public void setUp() {

		jenkins.establecerSiguiente(Wikipedia);
		Wikipedia.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(gag);		
		gag.establecerSiguiente(deuda);
		deuda.establecerSiguiente(gif);
		gif.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);
		
	
		
	}
	
	
   
	@Test
	public void giphy(){
		
		String[] mensajes = { "@jenkins gif de perros", "@jenkins gif de algo", "@jenkins giphy de fiesta" };
		for (String mensaje : mensajes) {
			
			
			Assert.assertEquals("https://i.giphy.com/media/",jenkins.escuchar(mensaje,USUARIO).substring(0,"https://i.giphy.com/media/".length() ));
		}
		
		
	}
	}


