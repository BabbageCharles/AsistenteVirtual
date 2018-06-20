package asistenteVirtual.unidadesMedicion;

public class Hora {
	
	private double cantidad;
	
	public Hora(double cant) {
		this.cantidad=cant;
	}
	
	public Segundo convertirA(Segundo s) {
		return new Segundo(this.cantidad*3600);
	}
	
	public Minuto convertirA(Minuto m) {
		return new Minuto(this.cantidad*60);
	}
	
	public double getCantidad() {
		return cantidad;
	}

}
