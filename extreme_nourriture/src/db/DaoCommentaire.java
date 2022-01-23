package db;

import bean.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;


// Classe/Dao permettant d'intéragir aisément avec la table Commentaires de la BD

public class DaoCommentaire{
    private static Connection connexion;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;


    //On effectue la connexion
    static{
        connexion=DBConnexion.getConnexion();
        preparedStatement=null;
        resultSet=null;
    }

    /*Méthode permettant d'obtenir un Objet Commentaire représentant un commentaire stocké en BD désigné par 
    son id de commentaire */
    public static Commentaire getCommentaire(int idCommentaire){
        try{
            String query="SELECT * FROM COMMENTAIRES WHERE idCommentaire=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCommentaire);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Commentaire commentaire=new Commentaire();
                commentaire.setIdCommentaire(resultSet.getInt(1));
                commentaire.setIdUtilisateur(resultSet.getInt(2));
                commentaire.setDate(resultSet.getString(4));
                commentaire.setNote(resultSet.getInt(3));
                commentaire.setTexte(resultSet.getString(5));
                return commentaire;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Echec de récupération du commentaire d'idCommentire: "+idCommentaire+": "+e);
            return null;
        }
    }

    /* Méthode permettant d'obtenir une ArrayList de Commentaire contenant l'intégralité des commentaires stocké en BD*/
    public static ArrayList<Commentaire> getCommentaires(){
        ArrayList<Commentaire> listeCommentaires=new ArrayList<Commentaire>();
        try{
            String query="SELECT * FROM COMMENTAIRES";
            preparedStatement = connexion.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listeCommentaires.add(getCommentaire(rs.getInt(1)));
            }
            return listeCommentaires;
        }catch(Exception e){
            System.out.println("Problème lors de la récupération des commentaires: "+e);
            return listeCommentaires;
        }
    }

    /* Méthode permettant d'ajouter un commentaire à la table Commentaires de la BD à partir d'un objet Commentaire */
    public static int ajouterCommentaire(Commentaire commentaire){
        try{
            String query="INSERT INTO COMMENTAIRES(idUser, note, dateCommentaire, texte) VALUES(?,?,?,?)";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,commentaire.getIdUtilisateur());
            preparedStatement.setInt(2,commentaire.getNote());
            preparedStatement.setString(3,commentaire.getDate().toString());
            preparedStatement.setString(4,commentaire.getTexte());
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Echec lors de l'ajout de l'objet commentaire"+e);
            return -1;
        }
    }

    /* Méthode permettant la supression d'un commentaire de la BD à partir de son idCommentaire */
    public static int suppressionCommentaire(int idCommentaire){
        try{
            String query="DELETE FROM COMMENTAIRES WHERE idCommentaire=?";
            preparedStatement=connexion.prepareStatement(query);
            preparedStatement.setInt(1,idCommentaire);
            return preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Erreur au cours de la supression du commentaire avec l'id: "+idCommentaire+" "+e);
            return -1;
        }
    }
}