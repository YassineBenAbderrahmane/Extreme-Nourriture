package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dao {
    public Connection connexion;


    public Dao(){
        this.connexion=DBConnexion.getConnexion();
    }

    /*public Connection getConnexion(){
        return this.connexion;
    }

    public Statement getStatement(){
        return this.statement;
    }

    public PreparedStatement getPreparedStatement(){
        return this.preparedStatement;
    }

    public ResultSet getResultSet(){
        return this.resultSet;
    }

    public String getQuery(){
        return this.query;
    }

    public void setConnexion(Connection connexion){
        this.connexion=connexion;
    }

    public void setStatement(Statement statement){
        this.statement=statement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement){
        this.preparedStatement=preparedStatement;
    }

    public void setResultSet(ResultSet resultSet){
        this.resultSet=resultSet;
    }

    public void setQuery(String query){
        this.query=query;
    }*/
}
