package main.servlets;

import java.io.IOException;
import java.rmi.RemoteException;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.publicadores.DtClase;
import main.publicadores.ControladorPublish;
import main.publicadores.ControladorPublishService;
import main.publicadores.ControladorPublishServiceLocator;

import javax.xml.rpc.ServiceException;


@WebServlet("/Entrenamos.uy/ConsultaDictadoClase")
public class ConsultaDictadoClaseServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaDictadoClaseServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtienes el parámetro de la institución desde la solicitud
        String institucionSeleccionada = request.getParameter("institucion");
        String tipo = request.getParameter("tipo");

        if (institucionSeleccionada != null && !institucionSeleccionada.isEmpty()) {
            if ("inst".equals(tipo)) {
                // Obtienes las actividades deportivas para la institución seleccionada
                String[] actividades = new String[0];
                try {
                    actividades = listarActividadesDeportivas(institucionSeleccionada);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }

                // Conviertes el array de cadenas a un formato JSON
                Gson gson = new Gson();
                String actividadesJson = gson.toJson(actividades);

                // Estableces el tipo de contenido de la respuesta como JSON
                response.setContentType("application/json");

                // Escribe la respuesta JSON al flujo de salida
                response.getWriter().write(actividadesJson);

            }
            else if ("act".equals(tipo)) {
                String actividadSeleccionada = request.getParameter("actividad");
                String[] clases = new String[0];
                try {
                    clases = listarClases(institucionSeleccionada, actividadSeleccionada);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }

                // Conviertes el arreglo de clases a JSON utilizando Gson
                Gson gson = new Gson();
                String clasesJson = gson.toJson(clases);

                // Estableces el tipo de contenido de la respuesta como JSON
                response.setContentType("application/json");

                // Escribe la respuesta JSON al flujo de salida
                response.getWriter().write(clasesJson);
            }
        }
        if ("dtclase".equals(tipo)) {
            // Obtienes el DtActividadDeportiva
            String claseSeleccionada = request.getParameter("clase");
            DtClase dt = null;
            try {
                dt = obtenerClase(claseSeleccionada);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Conviertes el objeto DtActividadDeportiva a JSON
            Gson gson = new Gson();
            String actividadJson = gson.toJson(dt);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(actividadJson);
        }
        else if ("dtsocio".equals(tipo)) {
            // Obtienes el DtActividadDeportiva
            String claseSeleccionada = request.getParameter("clase");
            String[] dt = new String[0];
            try {
                dt = obtenerSociosClase(claseSeleccionada);//CORREGIR ACA
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Conviertes el objeto DtActividadDeportiva a JSON
            Gson gson = new Gson();
            String actividadJson = gson.toJson(dt);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(actividadJson);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
    public String[] listarActividadesDeportivas(String institucionSeleccionada) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarActividadesDeportivas(institucionSeleccionada);
    }
    public String[] listarClases(String ins, String actividad) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarClases(ins, actividad);
    }
    private DtClase obtenerClase(String actividad) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerClase(actividad);
    }
    private String[] obtenerSociosClase(String actividad) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerSociosClase(actividad);
    }
}