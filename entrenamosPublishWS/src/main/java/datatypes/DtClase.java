package datatypes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DtClase {
	private String nombre;	
	private Date fecha;
	private String horaInicio;
	private String url;
	private Date fechaReg;

	public DtClase(){
		super();
	}
	public DtClase(String n, Date f, String h, String u, Date fR) {
		super();
		this.nombre = n;
		this.fecha = f;
		this.horaInicio = h;
		this.url = u;
		this.fechaReg = fR;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}
	@Override
	public String toString() {
		return "Nombre: " + getNombre() + "\nFecha: " + getFecha() + "\nHora inicio: " + getHoraInicio() + "\nUrl: " + getUrl() + "\nFecha registro: " + getFechaReg();
	}
	
	
	
}
