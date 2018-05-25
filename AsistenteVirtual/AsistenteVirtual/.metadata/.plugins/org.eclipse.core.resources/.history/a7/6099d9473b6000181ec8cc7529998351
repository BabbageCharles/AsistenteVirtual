package asistenteVirtual.unidadesMedicion;

public class Kilo {

	private double cantidad;
	
	public Kilo(double cant) {
		this.cantidad= cant;
	}
	
	public Miligramo convertirA(Miligramo ml) {
		double cant= this.cantidad*1000;
		return new Miligramo(cant*1000);
	}
	
	public Gramo convertirA(Gramo g) {
		return new Gramo(this.cantidad*1000);
	}

	public double getCantidad() {
		return cantidad;
	}
	
}
