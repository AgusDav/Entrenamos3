package main.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.publicadores.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.HashSet;
import java.util.Set;

@WebServlet("/Entrenamos.uy/ConsultaUsuario")
public class ConsultaUsuarioServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaUsuarioServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] clases;
        String nick = request.getParameter("user");
        String tipo = request.getParameter("tipo");

        if ("socio".equals(tipo)) {
            Gson gson = new Gson();
            try {
                boolean esSocio = esSocio(nick);
                response.setContentType("application/json");
                // Conviertes el array de cadenas a formato JSON
                String respuestaJson = gson.toJson(esSocio);
                // Escribe la respuesta JSON al flujo de salida
                response.getWriter().write(respuestaJson);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if ("usuario".equals(tipo)) {
            Gson gson = new Gson();
            String[] respuestaArray = null;
            try {
                if(esSocio(nick)){
                    DtSocio profesor = obtenerSocio(nick);
                    Calendar calendar = profesor.getFecNac();
// Convierte el objeto Calendar a una cadena con un formato específico
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Define el formato de fecha deseado
                    String fechaComoCadena = sdf.format(calendar.getTime()); // Convierte el Calendar a Date y luego a cadena
                    respuestaArray = new String[]{profesor.getNickname(), profesor.getEmail(), profesor.getNombre(), profesor.getApellido(), fechaComoCadena};
                }
                else{
                    DtProfesor profesor = obtenerProfesor(nick);
                    Calendar calendar = profesor.getFecNac();
// Convierte el objeto Calendar a una cadena con un formato específico
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Define el formato de fecha deseado
                    String fechaComoCadena = sdf.format(calendar.getTime()); // Convierte el Calendar a Date y luego a cadena
                    respuestaArray = new String[]{profesor.getNickname(), profesor.getEmail(), profesor.getNombre(), profesor.getApellido(), fechaComoCadena};
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Conviertes el array de cadenas a formato JSON
            String respuestaJson = gson.toJson(respuestaArray);

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(respuestaJson);
            return;
        }
        if ("clases".equals(tipo)) {
            try {
                if (esSocio(nick)) {
                    clases = usuarioEnClase(nick);
                } else {
                    clases = clasesProfe(nick);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Gson gson = new Gson();
            String clasesJson = gson.toJson(clases);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(clasesJson);
            return;
        }
        if("actividad".equals(tipo)){
            try {
                if (esSocio(nick)) {
                    try {
                        clases = usuarioEnClase(nick);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        clases = clasesProfe(nick);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Utilizar un conjunto para mantener un registro de los valores únicos
            Set<String> actividadesSet = new HashSet<>();

            for (int i = 0; i < clases.length; ++i) {
                // Obtener el nombre de la actividad de la clase
                String actividadNombre = null;
                try {
                    actividadNombre = obtenerClaseR(clases[i]).getActividad().getNombre();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // Agregar el nombre de la actividad al conjunto
                actividadesSet.add(actividadNombre);
            }
            // Convertir el conjunto de actividades nuevamente a un arreglo
            clases = actividadesSet.toArray(new String[0]);
            // Conviertes el array de cadenas a un formato JSON
            Gson gson = new Gson();
            String clasesJson = gson.toJson(clases);

            // Estableces el tipo de contenido de la respuesta como JSON
            response.setContentType("application/json");

            // Escribe la respuesta JSON al flujo de salida
            response.getWriter().write(clasesJson);
        }
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
    private String[] usuarioEnClase(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.usuarioEnClase(nick);
    }
    private String[] clasesProfe(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.clasesProfe(nick);
    }
    private boolean esSocio(String nick) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.esSocio(nick);
    }
    private Clase obtenerClaseR(String actividad) throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerClaseR(actividad);
    }
}