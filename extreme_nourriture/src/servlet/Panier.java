package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.*;
import db.DaoProduit;

/* Servlet qui gère l'affichage du panier mais aussi l'ajout/supression d'article, ainsi que la supression du panier*/

public class Panier extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String idProduitAjout = request.getParameter("idProduitAjout");
        String nomProduitSupprimer = request.getParameter("nomProduitSuppr");
        String viderPanier = request.getParameter("ViderPanier");
        HttpSession session = request.getSession (); 
        bean.Panier panier = (bean.Panier)session.getAttribute("panier"); // Get le panier à partir des sessions

        if (panier == null){
            panier = new bean.Panier(); // initialisation du panier
        }

        if(idProduitAjout != null){
            try{
                panier.addProduit(DaoProduit.getProduit(Integer.parseInt(idProduitAjout))); 
            }catch(Exception e){
                System.out.println("Erreur lors de l'ajout au panier du produit d'idProduit: "+idProduitAjout+" "+e);
            }
        }

        if(viderPanier != null){
            panier = new bean.Panier(); // Réinitialisation du panier  
        }

        if(nomProduitSupprimer != null){
            try{
                panier.deleteNomProduit(nomProduitSupprimer);
            }catch(Exception e){
                System.out.println("Erreur lors de l'ajout au panier du produit de nom Produit: "+nomProduitSupprimer+" "+e); //Suppression d'un produit à partir du nom
            }
        }

        session.setAttribute("panier", panier);
        this.getServletContext().getRequestDispatcher("/web_rsc/panier.jsp").include(request,response);
    }

}