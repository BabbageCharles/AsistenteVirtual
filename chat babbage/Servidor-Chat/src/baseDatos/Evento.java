package baseDatos;


public class Evento {
	
	@Override
	public String toString() {
		return "Evento: " + evento;
	}

	private int id;
	private String evento;
	private String usuario;

	
	public Evento() {
		
	}
	
	

	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}


	

}
