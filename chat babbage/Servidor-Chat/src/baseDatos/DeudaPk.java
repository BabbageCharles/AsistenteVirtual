package baseDatos;

import java.io.Serializable;

//@Embeddable
@SuppressWarnings("serial")
public class DeudaPk implements Serializable {

	protected String acreedor;
	protected String deudor;
	
	public DeudaPk() {
		
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


	public DeudaPk(String acredor,String deudor) {
		this.acreedor= acredor;
		this.deudor= deudor;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acreedor == null) ? 0 : acreedor.hashCode());
		result = prime * result + ((deudor == null) ? 0 : deudor.hashCode());
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
		DeudaPk other = (DeudaPk) obj;
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
		return true;
	}
	
	
}
