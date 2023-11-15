package main.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
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

@WebServlet("/Entrenamos.uy/AgregarDictadoClase")
public class AgregarDictadoClaseServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AgregarDictadoClaseServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí obtienes la instancia de la fábrica y del controlador

        // Obtienes el parámetro de la institución desde la solicitud
        String institucionSeleccionada = request.getParameter("institucion");
        String tipo = request.getParameter("tipo");
        if ("actividades".equals(tipo)) {
            if (institucionSeleccionada != null && !institucionSeleccionada.isEmpty()) {
                // Obtienes las actividades deportivas para la institución seleccionada
                String[] actividades = new String[0];
                try {
                    actividades = listarActividadesDeportivas(institucionSeleccionada);
                } catch (Exception e) {
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
        }else if ("institutos".equals(tipo)){
            Gson gson = new Gson();
            String[] datos;
            try {
                datos = listarInstitutos();
                response.setContentType("application/json");
                // Conviertes el array de cadenas a formato JSON
                String respuestaJson = gson.toJson(datos);
                // Escribe la respuesta JSON al flujo de salida
                response.getWriter().write(respuestaJson);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String institucion = request.getParameter("institucion");
        String actividad_depor = request.getParameter("actividad_depor");
        String nombre = request.getParameter("nombre");
        String fecIni = request.getParameter("fecIni");
        String url = request.getParameter("url");
        String hora = request.getParameter("hora");


        if (institucion == null || actividad_depor == null || nombre.isEmpty() || request.getParameter("fecIni") == null || url.isEmpty() || hora == null){

            request.setAttribute("error", "No puede haber campos vacíos");
            RequestDispatcher rd = request.getRequestDispatcher("/AgregarDictadoClase.jsp");
            rd.forward(request, response);
        }else{
            // Para obtener el usuario de la sesión actual
            HttpSession session = request.getSession();
            String storedUsername = (String) session.getAttribute("username");

            // Para obtener la fecha del sistema
            Date fechaReg = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaInicio;
            try {
                fechaInicio = formato.parse(fecIni);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            try {
                altaDictadoClase(new DtClase(nombre, fechaInicio, hora, url, fechaReg), institucion, actividad_depor, storedUsername);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            RequestDispatcher rd;
            request.setAttribute("mensaje", "Se ha ingresado correctamente el dictado de clase de nombre: " + nombre + " en el sistema.");
            rd = request.getRequestDispatcher("/notificacion.jsp");
            rd.forward(request, response);
        }


    }
    private String[] listarInstitutos() throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarInstitutos();
    }
    private String[] listarActividadesDeportivas(String institucionSeleccionada) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarActividadesDeportivas(institucionSeleccionada);
    }

    private void altaDictadoClase(DtClase dtclase,String institucion,String actividad_depor,String storedUsername) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        port.altaDictadoClase(dtclase, institucion, actividad_depor, storedUsername);
    }
}