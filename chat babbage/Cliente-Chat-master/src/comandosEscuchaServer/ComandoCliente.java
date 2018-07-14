package comandosEscuchaServer;

import cliente.Cliente;
import paqueteEnvios.Comando;

public abstract class ComandoCliente extends Comando{
	protected Cliente cliente;

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
