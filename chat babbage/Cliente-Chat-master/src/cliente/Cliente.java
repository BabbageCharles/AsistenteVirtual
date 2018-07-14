package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;


import comandosEscuchaServer.ComandoCliente;
import intefaces.Chat;
import intefaces.MenuInicio;
import intefaces.Sala;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteMensaje;
import paqueteEnvios.PaqueteSala;
import paqueteEnvios.PaqueteUsuario;

public class Cliente extends Thread {
	private Socket cliente;
	private static String miIp;
		
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	private PaqueteUsuario paqueteUsuario = new PaqueteUsuario();
	private PaqueteMensaje paqueteMensaje = new PaqueteMensaje();
	private PaqueteSala paqueteSala = new PaqueteSala();

	
	private Map<String, Chat> chatsActivos = new HashMap<>();
	private Map<String, Sala> salasActivas = new HashMap<>();

	private int accion; 

	public Cliente(String newIp, int newPort) {

		try {
			cliente = new Socket(newIp, newPort);
			miIp = cliente.getInetAddress().getHostAddress(); 
			entrada = new ObjectInputStream(cliente.getInputStream()); 
			salida = new ObjectOutputStream(cliente.getOutputStream()); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error al iniciar la app, chequee la conexión al Server" );
			System.exit(1);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				new MenuInicio(this);

				this.wait();

				ComandoCliente comando;
				Paquete paquete = new Paquete();
				
				EscuchaServer es = new EscuchaServer(this);
				es.start();
				
				while (true) {
					paquete.setComando(accion);
					comando = (ComandoCliente) paquete.getObjeto("comandosCliente");
					comando.setCliente(this);
					comando.ejecutar();

					salida.flush();
					
					this.wait();
				}

			} catch (IOException | InterruptedException  e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexión del Cliente.");
				e.printStackTrace();
				System.exit(1);
			} 
		}
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	public int getAccion() {
		return accion;
	}

	public Socket getSocket() {
		return cliente;
	}

	public void setSocket(final Socket cliente) {
		this.cliente = cliente;
	}

	public static String getMiIp() {
		return miIp;
	}

	public void setMiIp(final String miIp) {
		Cliente.miIp = miIp;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(final ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public void setSalida(final ObjectOutputStream salida) {
		this.salida = salida;
	}

	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}

	public PaqueteMensaje getPaqueteMensaje() {
		return paqueteMensaje;
	}

	public void setPaqueteMensaje(PaqueteMensaje paqueteMensaje) {
		this.paqueteMensaje = paqueteMensaje;
	}


	public Map<String, Chat> getChatsActivos() {
		return chatsActivos;
	}

	public Map<String, Sala> getSalasActivas() {
		return salasActivas;
	}

	public void setChatsActivos(Map<String, Chat> chatsActivos) {
		this.chatsActivos = chatsActivos;
	}

	public PaqueteSala getPaqueteSala() {
		return paqueteSala;
	}

	public void setPaqueteSala(PaqueteSala paqueteSala) {
		this.paqueteSala = paqueteSala;
	}

	public void eliminarChatActivo(String clave) {
		this.chatsActivos.remove(clave);
	}

}