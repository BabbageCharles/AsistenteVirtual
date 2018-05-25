package asistenteVirtual.unidadesMedicion;

public class MilimetroCubico {

	private double cantidad;
	
	public MilimetroCubico(double cant) {
		this.cantidad=cant;
	}
	
	public CentimetroCubico convertirA(CentimetroCubico c) {
		return new CentimetroCubico(this.cantidad*0.001);
	}
	
	public MetroCubico convertirA(MetroCubico m) {
		return new MetroCubico(this.cantidad*0.000000001);
	}
	
	public PulgadaCubico convertirA(PulgadaCubico p){
		return new PulgadaCubico(this.cantidad*6.1024e-5); //0.000061023744095
	}
	
	public PieCubico convertirA(PieCubico p) {
		return new PieCubico(this.cantidad*3.5315e-8); //0.00000003531
	}
	
	public YardaCubico convertiaA(YardaCubico y) {
		return new YardaCubico(this.cantidad*1.308e-9);//0.0000000013
	}
	
	public double getCantidad() {
		return cantidad;
	}
	
	
}
