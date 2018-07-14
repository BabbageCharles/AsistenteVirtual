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
	
	public Dracma convertirA(Dracma d) {
		return new Dracma(this.cantidad*0.0005644);
	}
	
	public Onza convertirA(Onza o) {
		return new Onza(this.cantidad*0.000035);
	}
	
	public Libra convertirA(Libra l) {
		return new Libra(this.cantidad*0.000002);
	}

	public double getCantidad() {
		return cantidad;
	}

}
