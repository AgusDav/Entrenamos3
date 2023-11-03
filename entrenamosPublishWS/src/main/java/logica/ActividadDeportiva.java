package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import java.io.Serializable;
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
@Entity
public class ActividadDeportiva implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
    private String nombre;
    private String descripcion;
    private int duracion;
    private float costo;
    @Temporal(TemporalType.DATE)
    private Date fecReg;
    @ManyToOne
    private InstitucionDeportiva institucion;
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    private List<Clase> clases = new ArrayList<>();

    public ActividadDeportiva() {
        super();
    }

    public ActividadDeportiva(String n, String d, int dur, float c, Date f) {
        super();
        this.nombre = n;
        this.descripcion = d;
        this.duracion = dur;
        this.costo  = c;
        this.fecReg = f;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public Date getFecReg() {
        return fecReg;
    }
    public void setFecReg(Date fecReg) {
        this.fecReg = fecReg;
    }

	public InstitucionDeportiva getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionDeportiva institucion) {
		this.institucion = institucion;
	}
	
	public List<Clase> getClases() {
	    return clases;
	}
	
	public void agregarClase(DtClase data, Profesor profe){
		Clase i = new Clase(data.getNombre(), data.getFecha(), data.getHoraInicio(), data.getUrl(), data.getFechaReg(), profe);
		clases.add(i);
		i.setActividad(this);
	}
	
	public Clase obtenerClase(String nombre) {
    	Clase aRetornar;
    	aRetornar = new Clase();
    	for(Clase i:clases) {
    		if (i.getNombre().equals(nombre))
				aRetornar=i;
    	}
    	return aRetornar;
    }
	

	public DtActividadDeportiva getDtActividadDeportiva() {
		return new DtActividadDeportiva(this.nombre, this.descripcion, this.duracion, this.costo, this.getFecReg());
	}
}
