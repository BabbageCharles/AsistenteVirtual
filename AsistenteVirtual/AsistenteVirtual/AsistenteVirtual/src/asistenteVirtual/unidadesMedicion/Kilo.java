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
	
	public Dracma convertirA(Dracma d) {
		return new Dracma(this.cantidad*564.383);
	}
	
	public Onza convertirA(Onza o) {
		return new Onza(this.cantidad*35.2740);
	}
	
	public Libra convertirA(Libra l) {
		return new Libra(this.cantidad*2.2046);
	}

	public double getCantidad() {
		return cantidad;
	}
	
}
