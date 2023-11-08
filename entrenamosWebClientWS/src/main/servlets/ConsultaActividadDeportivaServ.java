package main.servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import main.publicadores.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import main.publicadores.ControladorPublish;
import main.publicadores.ControladorPublishService;
import main.publicadores.ControladorPublishServiceLocator;

import javax.xml.rpc.ServiceException;

@WebServlet("/Entrenamos.uy/ConsultaActividadDeportiva")
public class ConsultaActividadDeportivaServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaActividadDeportivaServ() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí obtienes la instancia de la fábrica y del controlado

        // Obtienes el parámetro de la actividad desde la solicitud
        String actividadSeleccionada = request.getParameter("actividad");
        // Obtienes el parámetro "tipo" desde la solicitud
        String tipo = request.getParameter("tipo");

        if (actividadSeleccionada != null && !actividadSeleccionada.isEmpty()) {
            if ("dt".equals(tipo)) {
                // Obtienes el DtActividadDeportiva
                String ins = null;
                try {
                    ins = obtenerInstitucionActividad(actividadSeleccionada);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                DtActividadDeportiva dt = null;
                try {
                    dt = obtenerActividad(ins, actividadSeleccionada);
                } catch (ServiceException e) {
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
            else if ("clases".equals(tipo)){
                // Obtienes el arreglo de clases correspondientes a la actividad
                String ins = null;
                try {
                    ins = obtenerInstitucionActividad(actividadSeleccionada);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                String[] clases  = new String[0];
                try {
                    clases = listarClases(ins,actividadSeleccionada);
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
            else if ("nomIns".equals(tipo)) {
                // Obtienes la institución correspondiente a la actividad
                String ins = null;
                try {
                    ins = obtenerInstitucionActividad(actividadSeleccionada);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }

                // Estableces el tipo de contenido de la respuesta como JSON
                response.setContentType("application/json");

                // Escribe la respuesta JSON al flujo de salida
                response.getWriter().write(ins);
            }
        }
    }

    public String obtenerInstitucionActividad(String actividad) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerInstitucionActividad(actividad);
    }

    public DtActividadDeportiva obtenerActividad(String ins, String actividad) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.obtenerActividad(ins, actividad);
    }

    public String[] listarClases(String ins, String actividad) throws RemoteException, ServiceException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.listarClases(ins, actividad);
    }


}