package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.net.*;
import java.util.regex.*;

import asistenteVirtual.AsistenteEscucha;

public class Gif implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public  static String USUARIO;
	private Pattern regex = Pattern.compile("de (.*)");
	private Pattern Gif = Pattern.compile("media/(.+)/gip");

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido, String user) {
		String error = new String("No entiendo el mensaje");
		List<String> agradecimientos = Arrays.asList("gif", "GIF", "giphy");
		String b = agradecimientos.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse(error);
		USUARIO=user;
		if (!b.equals(error))
			return obtenerLink(pedido);
		return siguiente.escuchar(pedido,USUARIO);
	}
	
	private String obtenerLink(String mensaje) {
		System.setProperty("java.net.useSystemProxies", "true"); 
		Matcher categoria = regex.matcher(mensaje);
		if (categoria.find()) {
			String url = "http://api.giphy.com/v1/gifs/random?api_key=yvyt1ie3dMcJOJzDj1Lp7okLFHIUXYfs&fmt=html&tag="+categoria.group(1).replace(" ", "+");
			try {
				URL urlPagina = new URL(url);
				URLConnection urlConexion = urlPagina.openConnection();
				urlConexion.connect();

				InputStream inputStream = urlConexion.getInputStream();
				InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader lector = new BufferedReader(inputReader);
				String linea = "";
				String link = "https://i.giphy.com/media/";
				Matcher match;
				while ((linea = lector.readLine()) != null)
					if ((match = Gif.matcher(linea)).find()) {
						link += match.group(1) + "/giphy.gif";
						return link;
					}
				lector.close();
			} catch (Exception e) {
				return "Lo siento, vuelve a ingresar el pedido";
			}
		}
		
		return "Lo siento,vuelve a ingresar el pedido";
	}
	
	

}
