package asistenteVirtual;

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
import paqueteEnvios.PaqueteMensaje;
import servidor.Servidor;

public class AsistenteVirtual extends Thread implements AsistenteEscucha{
	
	private PaqueteMensaje paqueteMensaje;
	

	private String mensajeAEnviar;
	@SuppressWarnings("unused")
	private String mensajeRecibido;
	public String USUARIO; 
	
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
			AsistenteEscucha Wikipedia = new Wikipedia();
			AsistenteEscucha gif = new Gif();
			AsistenteEscucha rss = new Rss();
			AsistenteEscucha evento = new Eventos();
			AsistenteEscucha meme = new Memes();

	public AsistenteVirtual() {
		
		respuesta.establecerSiguiente(evento);
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
	}


	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {	
		
	}

	@Override
	public String escuchar(String pedido,String user) {
		return respuesta.escuchar(pedido,USUARIO);
	}
	
	public PaqueteMensaje getPaqueteMensaje() {
		return paqueteMensaje;
	}


	public void setPaqueteMensaje(PaqueteMensaje paqueteMensaje) {
		this.paqueteMensaje = paqueteMensaje;
	}
	
	public String getUSUARIO() {
		return USUARIO;
	}


	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				 

				while (true) {
										
					if (paqueteMensaje!=null) {
						setUSUARIO(paqueteMensaje.getUserEmisor());
						mensajeRecibido = paqueteMensaje.getMsjChat();						
						paqueteMensaje = null;
						setUSUARIO(null);
					} else {
						this.wait();
					}
				}
			} catch (InterruptedException e) {
				Servidor.getLog().append("Error en el proceso del chatbot." + System.lineSeparator());
			}
		} 
	}


	public String getMensajeAEnviar() {
		return mensajeAEnviar;
	}


	public void setMensajeAEnviar(String mensajeAEnviar) {
		this.mensajeAEnviar = mensajeAEnviar;
	}


	
}
