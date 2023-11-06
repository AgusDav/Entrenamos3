package logica;

import excepciones.InstitucionDeportivaRepetidaException;
import excepciones.UsuarioRepetidoException;
import excepciones.ActividadDeportivaRepetidaException;
import excepciones.DictadoRepetidoException;
import excepciones.RegistroAClaseRepetidoException;
import interfaces.IControlador;
import persistencia.Conexion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtInstitucionDeportiva;
import datatypes.DtUsuario;
import datatypes.DtProfesor;
import datatypes.DtSocio;

//import interfaces.UsuarioRepetidoExcepcion;

public class Controlador implements IControlador{
	public Controlador() {
		super();
	}
	
	@Override
	public void altaInstitucion(String nombre, String descripcion, String url) throws InstitucionDeportivaRepetidaException{
		ManejadorInstitucion mI= ManejadorInstitucion.getInstancia(); 
		InstitucionDeportiva i=mI.buscarInstitucionDeportiva(nombre);
		if(i!=null)
			throw new InstitucionDeportivaRepetidaException("El Instituto " + nombre + " ya existe");
		else {
			i=new InstitucionDeportiva(nombre,descripcion,url);
			mI.addInstitucion(i);
		}
	}

	@Override
	public void altaActividadDeportiva(String nombreIns,DtActividadDeportiva data) throws ActividadDeportivaRepetidaException{
		ManejadorInstitucion mI= ManejadorInstitucion.getInstancia(); 
		InstitucionDeportiva i=mI.buscarInstitucionDeportiva(nombreIns);
		
		if(i.buscarActividad2(data.getNombre())) {
			throw new ActividadDeportivaRepetidaException("La actividad deportiva " + data.getNombre() + " ya existe");
		}
		else {
			i.agregarActividadDeportiva(data);
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
			em.persist(i);
			em.getTransaction().commit();
			em.refresh(i);
		}
	}

	@Override
	public void agregarUsuario(DtUsuario user, String ins) throws UsuarioRepetidoException{
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUser = mU.buscarUsuario(user.getNickname());
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva inst = mI.buscarInstitucionDeportiva(ins);
		if (nuevoUser != null)
			throw new UsuarioRepetidoException("El usuario de nick "+ nuevoUser.getNickname() + " ya existe en el Sistema");
		if (user instanceof DtProfesor) {
			//Date fecha = Date.from(user.getFecNac().atStartOfDay(ZoneId.systemDefault()).toInstant());
			nuevoUser = new Profesor(user.getNickname(),user.getNombre(),user.getApellido(),user.getEmail(),user.getPassword(),user.getFecNac(),((DtProfesor) user).getDescripcion(),((DtProfesor) user).getBiografia(),((DtProfesor) user).getSitioWeb(), inst); 
			inst.agregarProfesor((Profesor) nuevoUser);
		}
		if (user instanceof DtSocio) {
			//Date fecha = Date.from(user.getFecNac().atStartOfDay(ZoneId.systemDefault()).toInstant());
			nuevoUser = new Socio(user.getNickname(),user.getNombre(),user.getApellido(),user.getEmail(),user.getPassword(),user.getFecNac());
		}
		mU.addUser(nuevoUser);
	}
	@Override
	public void agregarProfesor(DtProfesor profe, String ins) throws UsuarioRepetidoException {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUser = mU.buscarUsuario(profe.getNickname());
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva inst = mI.buscarInstitucionDeportiva(ins);
		if (nuevoUser != null){
			throw new UsuarioRepetidoException("El usuario de nick "+ nuevoUser.getNickname() + " ya existe en el Sistema");
		}
		nuevoUser = new Profesor(profe.getNickname(),profe.getNombre(),profe.getApellido(),profe.getEmail(),profe.getPassword(),profe.getFecNac(), profe.getDescripcion(),profe.getBiografia(),profe.getSitioWeb(), inst);
		inst.agregarProfesor((Profesor) nuevoUser);
		mU.addUser(nuevoUser);
	}
	@Override
	public void agregarSocio(DtSocio socio) throws UsuarioRepetidoException {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUser = mU.buscarUsuario(socio.getNickname());
		if (nuevoUser != null){
			throw new UsuarioRepetidoException("El usuario de nick "+ nuevoUser.getNickname() + " ya existe en el Sistema");
		}
		nuevoUser = new Socio(socio.getNickname(),socio.getNombre(),socio.getApellido(),socio.getEmail(),socio.getPassword(),socio.getFecNac());
		mU.addUser(nuevoUser);
	}

	@Override
	public void ModificarUsuario(String nick, String nombre, String apellido, Date fecNac , String Descripcion , String Biografia , String Sitio ) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario User = mU.buscarUsuario(nick);
		User.setNombre(nombre);
		User.setApellido(apellido);
		User.setFecNac(fecNac);
		if(User instanceof Profesor) {
			((Profesor) User).setBiografia(Biografia);
			((Profesor) User).setDescripcion(Descripcion);
			((Profesor) User).setSitioWeb(Sitio);
		}
		em.getTransaction().begin();
		em.persist(User);
		em.getTransaction().commit();
		em.refresh(User);
	}
	@Override
	public Boolean logIn(String nick,String password) {
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario User = mU.buscarUsuario(nick);
		if(User != null) {
			if(User.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void ModificarActividadDeportiva(String actividad, String descripcion, int duracion, float costo) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva Ins = mI.buscarInstitucionDeportiva(obtenerInstitucionActividad(actividad));
		ActividadDeportiva Act = Ins.buscarActividad(actividad);
		Act.setDescripcion(descripcion);
		Act.setDuracion(duracion);
		Act.setCosto(costo);
		em.getTransaction().begin();
		em.persist(Ins);
		em.getTransaction().commit();
		em.refresh(Ins);
	}
	
	@Override
	public void ModificarInstitucion(String nombre,String desc,String url) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva Ins = mI.buscarInstitucionDeportiva(nombre);
		Ins.setDescripcion(desc);
		Ins.setUrl(url);
		em.getTransaction().begin();
		em.persist(Ins);
		em.getTransaction().commit();
		em.refresh(Ins);
	}
	
	
	@Override
	public String[] obtenerTodasActividadesDeportivas(){
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    
	    Query query = em.createQuery("select a.nombre from ActividadDeportiva a");
	    List<String> nombres = (List<String>) query.getResultList();
	    
	    // Convertir la lista de nombres a un arreglo de cadenas
	    String[] nombresArray = nombres.toArray(new String[0]);
	    
	    // Devolver el arreglo de nombres de actividades deportivas
	    return nombresArray;
	}
	
	@Override
	public String[] obtenerRankingActividadesDeportivas(){
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    
	    Query query = em.createQuery("select a2.nombre from Clase a1 right join ActividadDeportiva a2 on a1.actividad.nombre=a2.nombre group by a2.nombre order by count(a1.nombre) desc");
	    List<String> nombres = (List<String>) query.getResultList();
	    
	    // Convertir la lista de nombres a un arreglo de cadenas
	    String[] nombresArray = nombres.toArray(new String[0]);
	    
	    // Devolver el arreglo de nombres de actividades deportivas
	    return nombresArray;
	}
	
	
	
	@Override
	public void altaDictadoClase(DtClase clase, String nomIns, String nomAct, String profe) throws DictadoRepetidoException{
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva aux;
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Profesor profesor = (Profesor) mU.buscarUsuario(profe);
		aux = mI.buscarInstitucionDeportiva(nomIns);
		ActividadDeportiva actD = aux.buscarActividad(nomAct);
		ManejadorClase mC = ManejadorClase.getInstancia();
		Clase cl = (Clase) mC.buscarClase(clase.getNombre());
		
		if(cl!=null) {
			throw new DictadoRepetidoException("La clase " + clase.getNombre() + " ya existe");
		}
		else {
			actD.agregarClase(clase, profesor);
			profesor.añadirClase(cl);
			mC.addClase(actD.obtenerClase(clase.getNombre()));
			
		}
	}
	
	@Override
	public String[] listarInstitutos() {
		ArrayList<String> instituto;
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		instituto = mI.obtenerInstitutos();
		String[] institutos_ret = new String[instituto.size()];
        int i=0;
        for(String ins:instituto) {
        	institutos_ret[i]=ins;
        	i++;
        }
        return institutos_ret;
	}
	
	@Override
	public String[] listarUsuarios() {
		ArrayList<String> users;
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		users = mU.obtenerUsuariosNick();
		String[] ret = new String[users.size()];
		int i = 0;
		for(String ins:users) {
			ret[i] = ins;
			i++;
		}
		return ret;
	}
	
	@Override
    public String[] listarActividadesDeportivas(String nombre) {
        ArrayList<String> insActD;
        ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
        insActD = mI.buscarInstitucionDeportiva(nombre).obtenerActividades();
        String[] actIns_ret = new String[insActD.size()];
        int i=0;
        for(String ins:insActD) {
            actIns_ret[i]=ins;
            i++;
        }
        return actIns_ret;
    }
	
	@Override
    public String[] listarProfesores(String nombre) {
        ArrayList<String> insActD;
        ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
        insActD = mI.buscarInstitucionDeportiva(nombre).obtenerProfesores();
        String[] actIns_ret = new String[insActD.size()];
        int i=0;
        for(String ins:insActD) {
            actIns_ret[i]=ins;
            i++;
        }
        return actIns_ret;
    }

	@Override
    public String[] listarSocios(String nombre) {
        String[] soc;
        ManejadorUsuario mU = ManejadorUsuario.getInstancia();
       	soc = mU.obtenerSocios();
        return soc;
    }
	
	@Override
	public DtUsuario obtenerUsuario(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(nick);
		DtUsuario dtU = u.getDtUsuario();
		return dtU;
	}
	@Override
	public DtProfesor obtenerProfesor(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(nick);
		DtProfesor dtU = (DtProfesor) u.getDtUsuario();
		return dtU;
	}
	
	@Override
	public DtInstitucionDeportiva obtenerInstitucion(String nombre) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva iD = mI.buscarInstitucionDeportiva(nombre);
		DtInstitucionDeportiva dtI  = iD.getDtInstitucion();
		return dtI;
	}
	
	@Override
	public Usuario obtenerUsuarioReal(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(nick);
		return u;
	}
	
	@Override
	public DtActividadDeportiva obtenerActividad(String instituto, String actividad) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva I = mI.buscarInstitucionDeportiva(instituto);
		ActividadDeportiva a = I.buscarActividad(actividad);
		DtActividadDeportiva dtA = a.getDtActividadDeportiva();
		return dtA;
	}
	
	@Override
	public ActividadDeportiva obtenerActividadR(String instituto, String actividad) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva I = mI.buscarInstitucionDeportiva(instituto);
		ActividadDeportiva a = I.buscarActividad(actividad);
		return a;
	}
	
	@Override
	public List<Clase> obtenerClases(String instituto, String actividad) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva I = mI.buscarInstitucionDeportiva(instituto);
		ActividadDeportiva a = I.buscarActividad(actividad);
		return a.getClases();
	}
	
	@Override
	public String[] listarClases(String instituto, String actividad) {
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		InstitucionDeportiva I = mI.buscarInstitucionDeportiva(instituto);
		ActividadDeportiva a = I.buscarActividad(actividad);
		List<Clase> c = a.getClases();
		String[] ret = new String[c.size()];
        int i=0;
        for(Clase aux:c) {
            ret[i]=aux.getNombre();
            i++;
        }
        return ret;
	}
	
	@Override
	public String[] listarClases2(){
		List<String> clases;
		ManejadorClase mC = ManejadorClase.getInstancia();
		clases = mC.obtenerClasesRanking();
		String[] ret = new String[clases.size()];
		int i = 0;
		for(String ins:clases){
			ret[i] = ins;
			i++;
		}
		return ret;
	}

	@Override
	public void registroADictadoClase(String nick,String clase,Date fechaReg)throws RegistroAClaseRepetidoException{
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario us = mU.buscarUsuario(nick);
		ManejadorClase mC = ManejadorClase.getInstancia();
		Clase clas = mC.buscarClase(clase);
		if(mU.SocioEnClase(nick, clase)){
			throw new RegistroAClaseRepetidoException("El registro del socio " + nick + " ya existe");
		}
		else{
			Registro reg = new Registro ((Socio)us,clas,fechaReg);
			clas.añadirRegistro(reg);
			us.añadirRegistro(reg);
			Conexion conexion = Conexion.getInstancia();
			EntityManager em = conexion.getEntityManager();
			em.getTransaction().begin();
			em.persist(clas);
			em.getTransaction().commit();
		}
	}
	
	@Override
	public DtClase obtenerClase(String nombreClase){
		ManejadorClase mC = ManejadorClase.getInstancia();
		Clase c = mC.buscarClase(nombreClase);
		DtClase dtC = c.getDtClase();
		return dtC;
	}
	
	@Override
	public Clase obtenerClaseR(String nombreClase){
		ManejadorClase mC = ManejadorClase.getInstancia();
		Clase c = mC.buscarClase(nombreClase);
		return c;
	}
	
	@Override
	public String obtenerInstitucionActividad(String nombreAct){
		ManejadorInstitucion mI = ManejadorInstitucion.getInstancia();
		ArrayList<String> inst = mI.obtenerInstitutos();
		String ret = null;
		for (String ins : inst) {
	        InstitucionDeportiva institucion = mI.buscarInstitucionDeportiva(ins);
	        ActividadDeportiva actividad = institucion.buscarActividad(nombreAct);

	        if (actividad != null && actividad.getNombre() != null) {
	        	if(actividad.getNombre().equals(nombreAct)) {
	        		ret = ins;
	        		break; // Terminar el bucle si se encuentra una coincidencia
	        	}
	        }
	    }
		return ret;
	}
	public Boolean esSocio(String nick) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario user = mU.buscarUsuario(nick);
		return user instanceof Profesor ? false : true;
	}

	public String[] usuarioEnClase(String nick) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Query query = em.createQuery("select r from Registro r where socio.nickname = :nick").setParameter("nick", nick);
		List<Registro> listCla = query.getResultList();
		String[] aRetornar = new String[listCla.size()];
		int i = 0;

		for(Iterator var8 = listCla.iterator(); var8.hasNext(); ++i) {
			Registro r = (Registro)var8.next();
			aRetornar[i] = r.getClase().getNombre();
		}

		return aRetornar;
	}

	public String[] clasesProfe(String nick) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Query query = em.createQuery("select c from Clase c where profe.nickname = :nick").setParameter("nick", nick);
		List<Clase> listCla = query.getResultList();
		String[] aRetornar = new String[listCla.size()];
		int i = 0;

		for(Iterator var8 = listCla.iterator(); var8.hasNext(); ++i) {
			Clase c = (Clase)var8.next();
			aRetornar[i] = c.getNombre();
		}

		return aRetornar;
	}



}
