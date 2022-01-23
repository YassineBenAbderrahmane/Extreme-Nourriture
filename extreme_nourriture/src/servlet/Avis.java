package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Avis extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        this.getServletContext().getRequestDispatcher("/web_rsc/avis.jsp").include(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        this.getServletContext().getRequestDispatcher("/web_rsc/avis.jsp").include(request,response);
    }

}