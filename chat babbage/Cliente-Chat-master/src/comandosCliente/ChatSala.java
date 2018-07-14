package comandosCliente;

import java.io.IOException;

import comandosEscuchaServer.ComandoCliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;

public class ChatSala extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = cliente.getPaqueteMensaje();
		paqueteMensaje.setComando(Comando.CHATSALA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteMensaje));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
