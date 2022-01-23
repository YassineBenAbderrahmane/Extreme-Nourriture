package bean;

import java.io.Serializable;


public class Utilisateur implements Serializable{
    private int id;
    private String prenom;
    private String nom;
    private String mail;
    private String sexe;
    private String motDePasse; //Ca serait mieux sous forme de hash
    private String adresseLivraison;
    private String privilege;
    private String cheminAvatar;



    public Utilisateur(){
        this.id=0;
        this.prenom=null;
        this.nom=null;
        this.sexe=null;
        this.mail=null;
        this.motDePasse=null;
        this.adresseLivraison=null;
        this.privilege=null;
        this.cheminAvatar=null;
    }

    public Utilisateur(int id,String prenom, String nom, String sexe, String mail, String motDePasse, String adresseLivraison, String privilege, String cheminAvatar){
        this.id=id;
        this.mail=mail;
        this.prenom=prenom;
        this.nom=nom;
        this.sexe=sexe;
        this.motDePasse=motDePasse;
        this.adresseLivraison=adresseLivraison;
        this.privilege=privilege;
        this.cheminAvatar=cheminAvatar;
    }

    public int getId(){
        return this.id;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getNom(){
        return this.nom;
    }

    public String getSexe(){
        return this.sexe;
    }

    public String getMail(){
        return this.mail;
    }

    public String getMotDePasse(){
        return this.motDePasse;
    }

    public String getAdresseLivraison(){
        return this.adresseLivraison;
    }

    public String getPrivilege(){
        return this.privilege;
    }

    public String getCheminAvatar(){
        return this.cheminAvatar;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setMail(String mail){
        this.mail=mail;
    }

    public void setSexe(String sexe){
        this.sexe=sexe;
    }

    public void setMotDePasse(String motDePasse){
        this.motDePasse=motDePasse;
    }

    public void setPrivilege(String privilege){
        this.privilege=privilege;
    }

    public void setAdresseLivraison(String adresseLivraison){
        this.adresseLivraison=adresseLivraison;
    }

    public void setCheminAvatar(String cheminAvatar){
        this.cheminAvatar=cheminAvatar;
    }

    
}