package asistenteVirtual;

import asistenteVirtual.respuestas.Agradecer;
import asistenteVirtual.respuestas.Calcular;
import asistenteVirtual.respuestas.ChuckNorrisFacts;
import asistenteVirtual.respuestas.Default;
import asistenteVirtual.respuestas.Fecha;
import asistenteVirtual.respuestas.LeyesRobotica;
import asistenteVirtual.respuestas.Saludar;
import asistenteVirtual.unidadesMedicion.ConvertorDeUnidades;

public class AsistenteVirtual {
	public final static String USUARIO = "delucas"; // Generar nombre random en
	// el constructor
	// public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15,
	// 15, 0).getTime();// se generaria en el constructor

	// CONSTRUCTORES
	public AsistenteVirtual(String s) {
	}

	public AsistenteVirtual() {
	}

	public static void main(String[] args) {

		// Creo las operaciones con las cuales voy a trabajar
		AsistenteEscucha respuesta = new Saludar();
		AsistenteEscucha agradecer = new Agradecer();
		AsistenteEscucha calcular = new Calcular();
		AsistenteEscucha fecha = new Fecha();
		AsistenteEscucha conversor = new ConvertorDeUnidades();
		AsistenteEscucha chuckNorris = new ChuckNorrisFacts();
		AsistenteEscucha leyesRobotica = new LeyesRobotica();

		// AsistenteEscucha pgp = new PGP();
		AsistenteEscucha defaultOperation = new Default();

		respuesta.establecerSiguiente(agradecer);
		agradecer.establecerSiguiente(calcular);
		calcular.establecerSiguiente(fecha);
		fecha.establecerSiguiente(conversor);
		conversor.establecerSiguiente(chuckNorris);
		chuckNorris.establecerSiguiente(leyesRobotica);
		// chuckNorris.establecerSiguiente(pgp);
		leyesRobotica.establecerSiguiente(defaultOperation);

		// Con chuck Norris7
		System.out.println(respuesta.escuchar("¡Hola, @jenkins!"));
		System.out.println(respuesta.escuchar("chau"));
		String[] mensajes = { "¡Hola, @jenkins!", "@jenkins hola!", "buen día @jenkins", "@jenkins, buenas tardes",
				"hey @jenkins" };
		for (String mensaje : mensajes) {
			System.out.println(respuesta.escuchar(mensaje));
		}

	}
}
