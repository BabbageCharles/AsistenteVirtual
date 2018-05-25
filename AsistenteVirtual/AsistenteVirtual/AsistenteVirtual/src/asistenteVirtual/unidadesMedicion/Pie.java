package asistenteVirtual.unidadesMedicion;

public class Pie {

	private double cantidad;

	public Pie(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Pulgada convertirA(Pulgada p) {
		return new Pulgada(this.cantidad*12);
	}
	
	public Yarda convertirA(Yarda y) {
		return new Yarda(this.cantidad*0.3333);
	}
	
	public Milimetro convertirA(Milimetro ml){
		return new Milimetro(this.cantidad*304.8000);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*30.4800);
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad*0.3048);
	}
	
	public double getCantidad() {
		return cantidad;
	}
	
	
}
