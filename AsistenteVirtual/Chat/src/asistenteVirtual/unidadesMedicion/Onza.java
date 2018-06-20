package asistenteVirtual.unidadesMedicion;

public class Onza {
	
	private double cantidad;
	
	public Onza(double cant) {
		this.cantidad=cant;
	}
	
	public Dracma convertirA(Dracma d) {
		return new Dracma(this.cantidad*16);
	}
	
	public Libra convertirA(Libra l) {
		return new Libra(this.cantidad*0.0625);
	}
	
	public Miligramo convertiA(Miligramo m) {
		return new Miligramo(this.cantidad*28349);
	}
	
	public Gramo convertirA(Gramo g) {
		return new Gramo(this.cantidad*28.3495);
	}
	
	public Kilo convertirA(Kilo k) {
		return new Kilo(this.cantidad*0.0283);
	}

	public double getCantidad() {
		return cantidad;
	}

}
