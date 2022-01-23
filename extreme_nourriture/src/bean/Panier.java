package bean;

import java.util.*;
import java.io.Serializable;


public class Panier implements Serializable{
    private ArrayList<Produit> produits;
    private float prix;

    public Panier(){
        this.produits=new ArrayList<Produit>();
        this.prix=0;
    }

    public Panier(ArrayList<Produit> produits, float prix){
        this.produits=produits;
        this.prix=prix;
    }

    public ArrayList<Produit> getProduits(){
        return this.produits;
    }

    public float getPrix(){
        return this.prix;
    }

    public void setProduits(ArrayList<Produit> produits){
        this.produits=produits;
    }

    public void setPrix(float prix){
        this.prix=prix;
    }

    public void addProduit(Produit produit){
        float prix = produit.getPrix();
        this.prix=this.prix + prix;
        this.produits.add(produit);
    }

    public void deleteProduit(Produit produit){
        if(this.produits.remove(produit)){
            float prix = produit.getPrix();
            this.prix=this.prix - prix;
        }
    }
    

    public void deleteNomProduit(String nom_produit){
        Produit produitToDelete = null;
        for(Produit prod : this.getProduits()) {
            if ( prod.getNom().equals(nom_produit) ){
                produitToDelete = prod;
            }
        }
        if (produitToDelete != null){
            this.produits.remove(produitToDelete);
            this.prix=this.prix - produitToDelete.getPrix();
        }
    }
}