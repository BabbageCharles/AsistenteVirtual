package paqueteEnvios;

import java.io.Serializable;

public class Paquete implements Serializable, Cloneable {

	
	private static final long serialVersionUID = 1L;
	public static String msjExito = "1";
	public static String msjFracaso = "0";
	public static String msjFallo = "2";

	private String mensaje;
	private String ip;
	private int comando;

	public Paquete() {
	}

	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	public Paquete(int comando) {
		this.comando = comando;
	}

	public void setMsj(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setComando(int comando) {
		this.comando = comando;
	}

	public String getMsj() {
		return mensaje;
	}

	public String getIp() {
		return ip;
	}

	public int getComando() {
		return comando;
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	public Comando getObjeto(String nombrePaquete) {
		
		Comando c = null;
		try {
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.NOMBRES[comando]).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	

}
