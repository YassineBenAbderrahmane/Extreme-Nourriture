package bean;

import java.util.*;
import java.io.Serializable;


public class Produit {
    private int idProduit;
    private int idCategorie;
    private String nom;
    private float prix;
    private String description;
    private String cheminImage;

    public Produit(){
        this.idProduit=0;
        this.idCategorie=0;
        this.nom=null;
        this.prix=0;
        this.description=null;
        this.cheminImage=null;
    }

    public Produit(int idProduit, int idCategorie, String nom, int prix, String description, String cheminImage){
        this.idProduit=idProduit;
        this.idCategorie=idCategorie;
        this.nom=nom;
        this.prix=prix;
        this.description=description;
        this.cheminImage=cheminImage;
    }

    public int getIdProduit(){
        return this.idProduit;
    }

    public int getIdCategorie(){
        return this.idCategorie;
    }

    public String getNom(){
        return this.nom;
    }

    public float getPrix(){
        return this.prix;
    }

    public String getDescription(){
        return this.description;
    }

    public String getCheminImage(){
        return this.cheminImage;
    }

    public void setIdProduit(int idProduit){
        this.idProduit=idProduit;
    }

    public void setIdCategorie(int idCategorie){
        this.idCategorie=idCategorie;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setPrix(float prix){
        this.prix=prix;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setCheminImage(String cheminImage){
        this.cheminImage=cheminImage;
    }
}
