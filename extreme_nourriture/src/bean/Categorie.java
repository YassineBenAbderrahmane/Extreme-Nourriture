package bean;

import java.io.Serializable;


public class Categorie implements Serializable{
    private int id;
    private String nom;
    private String description;

    public Categorie(){
        this.id=0;
        this.nom=null;
        this.description=null;
    }

    public Categorie(int id, String nom, String description){
        this.id=id;
        this.nom=nom;
        this.description=description;
    }

    public int getId(){
        return this.id;
    }

    public String getNom(){
        return this.nom;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setDescription(String description){
        this.description=description;
    }
}