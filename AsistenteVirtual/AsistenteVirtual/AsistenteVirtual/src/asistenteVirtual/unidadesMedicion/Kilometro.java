package asistenteVirtual.unidadesMedicion;

public class Kilometro {

	private double cantidad;

	public Kilometro(double cant) {
		this.cantidad = cant;
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad*1000);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*100000);
	}

	public Milimetro convertirA(Milimetro ml){
		return new Milimetro(this.cantidad*1000000);
	}
	
	public double getCantidad(){
		return this.cantidad;
	}
}
