package logica;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtClase;

@Entity
public class Clase implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String hora;
    private String url;
    @Temporal(TemporalType.DATE)
    private Date fechaReg;
    //pseudos
    @ManyToOne
    private Profesor profe;

    @ManyToOne
    private ActividadDeportiva actividad;
    
    @OneToMany(mappedBy="clase",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Registro> registros = new ArrayList<>();

    public Clase(){
        super();
    }

    public Clase(String nombre, Date fecha, String hora, String url, Date fechaReg, Profesor profe){
        super();
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.url = url;
        this.fechaReg = fechaReg;
        this.profe = profe;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Date getFecha(){
        return this.fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public String getHora(){
        return this.hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Date getFechaReg(){
        return this.fechaReg;
    }

    public void setFechaReg(Date fechaReg){
        this.fechaReg = fechaReg;
    }

	public Profesor getProfe() {
		return profe;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
	}

	public ActividadDeportiva getActividad() {
		return actividad;
	}

	public void setActividad(ActividadDeportiva actividad) {
		this.actividad = actividad;
	}

    public boolean obtenerRegistro(String nick){
        for(Registro reg:registros) {
            if (reg.getSocio().getNickname().equals(nombre))
                return true;
        }
        return false;
    }

    public String[] obtenerSocios(){
        String[] retornar = new String[registros.size()];
        int i=0;
        for(Registro reg:registros) {
            retornar[i]=(reg.getSocio().getNickname());
            i++;
        }
        return retornar;
    }

    public void añadirRegistro(Registro reg){
        registros.add(reg);
    }

    public void eliminarRegistro(Registro reg){
        registros.remove(reg);
    }

    public DtClase getDtClase() {
    	return new DtClase(this.nombre, this.fecha, this.hora, this.url, this.fechaReg);
    }
    
    public int getCantidadInscriptos() {
    	return registros.size();
    }
}
