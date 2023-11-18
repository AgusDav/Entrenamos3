package logica;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import datatypes.DtClase;
import datatypes.DtUsuario;
import java.io.Serializable;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorColumn(name="TIPOUSUARIO")
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    protected String nickname;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
    @Temporal(TemporalType.DATE)
    protected Date fecNac;

    public Usuario(){
        super();
    }

    public Usuario(String nickname, String nombre, String apellido, String email, String password, Date fecNac){
        super();
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fecNac = fecNac;
    }
    
    public abstract DtUsuario getDtUsuario();
    public abstract void añadirRegistro(Registro reg);

    public abstract void eliminarRegistro(Registro reg);
    public abstract void añadirClase(Clase reg);
    
    public abstract String[] obtenerClases();

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname =  nickname;
    }

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return this.password;}

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getFecNac(){
        return this.fecNac;
    }

    public void setFecNac(Date fecNac){
        this.fecNac = fecNac;
    }

}