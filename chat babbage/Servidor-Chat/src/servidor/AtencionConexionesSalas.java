package servidor;

import com.google.gson.Gson;

import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteSala;

public class AtencionConexionesSalas extends Thread {

	private final Gson gson = new Gson();
	private String nombreSala;


	public AtencionConexionesSalas() {
	}

	public void run() {
		synchronized (this) {
			try {
				while (true) {

					wait();

					if (nombreSala != null) {
						PaqueteSala ps = Servidor.getSalas().get(nombreSala);						
						ps.setComando(Comando.CONEXIONSALA);
						String s = gson.toJson(ps);
						for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
							if (ps.getUsuariosConectados()
									.contains(conectado.getPaqueteUsuario().getUsername())) {
								conectado.getSalida().writeObject(s);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
}