    <%@ page contentType="text/html; charset=utf-8"%>
    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="java.util.*" %>
    <title>Administration: Page d'ajout de catégorie</title>

    <h1 class="blog-post-title">Page d'administration: Ajout d'une catégorie</h1>

        <form name="formulaire" action="" method="POST">
                <fieldset>
                    <legend>Attributs :</legend>
                    <label for="nom">Nom: </label><input placeholder="Saisissez le nom"  id="nom" name="nom" type="text" size="30" /><br /><br/>
                    <label for="description">Description: </label><input placeholder="Saisissez la description"  id="description" name="description" type="text" size="30" /><br /><br/>
                </fieldset>
                <button id="AjoutCategorie" name="AjoutCategorie" value="True">Ajouter catégorie </button>
        </form>
</body>