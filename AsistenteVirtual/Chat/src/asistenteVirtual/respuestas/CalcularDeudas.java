package asistenteVirtual.respuestas;

import java.util.Arrays;
import java.util.List;

import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;
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

		int monto;
		String nombre[];
		monto = Utilitarias.buscarEntero(pedido);
		if (monto == -1) {
			simplificar(pedido);
		} else {
			nombre = Utilitarias.buscarNombre(pedido);
			if(pedido.contains("me debe"))
				return meDebe(nombre[0],monto);//VER VECTOR NOMBRES
		}

		return null;
	}

	private String meDebe(String nombre, int monto) {
		if(ServidorChat.actualizarDeudas(nombre,monto,USUARIO)){
			return "Anotado";
		}
		
		return "dfads";		
	}

	private void simplificar(String pedido) {
		int cantNom;
		cantNom = Utilitarias.cantidadNombres(pedido);

	}

}
