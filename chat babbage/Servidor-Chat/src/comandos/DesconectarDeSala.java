package comandos;

import java.io.IOException;

import paqueteEnvios.Comando;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteSala;
import servidor.Servidor;

public class DesconectarDeSala extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {
			if(Servidor.getNombresSalasDisponibles().contains(paqueteSala.getNombreSala()) 
					&& Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados().contains(paqueteSala.getCliente())) {
				
				Servidor.getSalas().get(paqueteSala.getNombreSala()).eliminarUsuario(paqueteSala.getCliente());
				paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());
				paqueteSala.setComando(Comando.DESCONECTARDESALA);
				
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

				synchronized(Servidor.getAtencionConexionesSalas()){
					Servidor.getAtencionConexionesSalas().setNombreSala(paqueteSala.getNombreSala());
					Servidor.getAtencionConexionesSalas().notify();
				}
				
			} else {
				paqueteSala.setComando(Comando.DESCONECTARDESALA);
				paqueteSala.setMsj(Paquete.msjFracaso);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar informar al usuario " + escuchaCliente.getPaqueteUsuario().getUsername() + " sobre su intento de desconectarse de la sala " + paqueteSala.getNombreSala() + System.lineSeparator() );
		}		
	}

}
