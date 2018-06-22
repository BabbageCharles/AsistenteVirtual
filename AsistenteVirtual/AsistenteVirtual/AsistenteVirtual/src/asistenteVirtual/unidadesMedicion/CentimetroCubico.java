package asistenteVirtual.unidadesMedicion;

public class CentimetroCubico {

	private double cantidad;
	
	public CentimetroCubico(double cant) {
		this.cantidad=cant;
	}
	
	public MilimetroCubico convertirA(MilimetroCubico c) {
		return new MilimetroCubico(this.cantidad*1000);
	}
	
	public MetroCubico convertirA(MetroCubico m) {
		return new MetroCubico(this.cantidad*0.000001);
	}
	
	public PulgadaCubico convertirA(PulgadaCubico p){
		return new PulgadaCubico(this.cantidad*0.0610237);
	}
	
	public PieCubico convertirA(PieCubico p) {
		return new PieCubico(this.cantidad*3.5315e-5); //0.00003531
	}
	
	public YardaCubico convertiaA(YardaCubico y) {
		return new YardaCubico(this.cantidad*1.308e-6); //0.0000013
	}
	
	
	public double getCantidad() {
		return cantidad;
	}	
}
