package publicadores;

import configuraciones.WebServiceConfiguracion;
import datatypes.DtUsuario;
import excepciones.InstitucionDeportivaRepetidaException;
import interfaces.Fabrica;
import interfaces.IControlador;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

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
    public DtUsuario obtenerUsuario(String nick) {
        return obtenerUsuario(nick);
    }
    @WebMethod
    public boolean LogIn(String nick, String password){
        return (icon.logIn(nick,password));
    }
}