package asistenteVirtual;

public interface AsistenteEscucha {	
	
	public void establecerSiguiente(AsistenteEscucha siguiente);

	public String escuchar(String pedido,String user);
}
