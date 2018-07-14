package asistenteVirtual.unidadesMedicion;

public class Yarda {

	private double cantidad;

	public Yarda(double cant) {
		this.cantidad = cant;
	}
	
	public Pulgada convertirA(Pulgada p) {
		return new Pulgada(this.cantidad*36);
	}
	
	public Pie convertirA(Pie p) {
		return new Pie(this.cantidad*3);
	}
	
	public Milimetro convertirA(Milimetro ml){
		return new Milimetro(this.cantidad*914.4000);
	}
	
	public Centimetro convertirA(Centimetro cm){
		return new Centimetro(this.cantidad*91.4400);
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad*0.9144);
	}
	
	public double getCantidad(){
		return this.cantidad;
	}
	

}
