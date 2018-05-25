package asistenteVirtual.unidadesMedicion;

public class Metro {

	private double cantidad;

	public Metro(double cant) {
		this.cantidad = cant;
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*100);
	}

	public Milimetro convertirA(Milimetro mm){
		return new Milimetro(this.cantidad*1000);
	}
	
	public Yarda convertirA(Yarda yr){
		return new Yarda(this.cantidad* 1.0936);
	}
	
	public double getCantidad() {
		return cantidad;
	}

	

}
