package asistenteVirtual.unidadesMedicion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import asistenteVirtual.Utilitarias;

public class ConvertorDeUnidades {
	
	public static String darConversion(String cadena) {
		
		DecimalFormat df = new DecimalFormat("#.#######");
		double resultado=0;		
		Integer cantidad= Utilitarias.buscarEntero(cadena);
		String paquete = ConvertorDeUnidades.class.getPackage().getName()+".";		
		String unidadOriginal= ConvertorDeUnidades.buscarUnidad(cadena.substring(cadena.indexOf(cantidad.toString())));		
		String unidadNueva = ConvertorDeUnidades.buscarUnidad(cadena.substring(0,cadena.indexOf(cantidad.toString())));	
		String aux= " equivale a ";
		
		try {
		Class<?> UnidadaOriginalClass = Class.forName(paquete+unidadOriginal);
		Class<?> UnidadaNuevaClass = Class.forName(paquete+unidadNueva);
		
		Constructor<?> constructor = UnidadaOriginalClass.getConstructor(double.class);
		Object uOri = constructor.newInstance(cantidad);		
		
		Constructor<?> constructor2 = UnidadaNuevaClass.getConstructor(double.class);	
		Object uNue = constructor2.newInstance(0);	
		
		Method methodConvert = UnidadaOriginalClass.getDeclaredMethod("convertirA",uNue.getClass());		
		uNue=methodConvert.invoke(uOri,uNue);
		
		Method methodGetCant = uNue.getClass().getDeclaredMethod("getCantidad");		
		resultado =  (double) methodGetCant.invoke(uNue);	
		
		}catch (Exception e) {
			System.out.println("No se puedo realizar la conversion");
		}
		
		// todos estos if son para mostras bien la salida, ej si llega a ser cubico, si es unidad en plural o singular, etc
		if(resultado != 1) {
			if(cadena.contains("c�bico"))
				unidadNueva = unidadNueva.substring(0,unidadNueva.indexOf("Cubico")).toLowerCase()+"s"+" c�bicos";
			else
				unidadNueva = unidadNueva.toLowerCase()+"s";
		}
		else {
			if(cadena.contains("c�bico"))
				unidadNueva = unidadNueva.substring(0,unidadNueva.indexOf("Cubico")).toLowerCase()+" c�bicos";
		}
		
		if(cantidad != 1) {
			if(cadena.contains("c�bico"))
				unidadOriginal = unidadOriginal.substring(0,unidadOriginal.indexOf("Cubico")).toLowerCase()+"s"+" c�bicos";
			else
				unidadOriginal = unidadOriginal.toLowerCase()+"s";
			aux= " equivalen a ";
		}
		else {
			if(cadena.contains("c�bico"))
				unidadOriginal = unidadOriginal.substring(0,unidadOriginal.indexOf("Cubico")).toLowerCase()+" c�bicos";
		}	
		
				
		String res = cantidad+" "+unidadOriginal.toLowerCase()+aux+df.format(resultado).replace(",", ".")+" "+unidadNueva.toLowerCase();
			
		return res;

	}	
	
	public static String buscarUnidad(String cadena) {
		String uni[]= {"Miligramo","Gramo","Kilo","Dracma","Onza","Libra",
				"Milimetro","Centimetro","Metro","Kilometro",
				"Pie","Pulgada","Yarda","Segundo","Minuto","Hora"};
		
		String unidad;
		int i=0;
		
		while(!cadena.contains(uni[i].toLowerCase()))
			i++;
		
		unidad= uni[i];		
		
		if(cadena.contains("c�bico") || cadena.contains("c�bica") )
			unidad = unidad+"Cubico";
			
		return unidad;
		
	}

	
}
