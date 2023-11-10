package main.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import main.publicadores.ControladorPublish;
import main.publicadores.ControladorPublishService;
import main.publicadores.ControladorPublishServiceLocator;
import main.publicadores.DtSocio;
import main.publicadores.DtProfesor;

@WebServlet("/Entrenamos.uy/ModificarUsuario")
public class ModificarUsuarioServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModificarUsuarioServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nuevoNombre");
        String apellido = request.getParameter("nuevoApellido");
        String fecha = request.getParameter("nuevaFecNac");
        HttpSession session = request.getSession();
        String nick = (String) session.getAttribute("username");
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date fecNacDate;
        Calendar calendario = Calendar.getInstance();
        try {
            fecNacDate = sdf.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        calendario.setTime(fecNacDate);
        try {
            if(esSocio(nick)) {
                modificarUsuario(nick,nombre,apellido,calendario,"","","");
            }
            else{
                DtProfesor DtP = obtenerProfesor(nick);
                modificarUsuario(nick,nombre,apellido,calendario,DtP.getDescripcion(), DtP.getBiografia(),DtP.getSitioWeb());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher rd;
        request.setAttribute("mensaje", "Se ha modificado correctamente el usuario " + nick);
        rd = request.getRequestDispatcher("/notificacion.jsp");
        rd.forward(request, response);

    }

    private DtProfesor obtenerProfesor(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerProfesor(nick);
    }
    private DtSocio obtenerSocio(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerSocio(nick);
    }

    private boolean esSocio(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.esSocio(nick);
    }

    private void modificarUsuario(String nick, String nombre, String apellido, Calendar fecNac , String Descripcion , String Biografia , String Sitio ) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        port.modificarUsuario(nick,nombre,apellido,fecNac,Descripcion,Biografia,Sitio);
    }
}