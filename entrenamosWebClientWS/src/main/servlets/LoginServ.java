package main.servlets;

import java.io.IOException;
import java.rmi.RemoteException;

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

@WebServlet("/Entrenamos.uy/Login")
public class LoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        try {
            if(logIn(nickname, password)){
                HttpSession session = request.getSession();
                session.setAttribute("username", nickname);

                RequestDispatcher rd;
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("error", "Nickname o contrasenia incorrectos");
                RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
                rd.forward(request, response);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }


    public boolean logIn(String nick, String pass) throws ServiceException, RemoteException {
        ControladorPublishService cps = new ControladorPublishServiceLocator();
        ControladorPublish port = cps.getControladorPublishPort();
        return (port.logIn(nick,pass));

    }
}
