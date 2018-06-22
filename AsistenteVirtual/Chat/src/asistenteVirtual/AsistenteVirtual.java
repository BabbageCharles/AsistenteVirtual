package asistenteVirtual;

import asistenteVirtual.respuestas.Agradecer;
import asistenteVirtual.respuestas.Calcular;
import asistenteVirtual.respuestas.CalcularDeudas;
import asistenteVirtual.respuestas.ChuckNorrisFacts;
import asistenteVirtual.respuestas.Default;
import asistenteVirtual.respuestas.Fecha;
import asistenteVirtual.respuestas.GagImage;
import asistenteVirtual.respuestas.LeyesRobotica;
import asistenteVirtual.respuestas.Saludar;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class AsistenteVirtual implements AsistenteEscucha {
	public String USUARIO; 
	// Creo las operaciones con las cuales voy a trabajar
			AsistenteEscucha respuesta = new Saludar();
			AsistenteEscucha agradecer = new Agradecer();
			AsistenteEscucha calcular = new Calcular();
			AsistenteEscucha fecha = new Fecha();
			AsistenteEscucha conversor = new ConvertorDeUnidades();
			AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
			AsistenteEscucha leyesRobotica = new LeyesRobotica();
			AsistenteEscucha gag = new GagImage();
			AsistenteEscucha deuda = new CalcularDeudas();
			AsistenteEscucha defaultOperation = new Default();


	public AsistenteVirtual(String usuario) {
		USUARIO=usuario;		
		respuesta.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(gag);		
		gag.establecerSiguiente(deuda);
		deuda.establecerSiguiente(leyesRobotica);
		leyesRobotica.establecerSiguiente(defaultOperation);
	}

	/*public static void main(String[] args) {

		
	}*/

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {	
		
	}

	@Override
	public String escuchar(String pedido,String user) {
		return respuesta.escuchar(pedido,USUARIO);
	}
}
