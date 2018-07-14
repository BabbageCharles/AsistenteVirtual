package comandos;

import java.io.IOException;
import java.net.Socket;

import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;
import servidor.EscuchaCliente;
import servidor.Servidor;

public class ChatSala extends ComandoServer{
	

	@SuppressWarnings("unused")
	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje =  (gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		try {
			paqueteMensaje.setComando(Comando.CHATSALA);
			Socket s2 = Servidor.getMapConectados().get(paqueteMensaje.getUserEmisor());
			for(EscuchaCliente conectado : Servidor.getClientesConectados()){
				if(Servidor.getSalas().get(paqueteMensaje.getNombreSala()).getUsuariosConectados().contains(conectado.getPaqueteUsuario().getUsername()) 
						&& conectado.getSocket() != s2){
					conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
				}
			}
			String msjAgregar = paqueteMensaje.getUserEmisor() + ": " + paqueteMensaje.getMsj() + "\n";			
			
		} catch (IOException e) {
			Servidor.getLog().append("Error al enviar el mensaje de " + paqueteMensaje.getUserEmisor() + " para la sala "+ paqueteMensaje.getNombreSala() + System.lineSeparator());
		}
		
	}

}
