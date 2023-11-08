package main.servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;

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

import javax.xml.rpc.ServiceException;

@WebServlet("/Entrenamos.uy/HeaderServ")
public class headerServ {
    private static final long serialVersionUID = 1L;

    public headerServ() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String tipoUser;

        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("username");
        if(esSocio(nickname)){
            tipoUser = "S";
        }
        else{
            tipoUser = "P";
        }
        request.setAttribute("tipoUser", tipoUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/header.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public boolean esSocio(String nick) throws ServiceException, RemoteException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return port.esSocio(nick);
    }
}
