    <%@ page contentType="text/html; charset=utf-8"%>
    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="java.util.*" %>

    <%ArrayList<Categorie> listeCategories = DaoCategorie.getCategories();%>
        
    <title>Administration: Page d'ajout de produit</title>

    <h1 class="blog-post-title">Page d'administration: Ajout d'un produit</h1>

    <form name="formulaire" action="" method="POST">
            <fieldset>
                <legend>Attributs :</legend>
                <label for="idCategorie">Id Categorie: </label>
                <select type='text' id="idCategorie" name="idCategorie">
                    <% for(Categorie cat : listeCategories){ %>
                        <option selected value= <%=cat.getId()%>><%=cat.getId()%>(<%=cat.getNom()%>)</option>
                    <%
                    }
                    %>
                </select><br/><br/>
                <label for="prix">Prix: </label><input placeholder="Saisissez le prix"  id="prix" name="prix" type="text" size="30" /><br /><br/>
                <label for="nom">Nom: </label><input placeholder="Saisissez le nom"  id="nom" name="nom" type="text" size="30" /><br /><br/>
                <label for="description">Description: </label><input placeholder="Saisissez la description"  id="description" name="description" type="text" size="30" /><br /><br/>
                <label for="cheminImage">Chemin Image: </label><input placeholder="Saisissez le chemin vers l'image"  id="cheminImage" name="cheminImage" type="text" size="30" /><br /><br/>
            </fieldset>
            <button id="AjoutProduit" name="AjoutProduit" value="True">Ajouter produit</button>
    </form>
</body>