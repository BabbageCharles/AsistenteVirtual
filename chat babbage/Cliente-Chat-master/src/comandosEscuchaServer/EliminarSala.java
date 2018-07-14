package comandosEscuchaServer;

import javax.swing.JOptionPane;

import cliente.Cliente;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteSala;

public class EliminarSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) gson.fromJson(cadenaLeida, PaqueteSala.class);

		if(paqueteSala.getMsj().equals(Paquete.msjExito)) {
			Cliente cliente = escuchaServer.getCliente();
			if(cliente.getSalasActivas().containsKey(paqueteSala.getNombreSala()) ) {
				
				JOptionPane.showMessageDialog(null, "La sala " + paqueteSala.getNombreSala() + " ha sido eliminada.");
				cliente.getSalasActivas().get(paqueteSala.getNombreSala()).dispose();
				cliente.getSalasActivas().remove(paqueteSala.getNombreSala());
			}
			cliente.getPaqueteUsuario().eliminarSala(paqueteSala.getNombreSala());
			escuchaServer.actualizarListaSalas();
		} else if(paqueteSala.getMsj().equals(Paquete.msjFracaso)) {
			JOptionPane.showMessageDialog(null, "Error al tratar de eliminar la sala.");
		} else if(paqueteSala.getMsj().equals(Paquete.msjFallo)){
			JOptionPane.showMessageDialog(null, "La sala solo puede ser eliminada por el user que la creo ");
		}
	}
}
