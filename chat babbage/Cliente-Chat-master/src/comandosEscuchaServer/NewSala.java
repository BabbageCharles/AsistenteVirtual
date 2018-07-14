package comandosEscuchaServer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import cliente.Cliente;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class NewSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaqueteDeUsuariosYSalas paqueteDUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);

		if( paqueteDUS.getMsj().equals(Paquete.msjExito)) {
			ArrayList<String> listadoSalas = paqueteDUS.getSalas();
			cliente.getPaqueteUsuario().setListaDeSalas(listadoSalas);
			escuchaServer.actualizarListaSalas();
		} else {
			if(paqueteDUS.getMsj().equals(Paquete.msjFallo))
				JOptionPane.showMessageDialog(null, "Sala ya existente.");
			else
				JOptionPane.showMessageDialog(null, "Error al tratar de crear la sala.");
		}		
	}

}
