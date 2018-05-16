package asistenteVirtual;

import java.text.DecimalFormat;

public class UnidadDeMasa {
	
	public final static double dracmaGramo = 1.77;
	public final static double onzaGramo = 28.35;
	public final static double libraGramo = 453.59;
	
	public final static double mgGramo = 0.001;
	public final static double kgGramo = 1000;
	
	public final static double dracmaOnza = 0.0625;
	public final static double libraOnza = 16;
	
	public static String darConversion(String cadena) {
		String res="";
		DecimalFormat df = new DecimalFormat("#.###"); // doy formato por si da decimales los muestra y sino no
		int index1;
		int index2;
		int valor=Utilitarias.buscarEntero(cadena);;	
		
		if(cadena.contains("miligramo") && cadena.contains("gramo")) {
			
			index1 = cadena.indexOf("miligramo");
			index2= cadena.indexOf("gramo");			
			String mlagr = df.format(UnidadDeMasa.miligramoAgramo(valor)).replace(",", ".");
			String graml= df.format(UnidadDeMasa.gramoAmiligramo(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" miligramos equivalen a "+mlagr+" gramos";
			else
				res= valor+" gramos equivalen a "+graml+" miligramos";
		}
		
		if(cadena.contains("miligramo") && cadena.contains("kilo")) {
			valor = Utilitarias.buscarEntero(cadena);
			index1 = cadena.indexOf("miligramo");
			index2= cadena.indexOf("kilo");			 
			String mlakg = df.format(UnidadDeMasa.miligramoAkilo(valor)).replace(",", ".");
			String kgaml= df.format(UnidadDeMasa.kiloAmiligramo(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" miligramos equivalen a "+mlakg+" kilos";
			else
				res= valor+" kilos equivalen a "+kgaml+" miligramos";
		}
		
		if(cadena.contains("gramo") && cadena.contains("kilo") && !cadena.contains("miligramo")) {
			valor = Utilitarias.buscarEntero(cadena);
			index1 = cadena.indexOf("gramo");
			index2= cadena.indexOf("kilo");			 
			String grakg = df.format(UnidadDeMasa.gramoAkilo(valor)).replace(",", ".");
			String kgagr= df.format(UnidadDeMasa.kiloAgramos(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" gramos equivalen a "+grakg+" kilos";
			else
				res= valor+" kilos equivalen a "+kgagr+" gramos";
		}
		
		if(cadena.contains("dracma") && cadena.contains("onza")) {
			
			index1 = cadena.indexOf("dracma");
			index2= cadena.indexOf("onza");			 
			String dracaonza = df.format(UnidadDeMasa.dracmaAonza(valor)).replace(",", ".");
			String onzaadrac= df.format(UnidadDeMasa.onzaAdracma(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" dracmas equivalen a "+dracaonza+" onzas";
			else
				res= valor+" onzas equivalen a "+onzaadrac+" dracmas";
		}
		
		if(cadena.contains("libra") && cadena.contains("onza")) {
			
			index1 = cadena.indexOf("libra");
			index2= cadena.indexOf("onza");			 
			String libraaonza = df.format(UnidadDeMasa.libraAonza(valor)).replace(",", ".");
			String onzalibra= df.format(UnidadDeMasa.onzaAlibra(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" libras equivalen a "+libraaonza+" onzas";
			else
				res= valor+" onzas equivalen a "+onzalibra+" libras";
		}
		
		if(cadena.contains("libra") && cadena.contains("dracma")) {
			
			index1 = cadena.indexOf("libra");
			index2= cadena.indexOf("dracma");			 
			String libraadrac = df.format(UnidadDeMasa.libraAdracma(valor)).replace(",", ".");
			String dracalibra= df.format(UnidadDeMasa.dracmaAlibra(valor)).replace(",", ".");
			if(index1>index2)
				res= valor+" libras equivalen a "+libraadrac+" dracmas";
			else
				res= valor+" dracmas equivalen a "+dracalibra+" libras";
		}
		
		
		
		return res;
	}
	
	public static double miligramoAgramo(double masa) {
		return masa*mgGramo;
	}
	
	public static double miligramoAkilo(double masa) {
		double conv= masa*mgGramo;
		return conv/kgGramo;
	}
	
	public static double gramoAmiligramo(double masa) {
		return masa/mgGramo;
	}
	
	public static double gramoAkilo(double masa) {
		return masa/kgGramo;
	}
	
	public static double kiloAmiligramo(double masa) {
		double conv= masa*kgGramo;
		return conv/mgGramo;
	}
	
	public static double kiloAgramos(double masa) {
		return masa*kgGramo;
	}
	
	public static double dracmaAonza(double masa){
		return masa*dracmaOnza;
	}
	
	public static double dracmaAlibra(double masa){
		double conv= masa*dracmaOnza;
		return conv/libraOnza;
	}
	
	public static double onzaAdracma(double masa){
		return masa/dracmaOnza;
	}
	
	public static double onzaAlibra(double masa){
		return masa/libraOnza;
	}
	
	public static double libraAdracma(double masa){
		double conv=masa*libraOnza;
		return conv/dracmaOnza;
	}
	
	public static double libraAonza(double masa){
		return masa*libraOnza;
	}
	
	
	
}
