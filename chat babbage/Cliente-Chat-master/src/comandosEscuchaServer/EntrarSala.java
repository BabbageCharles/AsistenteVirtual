package comandosEscuchaServer;

import javax.swing.JOptionPane;

import cliente.Cliente;
import intefaces.Sala;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteSala;

public class EntrarSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaqueteSala paqueteSala = gson.fromJson(cadenaLeida, PaqueteSala.class);
		cliente.setPaqueteSala(paqueteSala);
		if (paqueteSala.getMsj().equals(Paquete.msjExito)) {
			if (!cliente.getSalasActivas().containsKey(paqueteSala.getNombreSala())) {				
				Sala sala = new Sala(cliente);
				
				cliente.getSalasActivas().put(cliente.getPaqueteSala().getNombreSala(), sala);
				escuchaServer.actualizarListaConectadosSala(paqueteSala);
			} else {
				JOptionPane.showMessageDialog(null, "Ya se encuentra conectado a esta sala");
			} 
		} else {
			JOptionPane.showMessageDialog(null, "Error al intentar entrar en la sala " + cliente.getPaqueteSala().getNombreSala());
		}		
	}

}
