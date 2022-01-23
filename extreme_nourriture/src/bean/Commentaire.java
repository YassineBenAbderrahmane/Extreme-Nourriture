package bean;

import java.io.*;
import java.text.*;
import java.util.*;

public class Commentaire implements Serializable {
  private int idCommentaire;
  private int idUtilisateur;
  private int note;
  private static SimpleDateFormat formatter;
  private Date date;
  private String texte;

  static{
    formatter = new SimpleDateFormat("E d MMM yyyy, HH:mm:ss", Locale.FRANCE);
  }

  public Commentaire() { 
    this.date = new Date();
    date.setTime(System.currentTimeMillis());
    this.texte = null;
    this.note = 0;
    this.idCommentaire = 0;
    this.idUtilisateur = 0;
  }

  public Commentaire(String texte, int note, int idCommentaire, int idUtilisateur){
    this.date = new Date();
    date.setTime(System.currentTimeMillis());
    this.texte = texte;
    this.note = note;
    this.idCommentaire = idCommentaire;
    this.idUtilisateur = idUtilisateur;
  }

  public String getDate(){ 
    return formatter.format(date);
  }

  public void setDate(String date) { 
    try{
      this.date = formatter.parse(date); 
    }catch(Exception e){
      System.out.println("Erreur lors de la maj de la date d'un commentaire: "+e);
      this.date = null;
    }
  }

  public void setTexte(String texte) { 
    this.texte=texte; 
  }

  public String getTexte() { 
    return texte; 
  }

  public void setNote(int note) { 
    this.note = note; 
  }

  public int getNote() { 
    return this.note; 
  }
  
  public void setIdUtilisateur(int idUtilisateur) { 
    this.idUtilisateur = idUtilisateur; 
  }

  public int getIdUtilisateur() { 
    return this.idUtilisateur; 
  }

  public void setIdCommentaire(int idCommentaire) { 
    this.idCommentaire = idCommentaire; 
  }

  public int getIdCommentaire() { 
    return this.idCommentaire; 
  }
}

