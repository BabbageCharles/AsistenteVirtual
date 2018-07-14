
package comandosEscuchaServer;

import cliente.Cliente;
import intefaces.Chat;
import paqueteEnvios.PaqueteMensaje;

public class MP extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaqueteMensaje paqueteMensaje = gson.fromJson(cadenaLeida, PaqueteMensaje.class);

		if (!(cliente.getChatsActivos().containsKey(paqueteMensaje.getUserEmisor()))) {
			Chat chat = new Chat(cliente);

			chat.setTitle(paqueteMensaje.getUserEmisor());

			cliente.getChatsActivos().put(paqueteMensaje.getUserEmisor(), chat);
		}
		String msjAgregar = paqueteMensaje.getUserEmisor() + ": "+ paqueteMensaje.getMsjChat() + "\n";
		cliente.getChatsActivos().get(paqueteMensaje.getUserEmisor()).agregarMsj(msjAgregar);
	}

}
