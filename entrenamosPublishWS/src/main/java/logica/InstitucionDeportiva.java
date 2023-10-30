package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.DtActividadDeportiva;
import datatypes.DtInstitucionDeportiva;
import datatypes.DtUsuario;

import java.io.Serializable;
import java.time.ZoneId;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class InstitucionDeportiva implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    private String nombre;
    private String descripcion;
    private String url;
    @OneToMany(mappedBy="institucion",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<ActividadDeportiva> actD = new ArrayList<>();
    
    @OneToMany(mappedBy = "institucion", cascade = CascadeType.ALL)
    private List<Profesor> profesores = new ArrayList<>();
    
    public InstitucionDeportiva(){
        super();
    }
    
    public InstitucionDeportiva(String nombre, String descripcion, String url){
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }
    
    public DtInstitucionDeportiva getDtInstitucion() {
		return (new DtInstitucionDeportiva(this.nombre,this.descripcion,this.url));
	}
    

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void agregarActividadDeportiva(DtActividadDeportiva data){
        ActividadDeportiva i = new ActividadDeportiva(data.getNombre(),data.getDescripcion(),data.getDuracion(),data.getCosto(), data.getFecReg());
		actD.add(i);
		i.setInstitucion(this);
    }
    
    public ArrayList<String> obtenerActividades(){
        ArrayList<String> aRetornar = new ArrayList<>();
        for(ActividadDeportiva i: actD) {
            aRetornar.add(i.getNombre());
        }
        return aRetornar;
    }
    
    public ArrayList<String> obtenerProfesores(){
        ArrayList<String> aRetornar = new ArrayList<>();
        for(Profesor i: profesores) {
            aRetornar.add(i.getNickname());
        }
        return aRetornar;
    }
    
    public ActividadDeportiva buscarActividad(String nombre) {
    	ActividadDeportiva aRetornar;
    	aRetornar = new ActividadDeportiva();
    	for(ActividadDeportiva i:actD) {
    		if (i.getNombre().equals(nombre))
				aRetornar=i;
    	}
    	return aRetornar;
    }
    
    public boolean buscarActividad2(String nombre) {
    	
    	for(ActividadDeportiva i:actD) {
    		if (i.getNombre().equals(nombre))
				return true;
    	}
    	return false;
    }
    
    public void agregarProfesor(Profesor profe){
        //Profesor i = new Profesor();
        profesores.add(profe);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}