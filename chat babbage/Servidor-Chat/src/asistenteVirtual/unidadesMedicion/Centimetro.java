package asistenteVirtual.unidadesMedicion;

public class Centimetro {

	private double cantidad;

	public Centimetro(double cant) {
		this.cantidad = cant;
	}
	
	public Metro convertirA(Metro mt){
		return new Metro(this.cantidad/100);
	}

	public Milimetro convertirA(Milimetro mm){
		return new Milimetro(this.cantidad*10);
	}
	
	public Pulgada convertirA(Pulgada p) {
		return new Pulgada(this.cantidad*0.3937);
	}
	public Pie convertirA(Pie p) {
		return new Pie(this.cantidad*0.0328);
	}
	
	public Yarda convertirA(Yarda yr){
		return new Yarda(this.cantidad*0.0109);
	}
	
	public double getCantidad() {
		return cantidad;
	}


	
}
