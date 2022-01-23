package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import db.*;
import bean.*;

public class EnregistrerCommande extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adresseLivraison=request.getParameter("adresse");
        String typeCommande = request.getParameter("typeCommande");
        HttpSession session = request.getSession();
        bean.Panier panier = (bean.Panier)session.getAttribute("panier");
        String mail = (String)session.getAttribute("mail");
        String pas = (String)session.getAttribute("password");
        if(mail != null && pas != null){
            if(DaoUtilisateur.verificationMotDePasse(mail,pas)){
                int id = DaoUtilisateur.getUtilisateur(mail).getId();
                Commande commande = new Commande( 1,id, panier.getProduits(), panier.getPrix(), adresseLivraison); //idCommande est autocorrigé côté bd

                if (typeCommande.equals("1")){
                    commande.setEtat("En attente de récupération");
                }else{
                    commande.setEtat("En attente de livraison");
                }
                int test = DaoCommande.ajouterCommande(commande);
        
                panier = new bean.Panier();
                session.setAttribute("panier", panier);
            }
        }

    

    }
           

}