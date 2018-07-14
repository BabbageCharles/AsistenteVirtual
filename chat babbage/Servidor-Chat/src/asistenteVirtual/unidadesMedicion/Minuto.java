package asistenteVirtual.unidadesMedicion;

public class Minuto {

	private double cantidad;
	
	public Minuto(double cant) {
		this.cantidad= cant;
	}
	
	public Segundo convertirA(Segundo s) {
		return new Segundo(this.cantidad*60);
	}
	
	public Hora convertirA(Hora h) {
		return new Hora(this.cantidad*0.0166667);
	}

	public double getCantidad() {
		return cantidad;
	}

}
