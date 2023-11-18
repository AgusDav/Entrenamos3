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

import java.io.IOException;

@WebServlet
public class RankingActividadServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RankingActividadServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String[] actividades;
        try {
            actividades = rankingActividad();
        } catch (Exception e) {
            throw new ServletException("Error al listar actividades: " + e.getMessage());
        }
        // Conviertes el arreglo de clases a JSON utilizando Gson
        Gson gson = new Gson();
        String clasesJson = gson.toJson(actividades);

        // Estableces el tipo de contenido de la respuesta como JSON
        response.setContentType("application/json");

        // Escribe la respuesta JSON al flujo de salida
        response.getWriter().write(clasesJson);

    }

    private String[] rankingActividad() throws Exception {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerRankingActividadesDeportivas();
    }
}
