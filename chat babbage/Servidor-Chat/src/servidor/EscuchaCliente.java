package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import com.google.gson.Gson;

import comandos.ComandoServer;
import paqueteEnvios.Comando;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;
import paqueteEnvios.PaqueteSala;
import paqueteEnvios.PaqueteUsuario;

public class EscuchaCliente extends Thread {

	private final Socket socket;
	private final ObjectInputStream entrada;
	private final ObjectOutputStream salida;

	public final Gson gson = new Gson();

	private PaqueteUsuario paqueteUsuario;
	private PaqueteDeUsuariosYSalas paqueteDeUsuarios;



	public EscuchaCliente(String ip, Socket socket, ObjectInputStream entrada, ObjectOutputStream salida) {
		this.socket = socket;
		this.entrada = entrada;
		this.salida = salida;
		paqueteUsuario = new PaqueteUsuario();
	}

	public void run() {
		try {
			ComandoServer comando;
			Paquete paquete;
			
			String cadenaLeida = (String) entrada.readObject();

			while (!((paquete = gson.fromJson(cadenaLeida, Paquete.class)).getComando() == Comando.DESCONECTAR)) {							

				comando = (ComandoServer) paquete.getObjeto("comandos");
				comando.setCadena(cadenaLeida);
				comando.setEscuchaCliente(this);
				comando.ejecutar();

				salida.flush();
				synchronized (entrada) {
					cadenaLeida = (String) entrada.readObject();
				}
			}

			paqueteUsuario = (PaqueteUsuario) (gson.fromJson(cadenaLeida, PaqueteUsuario.class));

			if (paqueteUsuario.getUsername()!=null) {
				Servidor.getLog().append(paqueteUsuario.getUsername() + " ha Cerrado Sesión" + System.lineSeparator());

				entrada.close();
				salida.close();
				socket.close();

				Servidor.desconectarUsuario(paqueteUsuario.getUsername(),this);
				
				for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
					paqueteDeUsuarios = new PaqueteDeUsuariosYSalas(Servidor.getUsuariosConectados());
					paqueteDeUsuarios.setComando(Comando.CONEXION);
					conectado.salida.writeObject(gson.toJson(paqueteDeUsuarios, PaqueteDeUsuariosYSalas.class));
				}
				
				for (String nombreSala : Servidor.getNombresSalasDisponibles()) {
					if(Servidor.getSalas().get(nombreSala).getUsuariosConectados().contains(paqueteUsuario.getUsername())) {
						Servidor.getSalas().get(nombreSala).getUsuariosConectados().remove(paqueteUsuario.getUsername());
						PaqueteSala paq = Servidor.getSalas().get(nombreSala);
						String s = gson.toJson(paq);
						for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
							if(paq.getUsuariosConectados().contains(conectado.getPaqueteUsuario().getUsername())) {
								conectado.getSalida().writeObject(s);
							}
						}
					}
				}
			} else {
				int index = Servidor.getSocketsConectados().indexOf(socket);
				Servidor.getSocketsConectados().remove(index);
				Servidor.getClientesConectados().remove(index);
			}

			Servidor.getLog().append(paquete.getIp() + " se ha desconectado " + System.lineSeparator());

		} catch (IOException | ClassNotFoundException e) {
			Servidor.getLog().append("Hubo un error de conexion: " + e.getMessage() + System.lineSeparator());
			e.printStackTrace();
		} 
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}

	public void setPaqueteUsuario(PaqueteUsuario paqueteUsuario) {
		this.paqueteUsuario = paqueteUsuario;
	}
}