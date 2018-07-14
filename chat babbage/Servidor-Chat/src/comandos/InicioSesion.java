package comandos;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import paqueteEnvios.Comando;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;
import paqueteEnvios.PaqueteUsuario;
import servidor.Servidor;

public class InicioSesion extends ComandoServer {

	@SuppressWarnings("static-access")
	@Override
	public void ejecutar() {

		PaqueteUsuario paqueteUsuario = (PaqueteUsuario) (gson.fromJson(cadenaLeida, PaqueteUsuario.class));

		try {

			if(!Servidor.getUsuariosConectados().contains(paqueteUsuario.getUsername())) {
				if (Servidor.getConector().loguearUser(paqueteUsuario)) {
					escuchaCliente.setPaqueteUsuario(paqueteUsuario);
					PaqueteDeUsuariosYSalas pus = new PaqueteDeUsuariosYSalas(Servidor.getUsuariosConectados(),
							Servidor.getNombresSalasDisponibles(),Servidor.getSalasPrivadasNombresDisponibles());//VER
					pus.setComando(Comando.INICIOSESION);
					pus.setMsj(Paquete.msjExito);

					Servidor.conectarUsuario(paqueteUsuario.getUsername());
					
					escuchaCliente.getSalida().writeObject(gson.toJson(pus));

					synchronized (Servidor.getAtencionConexiones()) {
						Servidor.getAtencionConexiones().notify();
					}

				} else {
					paqueteUsuario.setMsj(Paquete.msjFracaso);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
				} 
			} else {
				paqueteUsuario.setMsj(Paquete.msjFallo);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
			}
		} catch (JsonSyntaxException | IOException e) {
			Servidor.getLog().append("Fallo al intentar informar al usuario "+ paqueteUsuario.getUsername() + " sobre su intento de inicio de sesion." + System.lineSeparator());
		} 
	}
}
