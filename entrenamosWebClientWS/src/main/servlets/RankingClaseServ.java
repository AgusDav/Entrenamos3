package main.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.publicadores.ControladorPublish;
import main.publicadores.ControladorPublishService;
import main.publicadores.ControladorPublishServiceLocator;
import main.publicadores.DtClase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/Entrenamos.uy/RankingClase")
public class RankingClaseServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RankingClaseServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        if (tipo.equals("clases")) {
            String[] clases;
            try {
                clases = listarClases();
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

    private String[] listarClases() throws Exception {
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
