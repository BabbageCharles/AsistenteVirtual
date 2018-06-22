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
		if (pedido.contains("leyes") || pedido.contains("robótica"))
			return leyesRobotica();
		return siguiente.escuchar(pedido);
	}

	public static String leyesRobotica() {
		String leyes = ("1-Un robot no hará daño a un ser humano, ni permitirá con su inacción que sufra daño.\n"
				+ "2-Un robot debe cumplir las órdenes dadas por los seres humanos, a excepción de aquellas que entrasen en conflicto con la primera ley.\n"
				+ "3-Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la primera o con la segunda ley.");
		return leyes;
	}

}
