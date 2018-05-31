package asistenteVirtual.unidadesMedicion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import asistenteVirtual.Utilitarias;

public class ConvertorDeUnidades {
	
	public static String darConversion(String cadena) {
		
		double resultado=0;		
		Integer cantidad= Utilitarias.buscarEntero(cadena);
		String paquete = ConvertorDeUnidades.class.getPackage().getName();		
		String unidadOriginal= ConvertorDeUnidades.buscarUnidad(cadena.substring(cadena.indexOf(cantidad.toString())));		
		String unidadNueva = ConvertorDeUnidades.buscarUnidad(cadena.substring(0,cadena.indexOf(cantidad.toString())));	
		
		
		try {
		Class<?> UnidadaOriginalClass = Class.forName(paquete+"."+unidadOriginal);
		Class<?> UnidadaNuevaClass = Class.forName(paquete+"."+unidadNueva);
		
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
		
		return ConvertorDeUnidades.darFormatoSalida(unidadOriginal, unidadNueva, cantidad, resultado);

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
	
	public static String darFormatoSalida(String uniOri,String uniNue,Integer cantOri,double cantNue) {
		
		DecimalFormat df = new DecimalFormat("#.#######");
		String aux= " equivale a ";
		
		if(cantNue != 1) {
			if(uniNue.contains("Cubico"))
				uniNue = uniNue.substring(0,uniNue.indexOf("Cubico")).toLowerCase()+"s"+" c�bicos";
			else
				uniNue = uniNue.toLowerCase()+"s";
		}
		else {
			if(uniNue.contains("Cubico"))
				uniNue = uniNue.substring(0,uniNue.indexOf("Cubico")).toLowerCase()+" c�bico";
		}
		
		if(cantOri != 1) {
			if(uniOri.contains("Cubico"))
				uniOri = uniOri.substring(0,uniOri.indexOf("Cubico")).toLowerCase()+"s"+" c�bicos";
			else
				uniOri = uniOri.toLowerCase()+"s";
			aux= " equivalen a ";
		}
		else {
			if(uniOri.contains("c�bico"))
				uniOri = uniOri.substring(0,uniOri.indexOf("Cubico")).toLowerCase()+" c�bico";
		}	
		
				
		String res = cantOri+" "+uniOri.toLowerCase()+aux+df.format(cantNue).replace(",", ".")+" "+uniNue.toLowerCase();
		
		return res;
			
	}

	
}
