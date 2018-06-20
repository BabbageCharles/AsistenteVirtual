package asistenteVirtual.respuestas;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import asistenteVirtual.AsistenteEscucha;

public class GagImage implements AsistenteEscucha{
	
	private AsistenteEscucha siguiente;
	public static String USUARIO; 
	
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;		
	}

	@Override
	public String escuchar(String pedido, String user) {
		if(pedido.contains("9gag") || pedido.contains("9GAG"))
			try {
				return extractImageUrl("https://9gag.com/random");
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		return siguiente.escuchar(pedido,user);
	}
	
	public static String extractImageUrl(String url) throws IOException {
        String contentType = new URL(url).openConnection().getContentType();
        if (contentType != null) {
            if (contentType.startsWith("image/")) {
                return url;
            }
        }

        Document document = Jsoup.connect(url).get();

        String imageUrl = null;      

        imageUrl = getImageFromLinkRel(document);
        if (imageUrl != null) {
            return imageUrl;
        }

      
        return imageUrl;
    }    

    private static String getImageFromLinkRel(Document document) {
        Element link = document.select("link[rel=image_src]").first();
        if (link != null) {
            return link.attr("abs:href");
        }
        return null;
    }
	

}
