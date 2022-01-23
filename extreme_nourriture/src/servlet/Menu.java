package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/* Servlet qui gère l'affichage des menus des catégories et des produits */
public class Menu extends HttpServlet{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCat = request.getParameter("id");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if(idCat==null){
            this.getServletContext().getRequestDispatcher("/web_rsc/menu_cat.jsp").include(request,response);
        }else{
            try{
                this.getServletContext().getRequestDispatcher("/web_rsc/menu_produit.jsp").include(request,response);
            }catch(Exception e){
                System.out.println("Problème lors de l'affichage du Menu de catégorie: "+idCat+" :"+e);

            }
        }    
    }
}
//Donc là on veut pouvoir passer l'id de la categorie demander en POST lorsque
//l'on clique sur le nom de la catégorie et du coup ça renvoie vers la bonne page