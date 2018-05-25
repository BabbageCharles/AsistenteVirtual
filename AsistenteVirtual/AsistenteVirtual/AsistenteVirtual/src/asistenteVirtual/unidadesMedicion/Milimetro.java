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
	
	public Pulgada convertirA(Pulgada p) {
		return new Pulgada(this.cantidad*0.0394);
	}
	public Pie convertirA(Pie p) {
		return new Pie(this.cantidad*0.0033);
	}
	
	public Yarda convertirA(Yarda yr){
		return new Yarda(this.cantidad*0.0011);
	}

	public double getCantidad() {
		return cantidad;
	}

}
