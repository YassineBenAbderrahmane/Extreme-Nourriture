    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="java.util.*" %>
    <%@ page contentType="text/html; charset=utf-8"%>

    <%ArrayList<Categorie> listeCategories = DaoCategorie.getCategories();%>
    <title>Administration des categories Xtrem Nourriture</title>
    <h1 class="blog-post-title">Page d'administration des Catégories:</h1>
    <table class="table table-striped table-bordered" cellspacing="0" id="utilisateur" width="100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Nom</th>
                <th>Description</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%for(Categorie categorie : listeCategories){
            %>
                <tr>
                    <td><%=categorie.getId()%></td>
                    <td><%=categorie.getNom()%></td>
                    <td><%=categorie.getDescription()%></td>
                    <td>
                        <form action="" method="POST">
                            <button class="btn btn-danger" id="idSupprCategorie" name="idSupprCategorie" value="<%=categorie.getId()%>">Supprimer</button>
                        </form>
                    </td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
</body>