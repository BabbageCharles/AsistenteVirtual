package asistenteVirtual.respuestas;

import asistenteVirtual.AsistenteEscucha;
 
public class LeyesRobotica implements AsistenteEscucha {

	private AsistenteEscucha siguiente;

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;

	}

	@Override
	public String escuchar(String pedido) {
		if (pedido.contains("leyes") || pedido.contains("rob�tica"))
			return leyesRobotica();
		return siguiente.escuchar(pedido);
	}

	public static String leyesRobotica() {
		String leyes = ("1-Un robot no har� da�o a un ser humano, ni permitir� con su inacci�n que sufra da�o.\n"
				+ "2-Un robot debe cumplir las �rdenes dadas por los seres humanos, a excepci�n de aquellas que entrasen en conflicto con la primera ley.\n"
				+ "3-Un robot debe proteger su propia existencia en la medida en que esta protecci�n no entre en conflicto con la primera o con la segunda ley.");
		return leyes;
	}

}
