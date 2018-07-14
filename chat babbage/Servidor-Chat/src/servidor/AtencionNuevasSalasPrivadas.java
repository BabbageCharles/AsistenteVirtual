package servidor;

import com.google.gson.Gson;

import paqueteEnvios.Comando;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class AtencionNuevasSalasPrivadas extends Thread {

	private final Gson gson = new Gson();

	public AtencionNuevasSalasPrivadas() {
	}

	public void run() {
		synchronized (this) {
			try {
				while (true) {

					wait();

					PaqueteDeUsuariosYSalas psu =  (PaqueteDeUsuariosYSalas) new PaqueteDeUsuariosYSalas(null,Servidor.getNombresSalasDisponibles(),Servidor.getSalasPrivadasNombresDisponibles())
							.clone();
					psu.setComando(Comando.NEWSALA);
					psu.setMsj(Paquete.msjExito);
					String s = gson.toJson(psu);
					for (EscuchaCliente conectado : Servidor.getClientesConectados())
							conectado.getSalida().writeObject(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}