package asistenteVirtual;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitarias {

	public static int buscarEntero(String s) {
		String regexp = "([0-9]+)";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		// VER
//		if (matcher.matches() == false)
//			return -1;
		int i = Integer.parseInt(matcher.group());
		return i;
	}

	public static int cantidadNombres(String s) {

		int contador=0;
		
		while(s.indexOf("@")>-1){
			s=s.substring(s.indexOf("@")+"@".length(),s.length());
			contador++;
		}
		
		return contador;
	}
	
	public static String[] buscarNombre(String s){
		
		int i=0,b=0;
		int contador=cantidadNombres(s);
		String[] nombres = new String [contador-1];
		String[] tokens = s.split(" ");
		for (String t : tokens){
			String[] tokens2 = t.split(",");
			for( String nombre : tokens2)
			{
				if(nombre.charAt(0)=='@')
				{
					if(b!=0)
					{
						nombre=nombre.substring(1);
						nombres[i]=nombre;
						i++;
					}
					
				}
				b=1;
			}	
		}
		
		return nombres;
	}

}
