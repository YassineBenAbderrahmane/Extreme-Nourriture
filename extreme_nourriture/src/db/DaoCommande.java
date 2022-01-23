package db;

import bean.Commande;
import bean.Produit;
import bean.Utilisateur;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

// Classe/Dao permettant d'intéragir aisément avec la table Commande de la BD
public class DaoCommande extends Dao{
 
    private static Connection connexion;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    //Connexion à la BD
    static{
        connexion=DBConnexion.getConnexion();
        preparedStatement=null;
        resultSet=null;
    }

    /* Méthode permettant d'obtenir une ArrayList contenant toutes les commandes de la BD sous forme d'objet Commande*/
    public static ArrayList<Commande> getCommandes(){
        ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
        try{
            String query = "SELECT * FROM COMMANDES";
            preparedStatement = connexion.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listeCommandes.add(getCommande(rs.getInt(1)));
            }
            return listeCommandes;
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération des commandes de tout les utilisateurs: "+e);
            return listeCommandes;
        }
    }

    /* Méthode permettant d'obtenir une ArrayList contenant toutes les commandes de la BD d'un utilisateur particulier
    sous la forme d'objet Commande */
    public static ArrayList<Commande> getCommandesUtilisateur(Utilisateur utilisateur){
        ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
        try{
            String query = "SELECT * FROM COMMANDES WHERE idUser=?";
            preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1,utilisateur.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listeCommandes.add(getCommande(rs.getInt(1)));
            }
            return listeCommandes;
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération des commandes de l'utilisateur: "+utilisateur.getId());
            return listeCommandes;
        }
    }

    /* Méthode permettant d'obtenir le réel id d'un objet Commande (ce dernier pouvant être erroné car il est fixé côté BD)*/
    public static int getBonIdCommande(){
        try{
            String query="SELECT max(idCommande) FROM COMMANDES;";
            preparedStatement=connexion.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            return resultSet.getInt(1) +1;
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération du prochain IdCommande: "+e);
            return -1;
        }

        
    }


    /* Méthode permettant d'ajouter dans la table Quantités les produits qui se trouvent dans l'objet Commande*/
    public static void ajouterListeProduits(Commande commande){
        try{
            ArrayList<Produit> listeProduits=commande.getProduits();
            for(Produit p:listeProduits){
                String query="INSERT INTO QUANTITES(idCommande,idProduit,quantite) VALUES(?,?,?)";
                preparedStatement=connexion.prepareStatement(query);
                preparedStatement.setInt(1,commande.getIdCommande());
                preparedStatement.setInt(2,p.getIdProduit());
                /*Ce qui est ici est moche car on peut se retrouver dans la situation
                où on risque d'ajouter plusieurs fois le même produit à la place de modifier
                la quantité, à arranger plus tard*/
                preparedStatement.setInt(3,1);
                preparedStatement.executeUpdate();
            }
        }catch(Exception e){
            System.out.println("Erreur lors de l'ajout des listes de produit d'une commande:" +e);
        }
    }

    /*Méthode ayant pour but d'ajouter un objet Commande dans la table Commande de la BD*/
    //Ca force la mise à jour de l'idCommande et du prix aussi attention
    public static int ajouterCommande(Commande commande){
        try{
            commande.majIdCommande();
            commande.majPrix();
            String query="INSERT INTO COMMANDES(idUser, prix, adresseLivraison, etat) VALUES(?,?,?,?)";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,commande.getIdUser());
            preparedStatement.setFloat(2,commande.getPrix());
            preparedStatement.setString(3,commande.getAdresseLivraison());
            preparedStatement.setString(4,commande.getEtat());
            preparedStatement.executeUpdate();
            ajouterListeProduits(commande);
            return 1;
        }catch(Exception e){
            System.out.println("Erreur lors de l'ajout de la commande: "+e);
            return -1;
        }
    }

    /* Méthode ayant pour but de supprimer une commande de la table Commande de la BD à partir de son id de Commande*/
    public static int suppressionCommande(int idCommande){
        try{
            String query="DELETE FROM COMMANDES WHERE idCommande=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCommande);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur lors de la supression de la commande d'idCommande: "+idCommande+" :"+e);
            return -1;
        }
    }

    /* Méthode ayant pour but de retourner une ArrayList des produits se trouvant dans une commande à partir de 
    son id de Commande */
    private static ArrayList<Produit> getListeProduits(int idCommande){
        ArrayList<Produit> listeProduits=new ArrayList<Produit>();
        try{
            String query="SELECT * FROM QUANTITES WHERE idCommande=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCommande);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Produit produit= DaoProduit.getProduit(resultSet.getInt(3));
                for (int i=0;i<resultSet.getInt(4);i++){
                    listeProduits.add(produit);
                }
            }
            return listeProduits;
        }catch(Exception e){
            System.out.println("Erreur lors de la production de la liste de produits de la commande d'id: "+idCommande+" :"+e);
            return listeProduits;
        }

    }
    /* Méthode permettant de récuppérer un objet Commande correspondant à une commande au sein de la BD à partir de son id de Commande */
    //Force maj prix avant de rendre attention
    public static Commande getCommande(int idCommande){
        try{
            String query="SELECT * FROM COMMANDES WHERE idCommande=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCommande);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Commande commande=new Commande();
                commande.setIdCommande(resultSet.getInt(1));
                commande.setIdUser(resultSet.getInt(2));
                commande.setPrix(resultSet.getFloat(3));
                commande.setAdresseLivraison(resultSet.getString(4));
                commande.setEtat(resultSet.getString(5));
                commande.setProduits(getListeProduits(idCommande));
                commande.majPrix();
                return commande;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération de la commande d'idCommande: "+idCommande+" :"+e);
            return null;
        }
    }

    //Marche pas ?
    /* Méthode permettant de fixer l'état d'une commande au sein de la BD précisé à l'aide de son id de Commande*/
    public static int setEtat(int idCommande, String etat){
        try{
            String query="UPDATE COMMANDES SET etat=? WHERE idCommande= ?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,etat);
            preparedStatement.setInt(2,idCommande);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur lors du changement de l'état de la commande d'idCommande: "+idCommande+" :"+e);
            return -1;
        }
    }

}