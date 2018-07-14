package asistenteVirtual.respuestas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;
import baseDatos.ConsultasDB;
import baseDatos.RssBD;

public class Rss implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public static String USUARIO;

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;

	}

	@Override
	public String escuchar(String pedido, String user) {
		String error = new String("No entiendo el mensaje");

		List<String> rss = Arrays.asList("guardame este rss", "rss", "noticias", "noticias de rss","guardame");
		String d = rss.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);
		USUARIO = user;
		if (!d.equals(error))
			return resolverRss(pedido);

		return siguiente.escuchar(pedido, user);
	}

	private String resolverRss(String pedido) {
		
		if (pedido.contains("guardame")) {
			RssBD noticias = new RssBD();
			String link = pedido.substring(pedido.indexOf("http"));
			noticias.setLink(link);
			if(ConsultasDB.guardarLinkRss(link,USUARIO))
				return "Se a guardado tu rss";
			else
				return "No se puedo guardar tu rss";
		}
		
		if (pedido.contains("noticias") && pedido.contains("http"))
			return extraerLinks(pedido);

		
		
		if(pedido.contains("lista de rss"))
			return listaDeRss();

		return "lo siento, se produjo un error";

	}

	public String extraerLinks(String pedido) {
		String rss = pedido.substring(pedido.indexOf("http"));
		
		System.setProperty("java.net.useSystemProxies", "true");

		try {
			URL urlPagina = new URL(rss);
			URLConnection urlConexion = urlPagina.openConnection();
			urlConexion.connect();
			InputStream inputStream = urlConexion.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader lector = new BufferedReader(inputReader);
			String linea;
			String respuesta = "";

			while ((linea = lector.readLine()) != null) {
				if (linea.contains("<title>")) {
					String aux = linea.substring(linea.indexOf("<title>"));
					aux = aux.replace("<title>", "");
					aux = aux.substring(0, aux.indexOf("</title>"));
					respuesta += "Titulo: " + aux + "\n";
				}
				if (linea.contains("<link>")) {
					String aux = linea.substring(linea.indexOf("<link>"));
					aux = aux.replace("<link>", "");
					aux = aux.substring(0, aux.indexOf("</link>"));
					respuesta += "Link: " + aux + "\n";
				}
			}

			lector.close();
			respuesta = respuesta.replace("<![CDATA[","");
			respuesta = respuesta.replace("]]>","");
			
			return respuesta.substring(0,respuesta.length()-1);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "No se encontro la informacion solicitada";

	}

	public String listaDeRss() {
		List<RssBD> rss = ConsultasDB.levantarRss(USUARIO);
		String aux="";
		int i=1;
		
		if(rss == null)
			return "No hay rss registrados";
		
		if(!rss.isEmpty()) {
			for(RssBD r : rss) {
				aux += "Rss NR"+i+": "+r.getLink()+"\n";
				i++;
			}
			return aux.substring(0,aux.length()-1);
		}else	
			return "No hay rss registrados";
	}	

}
