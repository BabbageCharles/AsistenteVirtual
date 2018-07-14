package paqueteEnvios;

import java.io.Serializable;
import java.util.ArrayList;


public class PaqueteSala extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String nombreSala;
	private String texto;
	private String ownerSala;
	private String cliente;
	private int tipo;

	private ArrayList<String> UsuariosConectados = new ArrayList<String>();

	public PaqueteSala(String name, String cliente){
		this.nombreSala = name;
		this.cliente = cliente;
	}

	public PaqueteSala() {
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public ArrayList<String> getUsuariosConectados() {
		return UsuariosConectados;
	}

	public void setUsuariosConectados(ArrayList<String> usuariosConectados) {
		UsuariosConectados = usuariosConectados;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public void setNombreSala(String name) {
		this.nombreSala = name;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getOwnerSala() {
		return ownerSala;
	}

	public void setOwnerSala(String ownerSala) {
		this.ownerSala = ownerSala;
	}

	public void eliminarUsuario(String username) {
		this.UsuariosConectados.remove(username);
	}


	public void agregarUsuario(String cliente) {
		this.UsuariosConectados.add(cliente);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
