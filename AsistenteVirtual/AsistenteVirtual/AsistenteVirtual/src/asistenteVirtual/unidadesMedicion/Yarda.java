package asistenteVirtual.unidadesMedicion;

public class Yarda {

	private double cantidad;

	public Yarda(double cant) {
		this.cantidad = cant;
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad*0.914401829);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad* 91.44);
	}
	
	public Milimetro convertirA(Milimetro ml){
		return new Milimetro(this.cantidad* 914.4);
	}
	
	public double getCantidad(){
		return this.cantidad;
	}
	

}
