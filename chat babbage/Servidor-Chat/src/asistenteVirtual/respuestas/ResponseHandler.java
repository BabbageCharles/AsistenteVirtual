package asistenteVirtual.respuestas;

import java.io.IOException;

import asistenteVirtual.AsistenteVirtual;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;
import servidor.EscuchaCliente;
import servidor.Servidor;

public  class ResponseHandler extends Comando {

	public  void responseSend(String mensaje, String destinatario, String nombreSala,AsistenteVirtual jenkins) {
		
		jenkins.setUSUARIO(destinatario);
		String msjFinal = jenkins.escuchar(mensaje,destinatario);
		
	try {
		PaqueteMensaje paqueteMensaje = new PaqueteMensaje("@Jenkins", destinatario, msjFinal, nombreSala);
		paqueteMensaje.setComando(Comando.CHATSALA);
		if (!nombreSala.equals("Ventana Principal")) {
			for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
				if (Servidor.getSalas().get(paqueteMensaje.getNombreSala()).getUsuariosConectados()
						.contains(conectado.getPaqueteUsuario().getUsername())) {
					conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
				}
			}
			
		} else {
			paqueteMensaje.setComando(Comando.CHATALL);
			for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
				conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
			}
		}
	} catch (IOException e) {
		Servidor.getLog().append("Error al tratar de responder a la solicitud" + System.lineSeparator());
		e.printStackTrace();
	}
	}

	@Override
	public void ejecutar() {

	}
}
