package asistenteVirtual.unidadesMedicion;

public class PulgadaCubico {
	
	private double cantidad;
	
	public PulgadaCubico(double cant) {
		this.cantidad=cant;
	}
	
	public PieCubico convertirA(PieCubico p) {
		return new PieCubico(this.cantidad*35.3147);
	}
	
	public YardaCubico convertiaA(YardaCubico y) {
		return new YardaCubico(this.cantidad*1.30795);
	}
	
	public MilimetroCubico convertirA(MilimetroCubico c) {
		return new MilimetroCubico(this.cantidad*16387.064);
	}
	
	public CentimetroCubico convertirA(CentimetroCubico c) {
		return new CentimetroCubico(this.cantidad*16.3871);
	}
	
	public MetroCubico convertirA(MetroCubico m) {
		return new MetroCubico(this.cantidad*1.6387e-5);
	}

	public double getCantidad() {
		return cantidad;
	}	

}
