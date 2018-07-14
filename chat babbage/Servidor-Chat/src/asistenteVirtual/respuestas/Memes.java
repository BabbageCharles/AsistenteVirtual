package asistenteVirtual.respuestas;


import java.util.Arrays;
import java.util.List;
import asistenteVirtual.AsistenteEscucha;
import baseDatos.ConsultasDB;
import baseDatos.Meme;

public class Memes implements AsistenteEscucha{

	private AsistenteEscucha siguiente;
	public static String USUARIO;
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}
	
	@Override
	public String escuchar(String pedido,String user) {
		String error = new String("No entiendo el mensaje");
		List<String> evento = Arrays.asList("meme", "memes","lista de meme","lista de memes");
		String b = evento.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse(error);
		USUARIO=user;
		if (!b.equals(error))
			return resolver(pedido);
		return siguiente.escuchar(pedido,USUARIO);
	}

	private String resolver(String pedido) {
		if(pedido.contains("lista"))
			return listado();
		else
			return devolvermeme(pedido);
				
	}

	private String devolvermeme(String pedido) {
		List<Meme>m= ConsultasDB.levantarMeme();	
		int i=0;		
		
		while((i<m.size())) {							
			if(pedido.contains(m.get(i).getNombre()))	
				break;
			else
				i++;
		}
			
		if(i < m.size()) {
			
			String res = m.get(i).getImagen();
			
			if(!res.contains("http"))
				return res;
			else
				return  "meme "+ res;
		}
			
			
		return "No he encontrado el meme que solicitaste";		
		
	}

	private String listado() {
		List<Meme> meme = ConsultasDB.levantarMeme();
		String aux="";
		int i=1;
		
		if(meme == null)
			return "No hay memes disponibles";
		
		if(!meme.isEmpty()) {
			for(Meme m : meme) {
				aux += i+": "+m.getNombre()+"\n";
				i++;
			}
			return aux.substring(0,aux.length()-1);
		}else	
			return "No hay memes disponibles";
	}

}
