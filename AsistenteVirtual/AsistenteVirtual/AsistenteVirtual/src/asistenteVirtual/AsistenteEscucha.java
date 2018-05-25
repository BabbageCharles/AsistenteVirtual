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
		System.out.println(AsistenteEscucha.escuchar("@jenkins cu�nto es 1 + 2"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos metros son 100 centimetros?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos centimetros son 1 metro?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos centimetros son 1 kilometro?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos kilometros son 100000 centimetros?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos kilometros son 1 yarda?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos yardas son 5 kilometros?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos yardas son 5 centimetros?"));
		System.out.println(AsistenteEscucha.escuchar("@jenkins cuantos kilometros son 1 yarda?"));
		
		
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
		
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("UnidadMasa"))
			return AsistenteVirtual.convert(msjIn);
		
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("UnidadLongitud"))
			return AsistenteVirtual.convert(msjIn);

/*		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-Agradecer")) {// DOS PEDIDOS
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.agradecer(msjIn);
		}

		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-DarFecha")) {
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.darFecha(msjIn);
		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Agradecer-DarFecha")) {
			return AsistenteVirtual.agradecer(msjIn) + "," + AsistenteVirtual.darFecha(msjIn);
		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-Calcular")) {
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.calculo(msjIn);
		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Agradecer-Calcular")) {
			return AsistenteVirtual.agradecer(msjIn) + "," + AsistenteVirtual.calculo(msjIn);
		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("DarFecha-Calcular")) {
		return AsistenteVirtual.darFecha(msjIn) + "," + AsistenteVirtual.calculo(msjIn);
		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-Agradecer-DarFecha")) {// TRES PEDIDOS
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.agradecer(msjIn);

		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-Agradecer-Calcular")) {
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.agradecer(msjIn) + ","
					+ AsistenteVirtual.calculo(msjIn);

		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-DarFecha-Calcular")) {
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.calculo(msjIn) + ","
					+ AsistenteVirtual.darFecha(msjIn);

		}
		if (nuevoEscucha.reconocerPedidos(msjIn).equals("Saludar-Agradecer-DarFecha-Calcular")) {// 4 PEDIDOS
			return AsistenteVirtual.saludar(msjIn) + "," + AsistenteVirtual.calculo(msjIn) + ","
					+ AsistenteVirtual.darFecha(msjIn) + "," + AsistenteVirtual.agradecer(msjIn);
		}*/

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
		
		List<String> unidadDeMasa = Arrays.asList("miligramo","miligramos","gramo","gramos",
				 "kilo","kilos","dracma","dracmas","onza","onzas","libra","libras");
		
		List<String> unidadDeLongitud = Arrays.asList("centimetros","centimetro","milimetro",
				"milimetros","Kilometros","Kilometro","metros","metro","yardas","yarda","pulgadas",
				"pulgada","pies","pie");
		
		

		String a = saludos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String b = agradecimientos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String c = fechas.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		String d = calculos.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst()
				.orElse("No entiendo el mensaje");
		String e= unidadDeMasa.stream().filter(x -> msj.toLowerCase().matches(x)).findFirst().orElse(error);
		String f= unidadDeLongitud.stream().filter(x -> msj.toLowerCase().contains(x)).findFirst().orElse(error);
		

		// un solo pedido
		if (!a.equals(error) && b.equals(error) && c.equals(error) && d.equals(error) && e.equals(error) && f.equals(error))
			return "Saludar";

		if (a.equals(error) && !b.equals(error) && c.equals(error) && d.equals(error) && e.equals(error) && f.equals(error))
			return "Agradecer";

		if (a.equals(error) && b.equals(error) && !c.equals(error) && d.equals(error) && e.equals(error) && f.equals(error))
			return "DarFecha";

		if (a.equals(error) && b.equals(error) && c.equals(error) && !d.equals(error) && e.equals(error) && f.equals(error))
			return "Calcular";
		
		if (a.equals(error) && b.equals(error) && c.equals(error) && d.equals(error) && !e.equals(error) && f.equals(error))
			return "UnidadMasa";

		if (a.equals(error) && b.equals(error) && c.equals(error) && d.equals(error) && e.equals(error) && !f.equals(error))
			return "UnidadLongitud";
		
/*		// dos pedidos
		if (!a.equals(error) && !b.equals(error) && c.equals(error) && d.equals(error))
			return "Saludar-Agradecer";

		if (!a.equals(error) && b.equals(error) && !c.equals(error) && d.equals(error))
			return "Saludar-DarFecha";

		if (a.equals(error) && !b.equals(error) && !c.equals(error) && d.equals(error))
			return "Agradecer-DarFecha";

		if (!a.equals(error) && b.equals(error) && c.equals(error) && !d.equals(error))
			return "Saludar-Calcular";
		if (!a.equals(error) && !b.equals(error) && c.equals(error) && !d.equals(error))
			return "Agradecer-Calcular";

		if (!a.equals(error) && b.equals(error) && !c.equals(error) && !d.equals(error))
			return "DarFecha-Calcular";

		// tres pedidos 
		if (!a.equals(error) && !b.equals(error) && !c.equals(error) && d.equals(error))
			return "Saludar-Agradecer-DarFecha";

		if (!a.equals(error) && !b.equals(error) && c.equals(error) && !d.equals(error))
			return "Saludar-Agradecer-Calcular";

		if (!a.equals(error) && b.equals(error) && !c.equals(error) && !d.equals(error))
			return "Saludar-DarFecha-Calcular";*/

		// cuatro pedidos o no entendimiento
		if (!a.equals(error) && !b.equals(error) && !c.equals(error) && !d.equals(error))
			return "Saludar-Agradecer-DarFecha-Calcular";
		else
			return "Disculpa... no entiendo el pedido, @" + USUARIO + " �podr�as repetirlo?";
	}

}
