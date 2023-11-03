package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorClase{
    private static ManejadorClase instancia = null;
	private List<Clase> clase = new ArrayList<>();
	private ManejadorClase(){}
	
	public static ManejadorClase getInstancia() {
		if (instancia == null)
			instancia = new ManejadorClase();
		return instancia;
	}
	
	public Clase buscarClase(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Clase aretornar = em.find(Clase.class, nombre);
		return aretornar;
	}
	
	public void addClase(Clase clas) {
		Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();

        em.persist(clas);
        
        em.getTransaction().commit();
        em.refresh(clas);        
        //refresca profesor para ver nueva intancia en su lista de clases en la misma aplicacion
        em.refresh(clas.getProfe());
	}
	
	public ArrayList<String> obtenerClase(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select c from Clase c");
		List<Clase> listC = (List<Clase>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Clase i: listC) {
			aRetornar.add(i.getNombre());
		}
		return aRetornar;
	}
	
	public ArrayList<String> obtenerClasesRanking() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select c from Clase c");
		List<Clase> listClas = (List<Clase>) query.getResultList();
		
		Collections.sort(listClas, new Comparator<Clase>() {
	        @Override
	        public int compare(Clase clase1, Clase clase2) {
	            return Integer.compare(clase2.getCantidadInscriptos(), clase1.getCantidadInscriptos());
	        }
	    });
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Clase i: listClas) {
			aRetornar.add(i.getNombre());
		}
		return aRetornar;
	}

}
	

	