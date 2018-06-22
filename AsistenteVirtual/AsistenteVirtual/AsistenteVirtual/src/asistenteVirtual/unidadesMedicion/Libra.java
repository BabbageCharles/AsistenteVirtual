package asistenteVirtual.unidadesMedicion;

public class Libra {
	
	private double cantidad;
	
	public Libra(double cant) {
		this.cantidad = cant;
	}
	
	public Dracma convertirA(Dracma d) {
		return new Dracma(this.cantidad*210.7);
	}
	public Onza convertirA(Onza a) {
		return new Onza(this.cantidad*16);
	}
	
	public Miligramo convertiA(Miligramo m) {
		return new Miligramo(this.cantidad*453592);
	}
	
	public Gramo convertirA(Gramo g) {
		return new Gramo(this.cantidad*453.5920);
	}
	
	public Kilo convertirA(Kilo k) {
		return new Kilo(this.cantidad*0.4536);
	}

	public double getCantidad() {
		return cantidad;
	}	

}
