package main.servlets;

import com.google.gson.Gson;
import interfaces.Fabrica;
import interfaces.IControlador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.publicadores.ControladorPublish;
import main.publicadores.ControladorPublishService;
import main.publicadores.ControladorPublishServiceLocator;
import main.publicadores.DtProfesor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/Entrenamos.uy/ConsultaUsuario")
public class ConsultaUsuarioServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaUsuarioServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();
        String[] clases;
        String nick = request.getParameter("user");
        String tipo = request.getParameter("tipo");

        if ("usuario".equals(tipo)) {
            Gson gson = new Gson();
            String[] respuestaArray = null;
            try {
                DtProfesor profesor = obtenerProfesor(nick);
                Calendar calendar = profesor.getFecNac();
// Convierte el objeto Calendar a una cadena con un formato específico
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Define el formato de fecha deseado
                String fechaComoCadena = sdf.format(calendar.getTime()); // Convierte el Calendar a Date y luego a cadena
                respuestaArray = new String[]{profesor.getNickname(), profesor.getEmail(), profesor.getNombre(), profesor.getApellido(), fechaComoCadena};
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
            if (icon.esSocio(nick)) {
                clases = icon.usuarioEnClase(nick);
            } else {
                clases = icon.clasesProfe(nick);
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
            if (icon.esSocio(nick)) {
                clases = icon.usuarioEnClase(nick);
            } else {
                clases = icon.clasesProfe(nick);
            }
            // Utilizar un conjunto para mantener un registro de los valores únicos
            Set<String> actividadesSet = new HashSet<>();

            for (int i = 0; i < clases.length; ++i) {
                // Obtener el nombre de la actividad de la clase
                String actividadNombre = icon.obtenerClaseR(clases[i]).getActividad().getNombre();

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
}
