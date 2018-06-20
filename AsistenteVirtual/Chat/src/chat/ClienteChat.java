
package chat;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class ClienteChat {

	VentanaChat vch;

	private Socket socket;

	private Usuario user;
	public boolean flag;
	

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	

	public ClienteChat() {

		// ventana de registro o inicio
		RegistrarIniciar ri = new RegistrarIniciar(this);

		if (ri.salio) {
			System.out.println("Quieres conectarte a " + user.getHost() + " en el puerto " + user.getPuerto()
					+ " con el nombre de ususario: " + user.getUsuario() + ".");
			
			///VENTANA PRINCIPAL
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Pantalla_Principal frame = new Pantalla_Principal(user);
						frame.setVisible(true);	
						frame.pack();						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			////
			///NO VA ACA---VER DONDE VA
			// Se crea el socket para conectar con el Sevidor del Chat
			try {
				socket = new Socket(user.getHost(), user.getPuerto());
				//vch=new VentanaChat(this);
				//vch.setVisible(true);

			} catch (UnknownHostException ex) {
				System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
			} catch (IOException ex) {
				System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
			}

		} else {
			MostrarError error = new MostrarError("Usuario o contrase�a incorrecta");
			error.setVisible(true);
			error.pack();
			System.out.println("error al registrar o iniciar sesion");
		}
		flag = ri.salio;
	}

	public void recibirMensajesServidor() {
		// Obtiene el flujo de entrada del socket
		DataInputStream entradaDatos = null;
		String mensaje;
		try {
			entradaDatos = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Error al crear el stream de entrada: " + ex.getMessage());
		} catch (NullPointerException ex) {
			System.out.println("El socket no se creo correctamente. HOLA");
		}

		// Bucle infinito que recibe mensajes del servidor
		boolean conectado = true;
		while (conectado) {
			try {
				mensaje = entradaDatos.readUTF();
				vch.textArea.append(mensaje + System.lineSeparator());
			} catch (IOException ex) {
				System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
				conectado = false;
			} catch (NullPointerException ex) {
				System.out.println("El socket no se creo correctamente. ke ");
				conectado = false;
			}
		}
	}

	public static void main(String[] args) {
		ClienteChat c = new ClienteChat();
		if (c.flag)
			c.recibirMensajesServidor();
		else {
			System.exit(0);
		}
	}

}