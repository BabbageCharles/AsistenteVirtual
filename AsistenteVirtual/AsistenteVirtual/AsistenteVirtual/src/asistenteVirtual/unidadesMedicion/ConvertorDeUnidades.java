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
		
		if(resultado != 1)
			unidadNueva = unidadNueva.toLowerCase()+"s";
		
		if(cantidad != 1)
			unidadOriginal = unidadOriginal.toLowerCase()+"s";
		
		String res = cantidad+" "+unidadOriginal.toLowerCase()+" equivalen a "+df.format(resultado).replace(",", ".")+" "+unidadNueva.toLowerCase();
			
		return res;
	}	
	
	public static String buscarUnidad(String cadena) {
		String uni[]= {"Miligramo","Gramo","Kilo","Dracma","Onza","Libra",
				"Milimetro","Centimetro","Metro","Kilometro",
				"Pie","Pulgada","Yarda"};
		
		String unidad;
		int i=0;
		
		while(!cadena.contains(uni[i].toLowerCase()))
			i++;
		
		unidad= uni[i];		
		return unidad;
		
	}
	
}
