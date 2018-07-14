package comandosCliente;

import java.io.IOException;

import cliente.Cliente;
import comandosEscuchaServer.ComandoCliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteUsuario;

public class Desconectar extends ComandoCliente {

	@Override
	public void ejecutar() {	
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		paqueteUsuario.setIp(Cliente.getMiIp());
		paqueteUsuario.setComando(Comando.DESCONECTAR);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
