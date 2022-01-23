package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.*;
import bean.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;




/* Servlet qui gère l'affichage de la page d'inscription et l'inscription*/

public class RegisterServlet extends HttpServlet {
    private static int id=0;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String sexe = request.getParameter("sexe");
        String adresse = request.getParameter("adresse");
 

        Utilisateur new_utilisateur = new Utilisateur(id,prenom,nom,sexe,email,password,adresse,"client","chemin");
        int res = DaoUtilisateur.ajouterUtilisateur(new_utilisateur);
        if (res==-1){
            out.write("false");
        }else{
            out.write("true");
        }
        System.out.println(res);
    }

}
