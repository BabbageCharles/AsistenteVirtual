package asistenteVirtual;

import java.util.Arrays;
import java.util.List;

public class AsistenteEscucha extends AsistenteVirtual {

	// CONSTRUCTORES
	public AsistenteEscucha(String s) {
		// TODO Auto-generated constructor stub
	}

	public AsistenteEscucha() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos metros son 100 centimetros?"));
		/*System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos gramo son 1 kilo?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos centimetros son 1 metro?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos metros son 100000 centimetros?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos centimetro son 1 yarda?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos yardas son 5 milimetro?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos yardas son 5 centimetros?"));
		System.out.println(ConvertorDeUnidades.darConversion("@jenkins cuantos kilometros son 1 yarda?"));*/
		
		
	}

	// RESPONDE
	public static String escuchar(String msjIn) {
		AsistenteEscucha nuevoEscucha = new AsistenteEscucha();
		
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar"))// UN SOLO PEDIDO
			return AsistenteVirtual.saludar(msjIn);

		if (nuevoEscucha.reconocerPedidos(msjIn).equals("DarFecha"))
			return AsistenteVirtual.darFecha(msjIn);

		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Agradecer"))
			return AsistenteVirtual.agradecer(msjIn);
		
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Calcular")) {
			if(msjIn.contains("%"))
				return AsistenteVirtual.porcentaje(msjIn);
			else
			{
				if(msjIn.contains("(")){
					int pos= msjIn.indexOf("(");
					String s = msjIn.substring(pos);
					return AsistenteVirtual.calculo(s);
				}
				else {
				String n= Integer.toString(Utilitarias.buscarEntero(msjIn));				
				int pos= msjIn.indexOf(n);
				String s = msjIn.substring(pos);				
				return AsistenteVirtual.calculo(s); 
				}
			}			
		}
		
		if(msjIn.contains("leyes") || msjIn.contains("rob�tica")) 						
			return AsistenteVirtual.leyesRobotica(msjIn);
		
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("UnidadMedicion"))
			return AsistenteVirtual.convertor(msjIn);

		return "Disculpa... no entiendo el pedido, @" + USUARIO + " �podr�as repetirlo?";// NO ENTENDIMIENTO DEL MENSAJE
	}

	// RECONOCE DE QUE PEDIDO SE TRATA
	public String reconocerPedidos(String msj) {
		String error = new String("No entiendo el mensaje");
		List<String> saludos = Arrays.asList("chau", "adios", "hasta luego", "bye", "goodbye", "good bye", "saludos",
				"s2", "hola", "hello", "buen dia", "buen d�a", "hey", "buenos dias", "buenas", "buenas tardes",
				"buenas noches", "alo", "holis", "holi", "hi");
		List<String> agradecimientos = Arrays.asList("agradezco", "gracias", "thanks", "ty", "thank you", "arigato");
		List<String> fechas = Arrays.asList("qu� d�a ser�", "que dia sera", "que dia", "cuantos dias", "que dia ser�",
				"lunes", "martes", "mi�rcoles", "jueves", "viernes", "cu�ntos dias", "qu� d�a ser� dentro de",
				"qu� dia", "cu�nto falta", "qu� dia", "cu�ntas semanas", "cu�ntos meses", "cuantas semanas",
				"cuantos meses", "hace cu�nto", "pasaron desde", "faltan hasta", "d�as para", "semanas para",
				"meses para","hora","la fecha por favor","�qu� d�a es","hoy","me dec�s la fecha");
		List<String> calculos = Arrays.asList("Cu�nto es", "%", "+", "-", "elevado a", "raiz de", "menos", "*", "5/5",
				":", "^", "dividido", "sqrt", "porciento", "resultado de", "al cuadrado", "al cubo", "a la cuarta",
				"raiz cuadrada", "ra�z c�bica");		
		List<String> unidadDeMedicion = Arrays.asList("miligramo","miligramos","gramo","gramos",
				 "kilo","kilos","dracma","dracmas","onza","onzas","libra","libras","centimetros","centimetro","milimetro",
					"milimetros","Kilometros","Kilometro","metros","metro","yardas","yarda","pulgadas",
					"pulgada","pies","pie");		
		

		String a = saludos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String b = agradecimientos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String c = fechas.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String d = calculos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse("No entiendo el mensaje");
		String e= unidadDeMedicion.stream().filter(x -> msj.toLowerCase().matches(x)).findFirst().orElse(error);		

		
		if (!a.equals(error) && b.equals(error) && c.equals(error) && d.equals(error) && e.equals(error))
			return "Saludar";

		if (a.equals(error) && !b.equals(error) && c.equals(error) && d.equals(error) && e.equals(error))
			return "Agradecer";

		if (a.equals(error) && b.equals(error) && !c.equals(error) && d.equals(error) && e.equals(error))
			return "DarFecha";

		if (a.equals(error) && b.equals(error) && c.equals(error) && !d.equals(error) && e.equals(error))
			return "Calcular";
		
		if (a.equals(error) && b.equals(error) && c.equals(error) && d.equals(error) && !e.equals(error))
			return "UnidadMedicion";		

		return "Disculpa... no entiendo el pedido, @" + USUARIO + " �podr�as repetirlo?";
	}

}
