package asistenteVirtual.unidadesMedicion;

public class Metro {

	private double cantidad;

	public Metro(double cant) {
		this.cantidad = cant;
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*100);
	}

	public double getCantidad() {
		return cantidad;
	}

	

}
