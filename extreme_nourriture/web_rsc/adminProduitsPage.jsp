    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="java.util.*" %>
    <%@ page contentType="text/html; charset=utf-8"%>


    <% ArrayList<Categorie> listeCategories = DaoCategorie.getCategories(); 
        ArrayList<Produit> listeProduits;
    %>   
    <title>Administration des produits Xtrem Nourriture</title>
    <h1>Tableau des produits par catégories: </h1>
    <%  for(Categorie cat : listeCategories){
    %>
        <table class="table table-striped table-bordered" cellspacing="0" id="categorie" width="100%">
            <h4>Tableau des produits de la catégorie: <%=cat.getNom()%></h4>
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Produit</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%listeProduits = DaoProduit.getProduitsCategorie(cat.getId());
                    for(Produit prod : listeProduits){
                %>
                <tr>
                    <td><%if(prod.getCheminImage() == null){%>
                        <%=prod.getCheminImage()%>
                    <%
                    }else{
                    %>
                        <img width="200" height="150" src="<%=prod.getCheminImage()%>"
                    <%
                    }
                    %></td>
                    <td><%=prod.getNom()%></td>
                    <td><%=prod.getDescription()%></td>
                    <td><%=prod.getPrix()%></td>
                    <td><form action="" method="POST">
                        <button class="btn btn-danger" id="idSupprProd" name="idSupprProd" value="<%=prod.getIdProduit()%>">Supprimer</button>
                    </form>
                    </td>
                </tr>
                <%
                }
                %>
            </tbody>
    <%
    }
    %>
</body>

