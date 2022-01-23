package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/* Classe permettant de créer et de donner accès à une connexion vers la
base de données au DAO*/

public class DBConnexion{
    public static String url;
    public static Connection connexion;

    static{
        url="jdbc:sqlite:/tmp/test.db";
        connexion=null;
    }

    /*Méthode créant la connexion à la BD si elle n'existe pas encore avant de la rendre, ou
    la rend directement si elle existe déjà */

    public static Connection getConnexion(){
        if(connexion==null){
            try{
                Class.forName("org.sqlite.JDBC");
                connexion = DriverManager.getConnection(url);
                Statement s = connexion.createStatement();
                s.executeUpdate("PRAGMA foreign_keys = ON;");
            } catch(Exception e){
                System.out.println("La connexion a échoué: "+e);
                return null;
            }
            System.out.println("La connexion semble être établie.");
        }
        return connexion;
    }

    //Méthode permettant la déconnexion à la BD
    public static void deconnexion(){
        try{
            connexion.close();
        } catch(Exception e){
            System.out.println("La déconnexion a échoué:"+e);
        }
    }
}