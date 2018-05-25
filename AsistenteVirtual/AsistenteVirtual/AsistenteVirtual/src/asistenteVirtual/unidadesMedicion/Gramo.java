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
	
	public Dracma convertirA(Dracma d) {
		return new Dracma(this.cantidad*0.564);
	}
	
	public Onza convertirA(Onza o) {
		return new Onza(this.cantidad*0.03527);
	}
	
	public Libra convertirA(Libra l) {
		return new Libra(this.cantidad* 0.0022);
	}
	
}
