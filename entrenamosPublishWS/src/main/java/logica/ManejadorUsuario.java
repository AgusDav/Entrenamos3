package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;

	private ManejadorUsuario(){}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}
	
	public void addUser(Usuario user) {
		Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
        em.refresh(user);
	}

	public Usuario buscarUsuario(String nick){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Usuario aretornar = em.find(Usuario.class, nick);
		return aretornar;
	}
	
	public ArrayList<String> obtenerUsuariosNick(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select u from Usuario u");
		List<Usuario> listUs = (List<Usuario>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Usuario i: listUs) {
			aRetornar.add(i.getNickname());
		}
		return aRetornar;
    }
	
	public String[] obtenerSocios() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select u from Usuario u where tipousuario = 'S'");
		List<Usuario> listUs = (List<Usuario>) query.getResultList();
		
		String[] aRetornar = new String[listUs.size()];
		int i = 0;
		for(Usuario u: listUs) {
			aRetornar[i] = u.getNickname();
			i++;
		}
		return aRetornar;
	}
	
	public Boolean SocioEnClase(String Socio, String Clase) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("select r from Registro r");
		List<Registro> listReg = (List<Registro>) query.getResultList();
		for(Registro r:listReg) {
			if(r.getClase().getNombre().equals(Clase) && r.getSocio().getNickname().equals(Socio)) {
				return true;
			}
		}
		return false;
	}
	
	/*public ArrayList<String> obtenerClasesSocio(String socio){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Query query = em.createQuery("select c from Clase c join Usuario on ");
	}*/
}
