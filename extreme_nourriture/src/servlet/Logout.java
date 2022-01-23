package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/* Servlet qui gère l'affichage de la page de déconnexion */
public class Logout extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        this.getServletContext().getRequestDispatcher("/web_rsc/logout.jsp").include(request,response);
    }

}