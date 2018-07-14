package comandosCliente;

import java.io.IOException;

import comandosEscuchaServer.ComandoCliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteSala;

public class NewSala extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = cliente.getPaqueteSala();
		paqueteSala.setComando(Comando.NEWSALA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteSala));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
