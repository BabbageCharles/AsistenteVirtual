package asistenteVirtual.unidadesMedicion;

public class Miligramo {

	private double cantidad;
	
	public Miligramo(double cant) {
		this.cantidad= cant;
	}
	
	public Gramo convertirA(Gramo g) {
		return new Gramo(this.cantidad/1000);
	}
	
	public Kilo convertirA(Kilo k) {
		double cant=this.cantidad/1000;
		return new Kilo(cant/1000);
	}

	public double getCantidad() {
		return cantidad;
	}

}