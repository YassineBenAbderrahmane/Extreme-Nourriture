<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="db.*" %>
<%@ page language="java" import="bean.*" %>
<%@ page contentType="text/html; charset=utf-8"%>

<%String idCategorie = (String)request.getParameter("idCategorie");%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Votre Commande </title>
  </head>
  <body>
    <title>Panier Xtrem Nourriture</title>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
          <a class="navbar-brand" href="Accueil">XTREM NOURRITURE</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                      <a class="nav-link" href="/extremenourriture/Accueil">Accueil</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="/extremenourriture/Menu">Menu</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active" href="#">Panier</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="/extremenourriture/Avis">Avis</a>
                  </li>
              </ul>
              <ul class="navbar-nav">
                  <li class="nav-item">
                      <a class="nav-link" href="/extremenourriture/Login">Espace Client</a>
                  </li>
              </ul>
          </div>
      </div>
  </nav>
  <jsp:useBean id="panier" class="bean.Panier" scope="session"/>
  
  
    <p> Vous avez <%=panier.getProduits().size()%> Articles : </p>
    

    <%
        if(!panier.getProduits().isEmpty()) {
            for(Produit prod : panier.getProduits()) {
    %>
        <table border="1pt">
        
            <tr>
        <td><pre><%= ((Produit)prod).getNom() %></pre>  
            <% if (idCategorie != null){%>
                <!-- On envoie l'idCategorie + nom du produit pour le supprimer ensuite -->
                <a href="Panier?nomProduitSuppr=<%=prod.getNom()%>&idCategorie=<%=idCategorie%>">Supprimer ce Produit</a></td> 
            <%
            }else{
            %>
                <a href="Panier?nomProduitSuppr=<%=prod.getNom()%>">Supprimer ce Produit</a></td>
            <%
            }
            %>
    </td>
          <td><pre><%= ((Produit)prod).getPrix() %></pre></td>
        </tr>
        </table>	
    <% } %>

<p>Total : <%= panier.getPrix() %> euro</p>

<% }
else {%> 
<br><br>Pas de produits   
<% } %> 

<!-- On affiche le bouton paiement si et seulement si le panier n'est pas vide -->
<%
if(!panier.getProduits().isEmpty()) {    
%>

    <input type="button" id="Paiement" onclick= "window.location.href='/extremenourriture/Paiement';" value="Paiement" />

<%}%>
</body>
  <% if (idCategorie != null){
    %>
    <input type="button" onclick="window.location.href='Panier?ViderPanier=1&idCategorie=<%=idCategorie%>'" value="Vider le panier" />
    <%
    }else{%>
    <input type="button" onclick="window.location.href='Panier?ViderPanier=1'" value="Vider le panier" />
    <%
    }
    %>

  <% if (idCategorie != null){
    %>
    </br>

    
    <!-- On Retourne vers le Menu du même catégorei que le produit (supprimé/ajouté) c'est pour ça on vérifie à chaque fois si la catégorie est null ou pas  -->

    <a href="Menu?id=<%=idCategorie%>">Retourner au menu</a>
    <%
    }
    %>
</html>

