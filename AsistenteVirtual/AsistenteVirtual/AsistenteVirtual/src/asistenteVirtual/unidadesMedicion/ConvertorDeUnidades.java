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
		String paquete ="asistenteVirtual.unidadesMedicion.";
		
		String unidadOriginal= ConvertorDeUnidades.buscarUnidad(cadena.substring(cadena.indexOf(cantidad.toString())));
		String uniOriginal= unidadOriginal;	
		
		String unidadNueva = ConvertorDeUnidades.buscarUnidad(cadena.substring(0,cadena.indexOf(cantidad.toString())));
		String uniNueva= unidadNueva;		
		
		if(unidadNueva.endsWith("s"))
			unidadNueva = unidadNueva.substring(0, unidadNueva.indexOf("s"));
		if(unidadOriginal.endsWith("s"))
			unidadOriginal = unidadOriginal.substring(0, unidadOriginal.indexOf("s"));	
		
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
			// TODO: handle exception
		}
		
		if(resultado == 1)
			uniNueva = unidadNueva.toLowerCase();
		
		String res = cantidad+" "+uniOriginal.toLowerCase()+" equivalen a "+df.format(resultado).replace(",", ".")+" "+uniNueva.toLowerCase();
			
		return res;
	}	
	
	public static String buscarUnidad(String cadena) {
		String uni[]= {"Miligramos","Miligramo","Gramos","Gramo","Centimetros","Centimetro","Milimetros","Milimetro","Kilometros","Kilometro","Metros","Metro","Yardas","Yarda","Pulgadas","Pulgada","Pies","Pie","Kilos","Kilo"};
		String unidad;
		int i=0;
		
		while(!cadena.contains(uni[i].toLowerCase()))
			i++;
		
		unidad= uni[i];		
		return unidad;
		
	}
	
}