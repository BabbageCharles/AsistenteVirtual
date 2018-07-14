package comandosEscuchaServer;

import cliente.EscuchaServer;
import paqueteEnvios.Comando;


public abstract class ComandoEscuchaServer extends Comando{
	protected EscuchaServer escuchaServer;

	public void setEscuchaServer(EscuchaServer escuchaServer) {
		this.escuchaServer = escuchaServer;
	}
}
