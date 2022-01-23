package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/* Servlet qui gère l'affichage de la page d'accueil*/
public class Accueil extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        this.getServletContext().getRequestDispatcher("/web_rsc/accueilHeaderNavbar.jsp").include(request,response);
        this.getServletContext().getRequestDispatcher("/web_rsc/accueil.jsp").include(request,response);
    }
}