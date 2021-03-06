package asistenteVirtual.unidadesMedicion;

public class PieCubico {

private double cantidad;
	
	public PieCubico(double cant) {
		this.cantidad = cant;
	}
	
	public PulgadaCubico convertirA(PulgadaCubico p){
		return new PulgadaCubico(this.cantidad*61023.7);
	}	
	
	public YardaCubico convertiaA(YardaCubico y) {
		return new YardaCubico(this.cantidad*1.30795);
	}
	
	public MilimetroCubico convertirA(MilimetroCubico c) {
		return new MilimetroCubico(this.cantidad*2.832e+7);
	}
	
	public CentimetroCubico convertirA(CentimetroCubico c) {
		return new CentimetroCubico(this.cantidad*28316.846592);
	}
	
	public MetroCubico convertirA(MetroCubico m) {
		return new MetroCubico(this.cantidad*0.0283168);
	}
	
	public double getCantida() {
		return cantidad;
	}
}
