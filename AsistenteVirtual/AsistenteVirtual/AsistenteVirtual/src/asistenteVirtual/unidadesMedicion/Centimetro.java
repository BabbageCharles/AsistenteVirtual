package asistenteVirtual.unidadesMedicion;

public class Centimetro {

	private double cantidad;

	public Centimetro(double cant) {
		this.cantidad = cant;
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad/100);
	}

	public double getCantidad() {
		return cantidad;
	}


	
}
