package interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.*;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.InstitucionDeportiva;
import logica.Usuario;
import excepciones.InstitucionDeportivaRepetidaException;
import excepciones.ActividadDeportivaRepetidaException;
import excepciones.DictadoRepetidoException;
import excepciones.UsuarioRepetidoException;
import excepciones.RegistroAClaseRepetidoException;

//import excepciones.SocioRepetidoExcepcion;

public interface IControlador {
	//public void agregarUsuario(String nickname, String nombre, String apellido, String email, LocalDate fecNac) throws UsuarioRepetidoExcepcion;
	public void altaInstitucion(String nombre,String descripcion, String url) throws InstitucionDeportivaRepetidaException;
	public void altaActividadDeportiva(String nombreIns,DtActividadDeportiva data) throws ActividadDeportivaRepetidaException;
	public void agregarUsuario(DtUsuario user, String inst) throws UsuarioRepetidoException;
	public void agregarProfesor(DtProfesor profe, String ins) throws UsuarioRepetidoException;
	public void agregarSocio(DtSocio socio) throws UsuarioRepetidoException;
	public void altaDictadoClase(DtClase clase, String nomIns, String nomAct, String profe) throws DictadoRepetidoException;
	public void registroADictadoClase(String nick,String clase,Date fechaReg) throws RegistroAClaseRepetidoException;

	
	public String[] listarInstitutos();
	public String[] listarActividadesDeportivas(String nombre);
	public String[] listarProfesores(String nombre);
	public String[] listarSocios(String nombre);
	public String[] listarUsuarios();
	public DtUsuario obtenerUsuario(String nick);
	public Usuario obtenerUsuarioReal(String nick);
	public DtActividadDeportiva obtenerActividad(String instituto, String actividad);
	public ActividadDeportiva obtenerActividadR(String instituto, String actividad);
	public List<Clase> obtenerClases(String instituto, String actividad);
	public String[] listarClases(String instituto, String actividad);
	public String[] listarClases2();	// lista la coleccion de clases sin importar instituto ni actDep
	public DtClase obtenerClase(String nombreClase);
	public Clase obtenerClaseR(String nombreClase);
	public String obtenerInstitucionActividad(String nombreAct);
	public void ModificarUsuario(String nick, String nombre, String apellido, Date fecNac, String Descripcion , String Biografia , String Sitio );
	public void ModificarActividadDeportiva(String actividad, String descripcion, int duracion, float costo);
	public String[] obtenerTodasActividadesDeportivas();
	public DtInstitucionDeportiva obtenerInstitucion(String nombre);
	public void ModificarInstitucion(String nombre,String desc,String url);
	public String[] obtenerRankingActividadesDeportivas();
	public Boolean logIn(String nick,String password);
}
