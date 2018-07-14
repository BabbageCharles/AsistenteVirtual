package baseDatos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@SuppressWarnings("serial")
@Entity
@IdClass(DeudaPk.class)
public class Deuda implements Serializable{
	
	
	@Id
	protected String acreedor;
	@Id
	protected String deudor;
	
	private float dinero;	
	
	public Deuda() {
		
	}
	

	public String getAcreedor() {
		return acreedor;
	}
	public void setAcreedor(String acreedor) {
		this.acreedor = acreedor;
	}
	public String getDeudor() {
		return deudor;
	}
	public void setDeudor(String deudor) {
		this.deudor = deudor;
	}
	public float getDinero() {
		return dinero;
	}
	public void setDinero(float dinero) {
		this.dinero = dinero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acreedor == null) ? 0 : acreedor.hashCode());
		result = prime * result + ((deudor == null) ? 0 : deudor.hashCode());
		result = prime * result + Float.floatToIntBits(dinero);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deuda other = (Deuda) obj;
		if (acreedor == null) {
			if (other.acreedor != null)
				return false;
		} else if (!acreedor.equals(other.acreedor))
			return false;
		if (deudor == null) {
			if (other.deudor != null)
				return false;
		} else if (!deudor.equals(other.deudor))
			return false;
		if (Float.floatToIntBits(dinero) != Float.floatToIntBits(other.dinero))
			return false;
		return true;
	}
	

}
