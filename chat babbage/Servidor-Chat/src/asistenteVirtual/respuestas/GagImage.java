package asistenteVirtual.respuestas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import asistenteVirtual.AsistenteEscucha;

public class GagImage implements AsistenteEscucha{
	
	private AsistenteEscucha siguiente;
	public static String USUARIO; 
	private ArrayList<String> lista = new ArrayList<String>();
	private String []categorias = {"funny","football","drawing","cute","anime-manga","awesome","car","comic","cosplay","animefanart",
									"classicalartmemes","food","gaming","history","horror","movie-tv","music","pcmr","photography",
									"pokemon","pubg","starwars","savage","science","superhero","sport","wtf"};
	
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;		
	}

	@Override
	public String escuchar(String pedido, String user) {
		if(pedido.contains("9gag") || pedido.contains("9GAG"))			
				return buscarImagen2();
			
		return siguiente.escuchar(pedido,user);
	}
	
	public static String buscarImagen(String url) throws IOException {
		
		Document document = Jsoup.connect(url).get();
		Elements image = document.getElementsByAttributeValue("property", "og:image");

		if(image != null)
			return image.attr("content");
			
		return null;		
	}
	
	public  String buscarImagen2() {
		System.setProperty("java.net.useSystemProxies", "true"); 
		String url = "https://9gag.com/"+categorias[(int)(Math.random()*categorias.length)];
		try {
			URL urlPagina = new URL(url);			
			InputStreamReader inputReader = new InputStreamReader(urlPagina.openStream(), "UTF-8");
			BufferedReader lector = new BufferedReader(inputReader);
			String linea = "";
			String aux= "";
			while ((linea = lector.readLine()) != null)
				while (linea.contains("\"type\":\"Photo\"") ){
					linea = linea.substring(linea.indexOf("\"type\":\"Photo\""));					
					aux = linea.substring(linea.indexOf("\"url\":\"")+7,linea.indexOf(".jpg") + 4);
					aux = aux.replace("\\", "") ;
					this.lista.add(aux);
					linea = linea.substring(linea.indexOf(".jpg") + 4);
				}				
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		
		if (!lista.isEmpty()) {

			String direccion = lista.get((int)(Math.random()*lista.size()));
			lista.clear();
			return direccion;
		}

		return null;
	}
	

}
