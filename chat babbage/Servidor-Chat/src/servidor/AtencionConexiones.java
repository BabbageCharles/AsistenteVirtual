
package servidor;

import com.google.gson.Gson;

import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class AtencionConexiones extends Thread {

	private final Gson gson = new Gson();

	public AtencionConexiones() {
	}

	public void run() {
		synchronized (this) {
			try {
				while (true) {

					wait();
					PaqueteDeUsuariosYSalas pdu = (PaqueteDeUsuariosYSalas) new PaqueteDeUsuariosYSalas(Servidor.getUsuariosConectados(), Servidor.getNombresSalasDisponibles(),Servidor.getSalasPrivadasNombresDisponibles())
							.clone();
					pdu.setComando(Comando.CONEXION);
					String s = gson.toJson(pdu);
					for (EscuchaCliente conectado : Servidor.getClientesConectados())
						conectado.getSalida().writeObject(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}