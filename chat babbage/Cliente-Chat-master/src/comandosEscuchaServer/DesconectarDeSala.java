package comandosEscuchaServer;

import cliente.Cliente;
import paqueteEnvios.PaqueteSala;

public class DesconectarDeSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = gson.fromJson(cadenaLeida, PaqueteSala.class);
		Cliente cliente = escuchaServer.getCliente();
		if(cliente.getSalasActivas().containsKey(paqueteSala.getNombreSala())) {
			cliente.getSalasActivas().remove(paqueteSala.getNombreSala());
		}
	}

}
