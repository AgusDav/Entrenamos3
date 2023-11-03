package persistencia;

import java.io.Serializable;
import java.util.Objects;

//No es una entidad, debe implementar serializable
public class RegistroID implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String clase;
	private String socio;
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clase, socio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroID other = (RegistroID) obj;
		return Objects.equals(clase, other.clase) && Objects.equals(socio, other.socio);
	}
}
