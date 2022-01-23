package db;

import bean.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

// Classe/Dao permettant d'intéragir aisément avec la table Utilisateurs de la BD

public class DaoUtilisateur extends Dao{

    private static Connection connexion;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    //On effectue la connexion
    static{
        connexion=DBConnexion.getConnexion();
        preparedStatement=null;
        resultSet=null;
    }

    /* Méthode permettant d'obtenir une ArrayList des Utilisateur contenant l'intégralité des
    Utilisateurs contenu dans la table Utilisateurs de la BD*/
    public static ArrayList<Utilisateur> getUtilisateurs(){
        ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
        try{
            String query="SELECT * FROM UTILISATEURS";
            preparedStatement = connexion.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listeUtilisateurs.add(getUtilisateur(rs.getString(4)));
            }
            return listeUtilisateurs;
        }catch(Exception e){
            System.out.println("Problème lors de la récupération des utilisateurs: "+e);
            return listeUtilisateurs;
        }
    }

    /* Méthode permettant d'obtenir l'id correct d'un utilisateur à partir de son objet Utilisateur
    (en effet l'id utilisateur étant fixé côté BD, on peut avoir une désynchronisation entre
    l'id dans l'objet Java et dans la BD)*/
    private static int getBonIdUtilisateur(Utilisateur utilisateur){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE mail=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,utilisateur.getMail());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }else{
                return -1;
            }
        }catch(Exception e){
            System.out.println("Erreur lors de la recherche du bon id de l'utilisateur d'email: "+utilisateur.getMail()+" :"+e);
            return -1;
        }
    }

    public static void majIdUtilisateur(Utilisateur utilisateur){
        utilisateur.setId(getBonIdUtilisateur(utilisateur));
    }

    /* Méthode permettant d'ajouter un utilisateur à la table Utilisateurs de la BD à partir d'un objet Utilisateur */
    //Attention il existe une contrainte d'unicité du mail qui est vérifié côté BD
    //Attention il existe peut être un décalage entre l'id Java et l'id Côté Bd il faut penser à le maj manuellement avec
    //la fonction majIdUtilisateur()
    public static int ajouterUtilisateur(Utilisateur utilisateur){
        try{
            String query="INSERT INTO UTILISATEURS(prenom, nom, mail, sexe, motDePasse, adresseLivraison, privilege, cheminAvatar) VALUES(?,?,?,?,?,?,?,?)";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1, utilisateur.getPrenom());
            preparedStatement.setString(2,utilisateur.getNom());
            preparedStatement.setString(3,utilisateur.getMail());
            preparedStatement.setString(4,utilisateur.getSexe());
            preparedStatement.setString(5,utilisateur.getMotDePasse());
            preparedStatement.setString(6,utilisateur.getAdresseLivraison());
            preparedStatement.setString(7,utilisateur.getPrivilege());
            if(utilisateur.getCheminAvatar()!=null){
                preparedStatement.setString(8,utilisateur.getCheminAvatar());
            }else{
                preparedStatement.setNull(8,Types.NULL);
            }
            int result = preparedStatement.executeUpdate();
            return result;
        }catch(Exception e){
            System.out.println("Echec de l'ajout de l'utlisateur: "+e);
            return -1;
        }
    }

    /* Méthode permettant d'obtenir un objet utilisateur correspondant à un utilisateur de la table
    Utilisateurs de la BD désigné à l'aide de son email*/
    public static Utilisateur getUtilisateur(String mail){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE mail=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,mail);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Utilisateur utilisateur=new Utilisateur();
                utilisateur.setId(resultSet.getInt(1));
                utilisateur.setPrenom(resultSet.getString(2));
                utilisateur.setNom(resultSet.getString(3));
                utilisateur.setMail(resultSet.getString(4));
                utilisateur.setSexe(resultSet.getString(5));
                utilisateur.setMotDePasse(resultSet.getString(6));
                utilisateur.setAdresseLivraison(resultSet.getString(7));
                utilisateur.setPrivilege(resultSet.getString(8));
                utilisateur.setCheminAvatar(resultSet.getString(9));
                return utilisateur;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Echec de récupération de l'utilisateur au mail: "+mail+" :"+e);
            return null;
        }
    }
    /* Méthode permettant d'obtenir un objet utilisateur correspondant à un utilisateur de la table
    Utilisateurs de la BD désigné à l'aide de son id d'utilisateur*/
    public static Utilisateur getUtilisateur(int id){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Utilisateur utilisateur=new Utilisateur();
                utilisateur.setId(resultSet.getInt(1));
                utilisateur.setPrenom(resultSet.getString(2));
                utilisateur.setNom(resultSet.getString(3));
                utilisateur.setMail(resultSet.getString(4));
                utilisateur.setSexe(resultSet.getString(5));
                utilisateur.setMotDePasse(resultSet.getString(6));
                utilisateur.setAdresseLivraison(resultSet.getString(7));
                utilisateur.setPrivilege(resultSet.getString(8));
                utilisateur.setCheminAvatar(resultSet.getString(9));
                return utilisateur;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Echec de récupération de l'utilisateur au mail: "+id+" :"+e);
            return null;
        }
    }

    /* Méthode permettant de vérifier si un utilisateur existe dans la table Utilisateurs de la 
    BD à partir de son adresse mail */
    //Peut aussi servir à vérifier si l'email existe en soit.
    public static Boolean utilisateurExiste(String mail){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE mail=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,mail);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence de l'utilisateur au mail: "+mail+" "+e);
            return null;
        }
    }

    /* Méthode permettant de vérifier si un utilisateur existe dans la table Utilisateurs de la 
    BD à partir de son id utilisateur */
    public static Boolean utilisateurExiste(int id){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification de l'existence de l'utilisateur d'id: "+id+" "+e);
            return null;
        }
    }

    /*Méthode permettant de vérifier si un utilisateur de la table Utilisateurs de la BD, désigné par son adresse mail, possède bien
    un certain mot de passe */
    //terrible façon de vérifier que le motDePasse, rien que pour le type de stockage, mais le prof a dit pas d'overkill
    public static Boolean verificationMotDePasse(String mail, String mdp){
        try{
            String query="SELECT * FROM UTILISATEURS WHERE mail=? AND motDePasse=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,mdp);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la vérification du mot de passe de l'utilisateur à l'email: "+mail+" :"+e);
            return null;
        }
    }

    /* Méthode permettant de supprimer un utilisateur désigné par son adresse mail de la table Utilisateurs de la BD*/
    public static int suppressionUtilisateur(String mail){
        try{
            String query="DELETE FROM UTILISATEURS WHERE mail=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setString(1,mail);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la supression de l'utilisateur à l'email: "+mail+" "+e);
            return -1;
        }
    }
    /* Méthode permettant de supprimer un utilisateur désigné par son id d'utilisateur de la table Utilisateurs de la BD*/
    public static int suppressionUtilisateur(int id){
        try{
            String query="DELETE FROM UTILISATEURS WHERE id=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Une erreur s'est produite lors de la supression de l'utilisateur à l'id: "+id+" "+e);
            return -1;
        }
    }


}