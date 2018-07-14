package comandosEscuchaServer;

import java.util.ArrayList;

import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class Conexión extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		ArrayList<String> usuariosConectados = new ArrayList<String>();
		usuariosConectados = (ArrayList<String>) gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class)
				.getUsuarios();

		usuariosConectados.remove(escuchaServer.getCliente().getPaqueteUsuario().getUsername());
		escuchaServer.getCliente().getPaqueteUsuario().setListaDeConectados(usuariosConectados);
		escuchaServer.actualizarLista();
	}

}
