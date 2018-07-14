package comandosEscuchaServer;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import intefaces.MenuInicio;
import intefaces.VentanaPrincipal;
import paqueteEnvios.Paquete;
import paqueteEnvios.PaqueteDeUsuariosYSalas;

public class InicioSesion extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);

		if (paqueteUS.getMsj().equals(Paquete.msjExito)) {
			ArrayList<String> salas = paqueteUS.getSalas();
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
			ArrayList<String> salasp = paqueteUS.getSalasprivadas();//VER
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalasPrivadas(salasp);//VER
			new VentanaPrincipal(escuchaServer.getCliente());
			escuchaServer.actualizarListaSalas();
		} else {
		
			if (paqueteUS.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "Error al iniciar sesión. Revise el usuario y la contraseña");
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe una sesión iniciada con ese usuario.");
			}
			escuchaServer.getCliente().getPaqueteUsuario().setUsername(null);
			new MenuInicio(escuchaServer.getCliente());
		}
	}

}
