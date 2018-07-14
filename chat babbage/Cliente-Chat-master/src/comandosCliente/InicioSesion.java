package comandosCliente;

import java.io.IOException;

import comandosEscuchaServer.ComandoCliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteUsuario;

public class InicioSesion extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		paqueteUsuario.setComando(Comando.INICIOSESION);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
