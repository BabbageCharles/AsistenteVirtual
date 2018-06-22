package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;
import chat.Deuda;
import chat.ServidorChat;

public class CalcularDeudas implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public static String USUARIO;

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;

	}

	@Override
	public String escuchar(String pedido, String user) {
		String error = new String("No entiendo el mensaje");
		List<String> deudas = Arrays.asList("deuda", "deudas", "simplificar", "debo", "me debe", "gastamos", "pago",
				"pagó", "pague", "pagué");
		String b = deudas.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);
		USUARIO = user;
		if (!b.equals(error))
			return estadoDeudas(pedido);
		return siguiente.escuchar(pedido, USUARIO);
	}

	private String estadoDeudas(String pedido) {

		float monto;
		String nombre[];
		nombre = Utilitarias.buscarNombre(pedido);
		monto = Utilitarias.buscarNumero(pedido);
		if(monto != (float)-1) {			
			if(pedido.contains("me debe"))
				return meDebe(nombre[0],monto);//VER VECTOR NOMBRES
			if(pedido.contains("le debo"))
				return leDebo(nombre[0], monto);
		} else {			
			if(pedido.contains("cuanto"))	// ver que no llame al de abajo
				return cuantoMeDebe(nombre[0]);	
			if(pedido.contains("estado de deuda"))
				return estadoDeudas();
			if(pedido.contains("simplificar"))
				return simplificar(nombre);
		}

		return null;
	}

	private String meDebe(String nombre, float monto) {
		if(ServidorChat.actualizarDeudas(nombre,monto,USUARIO)){
			return "Anotado";
		}		
		return "Lo siento, se produjo un error";		
	}
	
	private String leDebo(String nombre, float monto) {
		if(ServidorChat.actualizarDeudas(USUARIO,monto,nombre)){
			return "Anotado";
		}		
		return "Lo siento, se produjo un error";		
	}

	private String simplificar(String[] nombres) {
		int cantNom = nombres.length;
		
		
		return "@"+USUARIO+" bueno.";
			

	}
	
	private String cuantoMeDebe(String nombre) {
		List<Deuda> d= ServidorChat.levantarDeudas(nombre,USUARIO);
		if(d.isEmpty())
			return "No hay deuda de @"+nombre;
		else {
			float n= d.get(0).getDinero();
			if(n==0)
				return "No hay deuda de @"+nombre;
			else
				return "@"+USUARIO+" @"+nombre+" te debe $"+n;
		}		
		
	}
	
	private String estadoDeudas() {
		List<Deuda> misdeudas= ServidorChat.MisDeudas(USUARIO);
		List<Deuda> misdeudores = ServidorChat.MisDeudores(USUARIO);
		String resultado= "@"+USUARIO;
		
		for(Deuda d: misdeudas) {
			if(misdeudas.size()==1)
				resultado+=" le debés $"+d.getDinero()+" a @"+d.getAcreedor()+".";
			else
				resultado+=" le debés $"+d.getDinero()+" a @"+d.getAcreedor()+",";
		}
		
		if(misdeudas.size()>1) {
			resultado= resultado.substring(0, resultado.length()-1);
			resultado+=". ";
		}
		
		for(Deuda d: misdeudores) {
			if(misdeudores.size()==1)
				resultado+="@"+d.getDeudor()+" te debe $"+d.getDinero();
			else
				resultado+="@"+d.getDeudor()+" te debe $"+d.getDinero()+",";
		}
		
		if(misdeudores.size()>1) {
			resultado= resultado.substring(0, resultado.length()-1);
		}
		
		return resultado;
			
	}

}
