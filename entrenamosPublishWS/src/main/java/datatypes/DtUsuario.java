package datatypes;

import java.time.LocalDate;
import java.util.Date;

public abstract class DtUsuario {
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;

	protected String password;
	protected Date fecNac;

	public DtUsuario(String n, String nom, String a, String e, String p, Date f) {
		super();
		this.nickname = n;
		this.nombre = nom;
		this.apellido = a;
		this.email = e;
		this.password = p;
		this.fecNac = f;
	}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecNac() {
		return fecNac;
	}
	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}
	@Override
	public String toString() {
		return "Nickname: " + nickname + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nEmail: " + email + "\nFecha nacimiento: " + fecNac;
	}
}
	
	