package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;
import asistenteVirtual.AsistenteEscucha;
import baseDatos.ConsultasDB;
import baseDatos.Evento;

public class Eventos implements AsistenteEscucha{

	private AsistenteEscucha siguiente;
	public static String USUARIO;
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}
	
	@Override
	public String escuchar(String pedido,String user) {
		String error = new String("No entiendo el mensaje");
		List<String> evento = Arrays.asList("agrega", "evento", "lista de eventos");
		String b = evento.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse(error);
		USUARIO=user;
		if (!b.equals(error))
			return resolver(pedido);
		return siguiente.escuchar(pedido,USUARIO);
	}
	
	public String resolver(String pedido) {
		if ((pedido.matches(".*" + "agrega" + ".*"))) 
		{
			pedido = pedido.substring(pedido.indexOf(":") + 1);
			return agregarEvento(pedido.substring(pedido.indexOf(":") + 1));

		} else if (pedido.matches(".*" + "lista de eventos" + ".*")) {
			return listaDeEventos();
		}
	
		return "lo siento hay un error";
	}

	private String agregarEvento(String evento) {
		
		if (ConsultasDB.crearEvento(evento,USUARIO))
			return "Evento agregado";
		else
			return "No se pudo agregar el evento";
	}

	private String listaDeEventos() {
		List<Evento> eve = ConsultasDB.levantarEventos(USUARIO);
		String aux="";
		
		if(eve == null)
			return "No hay eventos";
		
		if(!eve.isEmpty()) {
			for(Evento e : eve) {
				aux += e.toString()+"\n";
			}
			return aux.substring(0,aux.length()-1);
		}else	
			return "No hay eventos";
	}	

}
