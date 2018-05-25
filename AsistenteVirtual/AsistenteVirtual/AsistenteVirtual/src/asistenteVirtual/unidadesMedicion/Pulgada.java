package asistenteVirtual.unidadesMedicion;

public class Pulgada {

	private double cantidad;

	public Pulgada(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Pie convertirA(Pie p) {
		return new Pie(this.cantidad*0.0833);
	}
	
	public Yarda convertirA(Yarda y) {
		return new Yarda(this.cantidad*0.0278);
	}
	
	public Milimetro convertirA(Milimetro ml){
		return new Milimetro(this.cantidad*25.4000);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*2.5400);
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad*0.0254);
	}
	
	public double getCantidad() {
		return cantidad;
	}	
	
}
