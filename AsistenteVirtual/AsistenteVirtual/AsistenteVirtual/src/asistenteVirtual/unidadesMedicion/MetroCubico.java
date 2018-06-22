package asistenteVirtual.unidadesMedicion;

public class MetroCubico {
	
	private double cantidad;
	
	public MetroCubico(double cant) {
		this.cantidad=cant;
	}
	
	public MilimetroCubico convertirA(MilimetroCubico c) {
		return new MilimetroCubico(this.cantidad*1000000000);
	}
	
	public CentimetroCubico convertirA(CentimetroCubico c) {
		return new CentimetroCubico(this.cantidad*1000000);
	}
	
	public PulgadaCubico convertirA(PulgadaCubico p){
		return new PulgadaCubico(this.cantidad*61023.7);
	}
	
	public PieCubico convertirA(PieCubico p) {
		return new PieCubico(this.cantidad*35.3147);
	}
	
	public YardaCubico convertiaA(YardaCubico y) {
		return new YardaCubico(this.cantidad*1.30795);
	}
	
	public double getCantidad() {
		return cantidad;
	}	

}
