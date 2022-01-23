package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import bean.Produit;
import java.util.*;

// Classe/Dao permettant d'intéragir aisément avec la table Produit de la BD

public class DaoProduit extends Dao{

    private static Connection connexion;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    //On réalise la connexion
    static{
        connexion=DBConnexion.getConnexion();
        preparedStatement=null;
        resultSet=null;
    }

    /* Méthode ayant pour but de récuppérer sous la forme d'une ArrayList de Produit l'intégralité
    des produits appartenant à une Catégorie de la BD dans la table Produits,précisé à l'aide de son id de Catégorie*/
    public static ArrayList<Produit> getProduitsCategorie(int idCategorie){
        ArrayList<Produit> listeProduits= new ArrayList<Produit>();
        try{
            String query="SELECT * FROM PRODUITS WHERE idCategorie=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCategorie);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                listeProduits.add(getProduit(rs.getInt(1)));
            }
            return listeProduits;
        }catch(Exception e){
            System.out.println("Problème lors de la récupération des produits: "+e);
            return listeProduits;
        }
    }

    /* Méthode ayant pour but de récuppérer sous la forme d'une ArrayList de Produit l'intégralité
    des produits contenus dans la table Produits de la BD*/
    public static ArrayList<Produit> getProduits(){
        ArrayList<Produit> listeProduits= new ArrayList<Produit>();
        try{
            String query="SELECT * FROM PRODUITS";
            preparedStatement=connexion.prepareStatement(query);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                listeProduits.add(getProduit(rs.getInt(1)));
            }
            return listeProduits;
        }catch(Exception e){
            System.out.println("Problème lors de la récupération des produits: "+e);
            return listeProduits;
        }
    }

    /* Méthode ayant pour but de permettre l'ajout d'un produit à la table Produits de la BD à partir d'un
    objet Produit*/
    public static int ajouterProduit(Produit produit){
        try{
            String query="INSERT INTO PRODUITS(idCategorie, nom, prix, descr, cheminImage) VALUES(?,?,?,?,?)";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,produit.getIdCategorie());
            preparedStatement.setString(2,produit.getNom());
            preparedStatement.setFloat(3,produit.getPrix());
            preparedStatement.setString(4,produit.getDescription());
            if(produit.getCheminImage()!=null){
                preparedStatement.setString(5,produit.getCheminImage()); 
            }else{
                preparedStatement.setNull(5,Types.NULL);
            }
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur lors de l'ajout du produit: "+e);
            return -1;
        }
    }

    /* Méthode ayant pour but de permettre la supression d'un produit de la table Produits de la BD désigné à partir de son
    id de Produit */
    public static int suppressionProduit(int idProduit){
        try{
            String query="DELETE FROM PRODUITS WHERE idProduit=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idProduit);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la supression du produit d'idProduit: "+idProduit+" "+e);
            return -1;
        }
    }

        /* Méthode ayant pour but de permettre la supression d'un produit de la table Produits de la BD désigné à partir de son
    nom de Produit et l'id de sa catégorie */
    public static int suppressionProduit(int idCategorie, String nom){
        try{
            String query="DELETE FROM PRODUITS WHERE idCategorie=? AND nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCategorie);
            preparedStatement.setString(2,nom);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la supression du produit d'idCategorie: "+idCategorie+"  nom:"+nom+" :"+e);
            return -1;
        }
    }

    /* Méthode ayant pour but de récuppérer un objet Produit correspondant à un produit de la table Produits de la BD, ce dernier est désigné
    à l'aide de son id de produit*/
    public static Produit getProduit(int idProduit){
        try{
            String query="SELECT * FROM PRODUITS WHERE idProduit=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idProduit);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Produit produit=new Produit();
                produit.setIdProduit(resultSet.getInt(1));
                produit.setIdCategorie(resultSet.getInt(2));
                produit.setNom(resultSet.getString(3));
                produit.setPrix(resultSet.getFloat(4));
                produit.setDescription(resultSet.getString(5));
                produit.setCheminImage(resultSet.getString(6));
                return produit;
            }else{
                return null;
            }
            
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération du produit d'idProduit: "+idProduit+" :"+e);
            return null;
        }
    }
    /* Méthode ayant pour but de récuppérer un objet Produit correspondant à un produit de la table Produits de la BD, ce dernier est désigné
    à l'aide de son nom de produit et l'id de la catégorie à laquelle il appartient*/
    public static Produit getProduit(int idCategorie, String nom){
        try{
            String query="SELECT * FROM PRODUITS WHERE idCategorie=? AND nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCategorie);
            preparedStatement.setString(2,nom);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Produit produit=new Produit();
                produit.setIdProduit(resultSet.getInt(1));
                produit.setIdCategorie(resultSet.getInt(2));
                produit.setNom(resultSet.getString(3));
                produit.setPrix(resultSet.getFloat(4));
                produit.setDescription(resultSet.getString(5));
                produit.setCheminImage(resultSet.getString(6));
                return produit;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Erreur lors de la récupération du produit d'idCategorie: "+idCategorie+" et de nom: "+nom+" :"+e);
            return null;
        }
    }

    /* Méthode ayant pour but de vérifier si un produit portant un certain id de Produit existe dans la BD*/
    public static Boolean produitExiste(int idProduit){
        try{
            String query="SELECT * FROM PRODUITS WHERE idProduit=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idProduit);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence du produit d'idProduit: "+idProduit+" :"+e);
            return null;
        }
    }
    /* Méthode ayant pour but de vérifier si un produit portant un certain nom de Produit au sein d'une catégorie dont on précise l'id de catégorie
     existe dans la BD*/
    public static Boolean produitExiste(int idCategorie, String nom){
        try{
            String query="SELECT * FROM PRODUITS WHERE idCategorie=? AND nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCategorie);
            preparedStatement.setString(2,nom);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence du produit d'idCategorie: "+idCategorie+" et de nom:"+nom+" :"+e);
            return null;
        }
    }

}