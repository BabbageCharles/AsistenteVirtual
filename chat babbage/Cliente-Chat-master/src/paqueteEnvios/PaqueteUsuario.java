package paqueteEnvios;

import java.io.Serializable;
import java.util.List;


public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String username;
	private List<String> listaDeConectados;
	private List<String> listaDeSalas;
	private List<String> listaDeSalasPrivadas;
	private String password;

	public PaqueteUsuario() {
	}

	public List<String> getListaDeConectados() {
		return listaDeConectados;
	}

	public void setListaDeConectados(List<String> listaDeConectados) {
		this.listaDeConectados = listaDeConectados;
	}

	public PaqueteUsuario(String user) {
		username = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getListaDeSalas() {
		return listaDeSalas;
	}

	public void setListaDeSalas(List<String> listaDeSalas) {
		this.listaDeSalas = listaDeSalas;
	}

	public void eliminarUsuario(String username) {
		this.listaDeConectados.remove(username);
	}

	public void eliminarSala(String nombreSala) {
		this.listaDeSalas.remove(nombreSala);		
	}

	public List<String> getListaDeSalasPrivadas() {
		return listaDeSalasPrivadas;
	}
	
	public void setListaDeSalasPrivadas(List<String> listaDeSalasP) {
		this.listaDeSalasPrivadas = listaDeSalasP;
	}
	
}

                                                               