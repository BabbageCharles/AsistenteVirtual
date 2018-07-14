package comandos;

import java.io.IOException;
import java.net.Socket;

import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;
import servidor.EscuchaCliente;
import servidor.Servidor;

public class ChatAll extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = (PaqueteMensaje) (gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		try {
			paqueteMensaje.setComando(Comando.CHATALL);
			
			Socket s1 = Servidor.getMapConectados().get(paqueteMensaje.getUserEmisor());
			for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
				if(conectado.getSocket() != s1)	{
					conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
				}
			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar mandar el mensaje de "+ paqueteMensaje.getUserEmisor() + System.lineSeparator());
		}
		
	}

}
