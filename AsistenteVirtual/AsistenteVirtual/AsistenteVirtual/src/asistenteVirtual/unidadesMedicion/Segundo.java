package asistenteVirtual.unidadesMedicion;

public class Segundo {

	private double cantidad;
	
	public Segundo(double cant) {
		this.cantidad = cant;
	}
	
	public Minuto convertirA(Minuto m) {
		return new Minuto(this.cantidad*0.0166667);
	}
	
	public Hora convertirA(Hora h) {
		return new Hora(this.cantidad*0.0003);
	}
	
	public double getCantidad() {
		return cantidad;
	}	
	
	
	
}
