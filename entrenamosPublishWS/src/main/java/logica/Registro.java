package logica;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import persistencia.RegistroID;

@Entity
@IdClass(RegistroID.class)
public class Registro {
	@Id
	@ManyToOne
	private Socio socio;
	@Id
	@ManyToOne
	private Clase clase;
	
	
	@Temporal(TemporalType.DATE)
	private Date fechaReg;
	
	public Registro(){
		super();
	}
	public Registro(Socio socio, Clase clase, Date fechaReg){
		super();
		this.socio=socio;
		this.clase=clase;
		this.setFechaReg(fechaReg);
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Date getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

}
