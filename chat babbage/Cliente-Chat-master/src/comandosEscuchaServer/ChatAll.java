package comandosEscuchaServer;

import intefaces.VentanaPrincipal;
import paqueteEnvios.PaqueteMensaje;

public class ChatAll extends ComandoEscuchaServer{

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = gson.fromJson(cadenaLeida, PaqueteMensaje.class);
		String msjAgregar = paqueteMensaje.getUserEmisor() + ": " + paqueteMensaje.getMsjChat() +"\n";
		VentanaPrincipal.setTextoChatGeneral(msjAgregar);
	}

}
