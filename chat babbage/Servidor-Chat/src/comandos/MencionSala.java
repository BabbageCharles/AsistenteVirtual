package comandos;

import java.io.IOException;
import java.net.Socket;

import asistenteVirtual.respuestas.ResponseHandler;
import paqueteEnvios.Comando;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteMensaje;
import servidor.EscuchaCliente;
import servidor.Servidor;

public class MencionSala extends ComandoServer {

	private String msjAgregar;
	public  ResponseHandler handler = new ResponseHandler();
	
	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = (gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		
		Socket s2 = Servidor.getMapConectados().get(paqueteMensaje.getUserEmisor());
		paqueteMensaje.setMsj(Paquete.msjExito);
	

		try {
			if(!paqueteMensaje.getNombreSala().equals("Ventana Principal")){
				paqueteMensaje.setComando(Comando.MENCIONSALA);
				for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
					if (Servidor.getSalas().get(paqueteMensaje.getNombreSala()).getUsuariosConectados()
							.contains(conectado.getPaqueteUsuario().getUsername()) && conectado.getSocket() != s2) {
						conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
					}
				}
						
				
				
				if (!paqueteMensaje.getUserReceptor().toUpperCase().equals("JENKINS") && !Servidor.mencionUsuario(paqueteMensaje)) {
					paqueteMensaje.setMsj(Paquete.msjFracaso);
					msjAgregar = "El usuario " + paqueteMensaje.getUserReceptor() + " "
							+ " no existe o se encuentra desconectado.";
					paqueteMensaje.setMsjChat(msjAgregar);
					paqueteMensaje.setUserEmisor("Servidor");
					paqueteMensaje.setComando(Comando.CHATSALA);
					

					for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
						if (Servidor.getSalas().get(paqueteMensaje.getNombreSala()).getUsuariosConectados()
								.contains(conectado.getPaqueteUsuario().getUsername())) {
							conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
						}
					}
				}
			} else {
				paqueteMensaje.setComando(Comando.MENCIONSALA);
				for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
					if (conectado.getSocket() != s2) {
						conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
					}
				}
				if (!paqueteMensaje.getUserReceptor().toUpperCase().equals("JENKINS") && !Servidor.mencionUsuario(paqueteMensaje)) {
					paqueteMensaje.setMsj(Paquete.msjFracaso);
					paqueteMensaje.setComando(Comando.CHATALL);
					msjAgregar = "El usuario " + paqueteMensaje.getUserReceptor() + " "
							+ " no existe o se encuentra desconectado.";
					paqueteMensaje.setMsjChat(msjAgregar);
					paqueteMensaje.setUserEmisor("Servidor");
					
					for (EscuchaCliente conectado : Servidor.getClientesConectados()) 
						conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));

				}
			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar enviar el mensaje de " + paqueteMensaje.getUserEmisor() + " para la sala "+ paqueteMensaje.getNombreSala() + "." + System.lineSeparator());
			e.printStackTrace();
		}
		
		if(paqueteMensaje.getUserReceptor().toUpperCase().equals("JENKINS")) {			
			synchronized (Servidor.getJenkins()) {
				System.out.println(paqueteMensaje.getNombreSala()+" "+paqueteMensaje.getUserEmisor());
				Servidor.getJenkins().setPaqueteMensaje(paqueteMensaje);

				handler.responseSend(paqueteMensaje.getMsjChat(), paqueteMensaje.getUserEmisor(), paqueteMensaje.getNombreSala(),Servidor.getJenkins());
				System.out.println(paqueteMensaje.getNombreSala()+" "+paqueteMensaje.getUserEmisor());			
				
				
			}
		}
	}

}


