package bean;

import db.DaoCommande;
import java.util.*;
import java.io.Serializable;


public class Commande implements Serializable{
    private int idCommande;
    private int idUser;
    private ArrayList<Produit> produits;
    private float prix;
    private String adresseLivraison;
    /*L'idée ça serait d'avoir deux états: En attente de livraison et prêt
    mais flemme de faire un enum au cas où on a des changements */
    private String etat;

    public Commande(){
        this.idCommande=0;
        this.idUser=0;
        this.produits=new ArrayList<Produit>();
        this.prix=0;
        this.adresseLivraison=null;
    }

    public Commande(int idCommande, int idUser, ArrayList<Produit> produits, float prix, String adresseLivraison){
        this.idCommande=idCommande;
        this.idUser=idUser;
        this.produits=produits;
        this.prix=prix;
        this.adresseLivraison=adresseLivraison;
    }

    public void majPrix(){
        float res=0;
        for(Produit p:this.getProduits()){
            res=res+p.getPrix();
        }
        this.setPrix(res);
    }

    public void majIdCommande(){
        this.setIdCommande(DaoCommande.getBonIdCommande());
    }

    public int getIdCommande(){
        return this.idCommande;
    }

    public int getIdUser(){
        return this.idUser;
    }

    public ArrayList<Produit> getProduits(){
        return this.produits;
    }

    public float getPrix(){
        return this.prix;
    }

    public String getAdresseLivraison(){
        return this.adresseLivraison;
    }

    public String getEtat(){
        return this.etat;
    }

    public void setIdCommande(int idCommande){
        this.idCommande=idCommande;
    }

    public void setIdUser(int idUser){
        this.idUser=idUser;
    }

    public void setProduits(ArrayList<Produit> produits){
        this.produits=produits;
    }

    public void setPrix(float prix){
        this.prix=prix;
    }

    public void setAdresseLivraison(String adresseLivraison){
        this.adresseLivraison=adresseLivraison;
    }

    public void setEtat(String etat){
        this.etat=etat;
    }
}
