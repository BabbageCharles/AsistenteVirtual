package asistenteVirtual.unidadesMedicion;

public class Gramo {

	private double cantidad;
	
	public Gramo(double cant) {
		this.cantidad = cant;
	}
	
	public Miligramo convertirA(Miligramo ml) {
		return new Miligramo(this.cantidad*1000);
	}
	
	public Kilo convertirA(Kilo k) {
		return new Kilo(this.cantidad/1000);
	}

	public double getCantidad() {
		return cantidad;
	}
	
}
