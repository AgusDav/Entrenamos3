package publicadores;

import configuraciones.WebServiceConfiguracion;
import datatypes.*;
import excepciones.ActividadDeportivaRepetidaException;
import excepciones.DictadoRepetidoException;
import excepciones.InstitucionDeportivaRepetidaException;
import excepciones.RegistroAClaseRepetidoException;
import interfaces.Fabrica;
import interfaces.IControlador;
import logica.ActividadDeportiva;
import logica.Clase;
import logica.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.Date;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorPublish {
    private Fabrica fabrica;
    private IControlador icon;
    private WebServiceConfiguracion configuracion;
    private Endpoint endpoint;

    public ControladorPublish() {
        fabrica = Fabrica.getInstancia();
        icon = fabrica.getIControlador();
        try {
            configuracion = new WebServiceConfiguracion();
        } catch (Exception ex) {

        }
    }

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controlador", this);
        System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controlador");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    //LOS MÉTODOS QUE VAMOS A PUBLICAR
    @WebMethod
    public void altaInstitucion(String nombre, String descripcion, String url) throws InstitucionDeportivaRepetidaException {
        icon.altaInstitucion(nombre,descripcion,url);
    }
    @WebMethod
    public void altaActividadDeportiva(String nombreIns,DtActividadDeportiva data) throws ActividadDeportivaRepetidaException {
        icon.altaActividadDeportiva(nombreIns,data);
    }
    @WebMethod
    public void altaDictadoClase(DtClase clase, String nomIns, String nomAct, String profe) throws DictadoRepetidoException {
        icon.altaDictadoClase(clase,nomIns,nomAct,profe);
    }
    @WebMethod
    public void registroADictadoClase(String nick,String clase,Date fechaReg)throws RegistroAClaseRepetidoException {
        icon.registroADictadoClase(nick,clase,fechaReg);
    }
    @WebMethod
    public String[] obtenerSociosClase(String nombreClase){
        return (icon.obtenerSociosClase(nombreClase));
    }
    @WebMethod
    public boolean LogIn(String nick, String password){
        return (icon.logIn(nick,password));
    }
    @WebMethod
    public String[] listarInstitutos(){
        return icon.listarInstitutos();
    }
    @WebMethod
    public String[] listarActividadesDeportivas(String nombre){
        return icon.listarActividadesDeportivas(nombre);
    }
    @WebMethod
    public String[] listarProfesores(String nombre){
        return icon.listarProfesores(nombre);
    }
    @WebMethod
    public String[] listarSocios(String nombre){
        return icon.listarSocios(nombre);
    }
    @WebMethod
    public String[] listarUsuarios(){
        return icon.listarUsuarios();
    }
    @WebMethod
    public DtUsuario obtenerUsuario(String nick){
        return  icon.obtenerUsuario(nick);
    }
    @WebMethod
    public DtProfesor obtenerProfesor(String nick){
        return icon.obtenerProfesor(nick);
    }
    @WebMethod
    public DtSocio obtenerSocio(String nick){
        return icon.obtenerSocio(nick);
    }
    @WebMethod
    public Usuario obtenerUsuarioReal(String nick){
        return icon.obtenerUsuarioReal(nick);
    }
    @WebMethod
    public DtActividadDeportiva obtenerActividad(String instituto, String actividad){
        return icon.obtenerActividad(instituto, actividad);
    }
    @WebMethod
    public ActividadDeportiva obtenerActividadR(String instituto, String actividad){
        return icon.obtenerActividadR(instituto, actividad);
    }
    @WebMethod
    public String[] listarClases(String instituto, String actividad){
        return icon.listarClases(instituto, actividad);
    }
    @WebMethod
    public String[] listarClases2(){    // lista la coleccion de clases sin importar instituto ni actDep
        return icon.listarClases2();
    }
    @WebMethod
    public DtClase obtenerClase(String nombreClase){
        return icon.obtenerClase(nombreClase);
    }
    @WebMethod
    public Clase obtenerClaseR(String nombreClase){
        return icon.obtenerClaseR(nombreClase);
    }
    @WebMethod
    public String obtenerInstitucionActividad(String nombreAct){
        return icon.obtenerInstitucionActividad(nombreAct);
    }
    @WebMethod
    public void ModificarUsuario(String nick, String nombre, String apellido, Date fecNac, String Descripcion , String Biografia , String Sitio ){
        icon.ModificarUsuario(nick, nombre, apellido, fecNac, Descripcion, Biografia, Sitio);
    }
    @WebMethod
    public void ModificarActividadDeportiva(String actividad, String descripcion, int duracion, float costo){
        icon.ModificarActividadDeportiva(actividad, descripcion, duracion, costo);
    }
    @WebMethod
    public String[] obtenerTodasActividadesDeportivas(){
        return icon.obtenerTodasActividadesDeportivas();
    }
    @WebMethod
    public DtInstitucionDeportiva obtenerInstitucion(String nombre){
        return icon.obtenerInstitucion(nombre);
    }
    @WebMethod
    public void ModificarInstitucion(String nombre,String desc,String url){
        icon.ModificarInstitucion(nombre, desc, url);
    }
    @WebMethod
    public String[] obtenerRankingActividadesDeportivas(){
        return icon.obtenerRankingActividadesDeportivas();
    }
    @WebMethod
    public Boolean esSocio(String nick){
        return icon.esSocio(nick);
    }
    @WebMethod
    public String[] usuarioEnClase(String nick){
        return icon.usuarioEnClase(nick);
    }
    @WebMethod
    public String[] clasesProfe(String var1){
        return icon.clasesProfe(var1);
    }
}