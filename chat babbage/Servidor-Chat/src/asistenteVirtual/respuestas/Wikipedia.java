package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asistenteVirtual.AsistenteEscucha;

public class Wikipedia implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public static String USUARIO;
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	private String [] busquedas = {"informacion de John Lennon","informacion de Eruca Sativa","informacion de pikachu","informacion de videojuegos","informacion de java","informacion de caniche","informacion de messi","informacion de breaking bad","informacion de pokemon"};
	private int n= busquedas.length;
	
	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String escuchar(String pedido, String user) {
		String error = new String("No entiendo el mensaje");
		List<String> agradecimientos = Arrays.asList("informacion de", "que es", "informacion sobre", "informacion acerca de", "quien es", "hoy aprendo","quienes son");
		String b = agradecimientos.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst()
				.orElse(error);
		USUARIO=user;
		if (!b.equals(error))
			return buscarInformacion(pedido);
		return siguiente.escuchar(pedido,USUARIO);
	}
	
	public String limpiar(String str)
	{
		String cad = null;
		for (int n = 0; n <str.length (); n++) 
		{ 
			
			if(str.charAt (n)=='[')
			{
				char c=str.charAt (n);
				int aux=n;
				while(c!=' ' && aux<str.length())
					{
					 c=str.charAt (aux);
					 if(cad==null)
						 cad=""+c;
					 else
					cad+=c;
					aux++;
					}
				str = str.replace(cad, " ");
				cad=null;

					
			}
		
		}
		return str;
	}
	
	public String buscarInformacion(String mensaje) {
		
		String regex = "";
		if (mensaje.contains("informacion de"))
			regex = "informacion de";
		else 
			if (mensaje.contains("que es"))
				regex = "que es";
			else 
				if (mensaje.contains("informacion sobre"))
					regex = "informacion sobre";
				else 
					if (mensaje.contains("informacion acerca de"))
						regex = "informacion acerca de";
					else 
						if(mensaje.contains("quienes son"))
							regex= "quienes son";
						else
							if(mensaje.contains("quien es"))
								regex = "quien es";
							else
								if(mensaje.contains("hoy aprendo"))
								{
									int numero = (int) (Math.random() * n);
									Matcher busqueda = Pattern.compile(regex + " (.*)").matcher(busquedas[numero]);
									if (busqueda.find()) 
									{
										String link = "http://www.google.com/search?&btnI=745&pws=0&q=wikipedia%20sobre%20";
										link += busqueda.group(1).replace(" ", "%20");
										String informacion = obtenerInformacion(link);
										informacion = informacion.substring(3, informacion.length());
										if (informacion != null)
										{
											Matcher m = REMOVE_TAGS.matcher(informacion);
											informacion = m.replaceAll("");
											return limpiar(informacion);
										}
									}
								}

		Matcher busqueda = Pattern.compile(regex + " (.*)").matcher(mensaje);
		if (busqueda.find()) 
		{
			String link = "http://www.google.com/search?&btnI=745&pws=0&q=wikipedia%20sobre%20";
			link += busqueda.group(1).replace(" ", "%20");
			String informacion = obtenerInformacion(link);
			if(informacion.equals("No se encontro la informacion solicitada")==true)
				{
					String link2= "http://www.google.es/search?hl=es&source=hp&q=";
					link2 += busqueda.group(1).replace(" ", "%20");
					link2+= "&btnI=Voy+a+tener+suerte&meta=&aq=f&oq=";
					return "No se encontro la informacion solicitada, visita " + link2;
				}
			else
				informacion = informacion.substring(0, informacion.length());
			if (informacion != null)
			{
				Matcher m = REMOVE_TAGS.matcher(informacion);
				informacion = m.replaceAll("");
				return limpiar(informacion);
			}
		}
		return "No se encontro la informacion solicitada";
	}
	
	private String obtenerInformacion(String url) {
		System.setProperty("java.net.useSystemProxies", "true");
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/4.76");
			connection.setConnectTimeout(15000);
			connection.setReadTimeout(15000);
			connection.setInstanceFollowRedirects(true);
			connection.connect();
			url = connection.getHeaderField("location");

		} catch (Exception e1) {
			return "No se encontro la informacion solicitada";
		}

		try {
			URL urlPagina = new URL(url);
			URLConnection urlConexion = urlPagina.openConnection();
			urlConexion.connect();

			InputStream inputStream = urlConexion.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader lector = new BufferedReader(inputReader);
			String fuente = "Fuente: " + urlPagina+ "\n";
			String linea = "";
			while ((linea = lector.readLine()) != null)
				if (linea.contains("<p>"))
					return fuente+linea.substring(0,linea.length()-1);

		} catch (Exception e) {
			return "No se encontro la informacion solicitada";
		}

		return null;
	}


}
