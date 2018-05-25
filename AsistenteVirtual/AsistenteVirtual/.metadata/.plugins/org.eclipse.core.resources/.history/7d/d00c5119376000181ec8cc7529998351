package asistenteVirtual.unidadesMedicion;

public class Milimetro {

	private double cantidad;

	public Milimetro(double cant) {
		this.cantidad = cant;
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad/1000);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad/10);
	}
	
	/*public Kilometro convertirA(Kilometro km){
		return new Kilometro(this.cantidad/1000000);
	}*/
	
	public Yarda convertirA(Yarda yr){
		return new Yarda(this.cantidad* 0.0010936);
	}

	public double getCantidad() {
		return cantidad;
	}

}
