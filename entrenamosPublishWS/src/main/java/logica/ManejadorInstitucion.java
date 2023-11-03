package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorInstitucion{
    private static ManejadorInstitucion instancia = null;
	//private List<InstitucionDeportiva> ins = new ArrayList<>();
	private ManejadorInstitucion(){}
	
	public static ManejadorInstitucion getInstancia() {
		if (instancia == null)
			instancia = new ManejadorInstitucion();
		return instancia;
	}
    public InstitucionDeportiva buscarInstitucionDeportiva(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		InstitucionDeportiva aretornar = em.find(InstitucionDeportiva.class, nombre);
		return aretornar;
	}
	
	public void addInstitucion(InstitucionDeportiva inst) {
		Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        
        em.persist(inst);

        em.getTransaction().commit();
        em.refresh(inst);
	}
	
	public ArrayList<String> obtenerInstitutos(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select i from InstitucionDeportiva i");
		List<InstitucionDeportiva> listIns = (List<InstitucionDeportiva>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(InstitucionDeportiva i: listIns) {
			aRetornar.add(i.getNombre());
		}
		return aRetornar;
	}
}