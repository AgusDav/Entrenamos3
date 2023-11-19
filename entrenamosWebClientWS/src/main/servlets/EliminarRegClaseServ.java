package main.servlets;

import com.google.gson.Gson;
import excepciones.RegistroAClaseRepetidoException;
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
import main.publicadores.DtClase;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/Entrenamos.uy/EliminarRegClase")
public class EliminarRegClaseServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EliminarRegClaseServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí obtienes la instancia de la fábrica y del controlador

        // Obtienes el parámetro de la institución desde la solicitud
        String institucionSeleccionada = request.getParameter("institucion");
        String actividadSeleccionada = request.getParameter("actividad_depor");
        String tipo = request.getParameter("tipo");

        if (actividadSeleccionada != null && !actividadSeleccionada.isEmpty()) {
            // Obtienes las actividades deportivas para la institución seleccionada
            String[] clases = new String[0];
            try {
                clases = listarClases(institucionSeleccionada, actividadSeleccionada);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            // Conviertes el array de cadenas a un formato JSON
            Gson gson = new Gson();
            String clasesJson = gson.toJson(clases);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(clasesJson);
        }
        if (tipo.equals("clases")) {
            String[] clases;
            try {
                clases = listarClases2();
            } catch (Exception e) {
                throw new ServletException("Error al listar clases: " + e.getMessage());
            }
            // Conviertes el arreglo de clases a JSON utilizando Gson
            Gson gson = new Gson();
            String clasesJson = gson.toJson(clases);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(clasesJson);
        } else if (tipo.equals("datos")) {
            String clase = request.getParameter("clase");
            Gson gson = new Gson();
            String[] respuestaArray = null;
            try {
                DtClase dt = obtenerClase(clase);
                Calendar calendar = dt.getFecha();

                // Agrega logs de depuración
                System.out.println("Fecha antes de la conversión: " + calendar.getTime());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    String fechaComoCadena = sdf.format(calendar.getTime());

                    // Agrega logs de depuración
                    System.out.println("Fecha después de la conversión: " + fechaComoCadena);

                    respuestaArray = new String[]{dt.getNombre(), dt.getUrl(), fechaComoCadena};
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServletException("Error al procesar la fecha: " + e.getMessage());
                }
            } catch (ParseException pe) {
                pe.printStackTrace();
                throw new ServletException("Error al obtener la clase: " + pe.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Error al procesar la solicitud: " + e.getMessage());
            }
            response.setContentType("application/json");

            // Conviertes el array de cadenas a formato JSON
            String respuestaJson = gson.toJson(respuestaArray);

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(respuestaJson);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clase = request.getParameter("clase");
        HttpSession session = request.getSession();
        String nick = (String) session.getAttribute("username");

        try {
            eliminarRegClase(nick, clase);
        } catch (Exception e) {
            request.setAttribute("error", "El usuario de nick " + nick + " no esta registrado en la clase " + clase);
            RequestDispatcher rd = request.getRequestDispatcher("/EliminarRegClase.jsp");
            rd.forward(request, response);
        }
        RequestDispatcher rd;
        request.setAttribute("mensaje", "Se ha eliminado correctamente el usuario " + nick + " de la clase " + clase);
        rd = request.getRequestDispatcher("/notificacion.jsp");
        rd.forward(request, response);
    }

    public void eliminarRegClase(String nick, String clase) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        port.eliminarRegistro(nick, clase);
    }
    public String[] listarClases(String ins, String actividad) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarClases(ins, actividad);
    }

    private String[] listarClases2() throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarClases2();
    }

    private DtClase obtenerClase(String clase) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerClase(clase);
    }
}


