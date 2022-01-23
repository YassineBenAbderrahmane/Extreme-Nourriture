<%@ page language="java" import="db.*" %>
<%@ page language="java" import="bean.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8"%>

<%  ArrayList<Commentaire> listeCommentaires = DaoCommentaire.getCommentaires();
    Utilisateur user;
%>
<title>Administration des avis Xtrem Nourriture</title>
<h1 class="blog-post-title">Pages d'administration des avis:</h1>
<table class="table table-striped table-bordered" cellspacing="0" id="commande" width="100%">
    <thead>
        <tr>
            <th>Mail</th>
            <th>Prenom/Nom</th>
            <th>Date</th>
            <th>Note</th>
            <th>Commentaire</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%for(Commentaire commentaire : listeCommentaires){
            user = DaoUtilisateur.getUtilisateur(commentaire.getIdUtilisateur());
        %>
            <tr>
                <td><%=user.getMail()%></td>
                <td><%=user.getPrenom()%> <%=user.getNom()%></td>
                <td><%=commentaire.getDate()%></td>
                <td><%=commentaire.getNote()%></td>
                <td><%=commentaire.getTexte()%></td>
                <td>
                    <form action="" method="POST">
                        <button class="btn btn-danger" id="idSupprCommentaire" name="idSupprCommentaire" value="<%=commentaire.getIdCommentaire()%>">Supprimer</button>
                    </form>
                </td>
            </tr>
        <%
        }
        %>
    </tbody>
</table>
</body>
