package asistenteVirtual.unidadesMedicion;

public class YardaCubico {

	private double cantidad;
	
	public YardaCubico(double cantida) {
		this.cantidad = cantida;
	}
	
	public PulgadaCubico convertirA(PulgadaCubico p){
		return new PulgadaCubico(this.cantidad*61023.7);
	}
	
	public PieCubico convertirA(PieCubico p) {
		return new PieCubico(this.cantidad*35.3147);
	}
	
	public MilimetroCubico convertirA(MilimetroCubico c) {
		return new MilimetroCubico(this.cantidad*764554869);
	}
	
	public CentimetroCubico convertirA(CentimetroCubico c) {
		return new CentimetroCubico(this.cantidad*764555);
	}
	
	public MetroCubico convertirA(MetroCubico m) {
		return new MetroCubico(this.cantidad*0.764555);
	}
	
	public double getCantida() {
		return cantidad;
	}

	
	
	
}
