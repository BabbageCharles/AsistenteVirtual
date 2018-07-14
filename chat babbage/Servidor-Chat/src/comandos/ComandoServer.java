package comandos;

import paqueteEnvios.Comando;
import servidor.EscuchaCliente;

public abstract class ComandoServer extends Comando{
	protected EscuchaCliente escuchaCliente;

	public void setEscuchaCliente(EscuchaCliente escuchaCliente) {
		this.escuchaCliente = escuchaCliente;
	}
	

}
