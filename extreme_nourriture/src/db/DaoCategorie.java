package db;

import bean.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;


// Classe/Dao permettant d'intéragir aisément avec la table Catégorie de la BD
public class DaoCategorie {

    private static Connection connexion;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    //Connexion à la BD
    static{
        connexion=DBConnexion.getConnexion();
        preparedStatement=null;
        resultSet=null;
    }

    /*Méthode permettant l'obtention d'une liste d'objet Catégorie correspondant à toutes les catégories
    de la BD*/
    public static ArrayList<Categorie> getCategories(){
        ArrayList<Categorie> listeCategories=new ArrayList<Categorie>();
        try{
            String query="SELECT * FROM CATEGORIES";
            preparedStatement = connexion.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listeCategories.add(getCategorie(rs.getInt(1)));
            }
            return listeCategories;
        }catch(Exception e){
            System.out.println("Problème lors de la récupération des catégories: "+e);
            return listeCategories;
        }
    }
        /*Méthode  permettant l'ajout d'une catégorie dans la BD à partir d'un objet Catégorie */
    public static int ajouterCategorie(Categorie categorie){
        try{
            String query="INSERT INTO CATEGORIES(nom, description) VALUES(?,?)";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,categorie.getNom());
            preparedStatement.setString(2,categorie.getDescription());
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Echec lors de l'ajout de la catégorie"+e);
            return -1;
        }
    }

    /*Méthode  permettant la supression d'une catégorie dans la BD à partir de son id de Catégorie*/
    public static int supressionCategorie(int id){
        try{
            String query="DELETE FROM CATEGORIES WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur au cours de la supression de la catégorie avec l'id: "+id+" "+e);
            return -1;
        }
    }

    /* Méthode  permettant la supression d'une catégorie de la BD à partir de son nom */
    public static int supressionCategorie(String nom){
        try{
            String query="DELETE FROM CATEGORIES WHERE nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,nom);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur au cours de la supression de la catégorie avec le nom: "+nom+" "+e);
            return -1;
        }
    }

    /* Méthode  permettant de savoir si une catégorie existe dans la BD à partir de son id*/
    public static Boolean categorieExiste(int id){
        try{
            String query="SELECT * FROM CATEGORIES WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence de la catégorie d'id: "+id+" :"+e);
            return null;
        }
    }

    /* Méthode  permettant de savoir si une catégorie existe dans la BD à partir de son nom*/
    public static Boolean categorieExiste(String nom){
        try{
            String query="SELECT * FROM CATEGORIES WHERE nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,nom);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence de la catégorie de nom: "+nom+" "+e);
            return null;
        }
    }

    /* Méthode  permettant d'obtenir un objet Catégorie correspondant à une catégorie de la BD précisé à l'aide son id de Catégorie*/
    public static Categorie getCategorie(int id){
        try{
            String query="SELECT * FROM CATEGORIES WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Categorie categorie=new Categorie();
                categorie.setId(resultSet.getInt(1));
                categorie.setNom(resultSet.getString(2));
                categorie.setDescription(resultSet.getString(3));
                return categorie;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Echec de récupération de la catégorie d'id: "+id+": "+e);
            return null;
        }
    }

    /* Méthode  permettant d'obrenir un objet Catégorie correspondant à une catégorie de la BD précisé à l'aide de son nom de Catégorie */
    public static Categorie getCategorie(String nom){
        try{
            String query="SELECT * FROM CATEGORIES WHERE nom=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,nom);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Categorie categorie=new Categorie();
                categorie.setId(resultSet.getInt(1));
                categorie.setNom(resultSet.getString(2));
                categorie.setDescription(resultSet.getString(3));
                return categorie;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Echec de récupération de la catégorie nom: "+nom+" :"+e);
            return null;
        }
    }
}
