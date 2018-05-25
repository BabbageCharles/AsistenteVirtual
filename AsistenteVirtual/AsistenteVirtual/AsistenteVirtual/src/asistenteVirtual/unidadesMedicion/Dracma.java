package asistenteVirtual.unidadesMedicion;

public class Dracma {
	
	private double cantidad;
	
	public Dracma(double cant) {
		this.cantidad=cant;
	}
	
	public Onza convertirA(Onza o) {
		return new Onza(this.cantidad*0.0625);
	}
	
	public Libra convertirA(Libra l) {
		return new Libra(this.cantidad*0.003906);
	}
	
	public Miligramo convertiA(Miligramo m) {
		return new Miligramo(this.cantidad*1772);
	}
	
	public Gramo convertirA(Gramo g) {
		return new Gramo(this.cantidad*1.772);
	}
	
	public Kilo convertirA(Kilo k) {
		return new Kilo(this.cantidad*0.001772);
	}

	public double getCantidad() {
		return cantidad;
	}
}
