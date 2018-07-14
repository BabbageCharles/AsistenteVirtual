package comandosEscuchaServer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import intefaces.MenuInicio;
import intefaces.VentanaPrincipal;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class Registro extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMsj().equals(Paquete.msjExito)) {
			JOptionPane.showMessageDialog(null, "Registro exitoso.");
			PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);
			ArrayList<String> salas = paqueteUS.getSalas();
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
			new VentanaPrincipal(escuchaServer.getCliente());
			escuchaServer.actualizarListaSalas();
		} else {
			if (paquete.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar.");
			} else {
				JOptionPane.showMessageDialog(null, "El usuario ya se encuentra en uso.");
			}
			escuchaServer.getCliente().getPaqueteUsuario().setUsername(null);
			new MenuInicio(escuchaServer.getCliente());
		}
	}

}
