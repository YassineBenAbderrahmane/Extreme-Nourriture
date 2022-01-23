package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Categorie;
import bean.Produit;
import bean.Utilisateur;
import db.*;

/* Servlet qui gère l'affichage des différentes pages d'administration ainsi que l'ajout/supression d'utilisateurs/produits/catégories/avis */
public class Admin extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(verifierPrivilege(request, response)){
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String page = request.getParameter("page");
            if(page==null){
                this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                this.getServletContext().getRequestDispatcher("/web_rsc/adminPage.jsp").include(request,response);
            }else{
                switch(page){
                    case "PageUtilisateur":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminUtilisateursPage.jsp").include(request,response);
                        break;

                    case "PageProduits":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminProduitsPage.jsp").include(request,response);
                        break;
                    
                    case "PageCommandes":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminCommandesPage.jsp").include(request,response);
                        break;
                    
                    case "PageCategories":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminCategoriePage.jsp").include(request,response);
                        break;

                    case "PageCommentaires":
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminCommentairesPage.jsp").include(request,response);
                    break;
                
                    case "PageAjoutUtilisateur":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminAjoutUtilisateurPage.jsp").include(request,response);
                        break;

                    case "PageAjoutProduit":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminAjoutProduitPage.jsp").include(request,response);
                        break;

                    case "PageAjoutCategorie":
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminAjoutCategoriePage.jsp").include(request,response);
                        break;
                    
                    
                    default:
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                        this.getServletContext().getRequestDispatcher("/web_rsc/adminPage.jsp").include(request,response);
                }
            }
        }else{
            PrintWriter out = response.getWriter();
            out.write("<html><head><title>Error</title></head><body>Not Found</body></html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(verifierPrivilege(request, response)){
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String idSupprProd = request.getParameter("idSupprProd");
            String idSupprUser = request.getParameter("idSupprUser");
            String idSupprCommande = request.getParameter("idSupprCommande");
            String idSupprCategorie = request.getParameter("idSupprCategorie");
            String idSupprCommentaire = request.getParameter("idSupprCommentaire");
            String ajoutUser = request.getParameter("AjoutUser");
            String ajoutProduit = request.getParameter("AjoutProduit");
            String ajoutCat = request.getParameter("AjoutCategorie");

            if(idSupprProd != null){
                try{
                    DaoProduit.suppressionProduit(Integer.parseInt(idSupprProd));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminProduitsPage.jsp").include(request,response);

                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de la supression du produit d'idProduit: "+idSupprProd+" "+e);
                }

            }

            if(idSupprUser != null){
                try{
                    DaoUtilisateur.suppressionUtilisateur(Integer.parseInt(idSupprUser));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminUtilisateursPage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de la supression de l'utilisateur d'id: "+idSupprUser+" "+e);
                }
            }

            if(idSupprCommande != null){
                try{
                    DaoCommande.suppressionCommande(Integer.parseInt(idSupprCommande));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminCommandesPage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de la supression de la commande d'id: "+idSupprCommande+" "+e);
                }
            }

            if (idSupprCategorie != null){
                try{
                    DaoCategorie.supressionCategorie(Integer.parseInt(idSupprCategorie));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminCategoriePage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de la supression de la categorie d'id: "+idSupprCategorie+" "+e);
                }
            }

            if(idSupprCommentaire != null){
                try{
                    DaoCommentaire.suppressionCommentaire(Integer.parseInt(idSupprCommentaire));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminCommentairesPage.jsp").include(request,response);

                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de la supression du commentaire d'id: "+idSupprCommentaire+" "+e);
                }

            }
                    
            if(ajoutUser != null){
                try{
                    ajoutUtilisateur(request.getParameter("prenom"),request.getParameter("nom"),request.getParameter("sexe"),request.getParameter("adresse"),request.getParameter("mail"),request.getParameter("mdp"),request.getParameter("privilege"));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminUtilisateursPage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de l'ajout d'un utilisateur' "+e);
                }
            }

            if(ajoutProduit != null){
                try{
                    ajoutProduit(request.getParameter("idCategorie"), request.getParameter("nom"), request.getParameter("prix"), request.getParameter("description"), request.getParameter("cheminImage"));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminProduitsPage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de l'ajout d'un produit' "+e);
                }
            }

            if(ajoutCat != null){
                try{
                    ajoutCategorie(request.getParameter("nom"), request.getParameter("description"));
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminHeaderNavbar.jsp").include(request,response);
                    this.getServletContext().getRequestDispatcher("/web_rsc/adminCategoriePage.jsp").include(request,response);
                }catch(Exception e){
                    System.out.println("Erreur dans l'espace Admin lors de l'ajout d'une catégorie: "+e);
                }
                
            }
        }else{
            PrintWriter out = response.getWriter();
            out.write("<html><head><title>Error</title></head><body>Not Found</body></html>");
        }
    }

    /*Fonction prenant les informations pour la création d'un nouvel utilisateur transmis par une requête et assurant sa création et
    son ajout à la BD*/
    private void ajoutUtilisateur(String prenom, String nom, String sexe, String adresse, String mail, String mdp, String privilege){
        try{
            Utilisateur userAjouter = new Utilisateur();
            userAjouter.setPrenom(prenom);
            userAjouter.setNom(nom);
            userAjouter.setSexe(sexe);
            userAjouter.setAdresseLivraison(adresse);
            userAjouter.setMail(mail);
            userAjouter.setMotDePasse(mdp);
            userAjouter.setPrivilege(privilege);
            userAjouter.setCheminAvatar(null);
            DaoUtilisateur.ajouterUtilisateur(userAjouter);
        }catch(Exception e){
            System.out.println("Erreur dans l'espace Admin lors de l'ajout d'un utilisateur' "+e);
        }
    }

    /*Fonction prenant les informations pour la création d'un nouveau produit transmis par une requête et assurant sa création et
    son ajout à la BD*/
    private void ajoutProduit(String idCategorie, String nom, String prix, String description, String cheminImage){
        try{
            Produit prodAjouter = new Produit();
            prodAjouter.setIdCategorie(Integer.parseInt(idCategorie));
            prodAjouter.setNom(nom);
            prodAjouter.setPrix(Float.parseFloat(prix));
            prodAjouter.setDescription(description);
            prodAjouter.setCheminImage(cheminImage);
            DaoProduit.ajouterProduit(prodAjouter);
        }catch(Exception e){
            System.out.println("Erreur dans l'espace Admin lors de l'ajout d'un produit: "+e);
        }
    }
    /*Fonction prenant les informations pour la création d'une nouvelle catégorie transmis par une requête et assurant sa création et
    son ajout à la BD*/
    private void ajoutCategorie(String nom, String description){
        try{
            Categorie catAjouter = new Categorie();
            catAjouter.setNom(nom);
            catAjouter.setDescription(description);
            DaoCategorie.ajouterCategorie(catAjouter);
        }catch(Exception e){
            System.out.println("Erreur dans l'espace Admin lors de l'ajout d'une catégorie: "+e);
        }
    }

    /* Fonction permettant de vérifier si l'utilisateur tentant d'accéder à cette page est bien connecté et 
    si il possède les droits nécessaire, renvoie un booléen en conséquence */
    private boolean verifierPrivilege(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String mail = (String)session.getAttribute("mail");
        String mdp = (String)session.getAttribute("password");
    
        if (mail != null && mdp != null){
            if(DaoUtilisateur.utilisateurExiste(mail) && (DaoUtilisateur.verificationMotDePasse(mail, mdp))){
                return DaoUtilisateur.getUtilisateur(mail).getPrivilege().equals("admin");
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}

