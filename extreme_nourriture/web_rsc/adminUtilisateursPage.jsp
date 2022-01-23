    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="java.util.*" %>
    <%@ page contentType="text/html; charset=utf-8"%>


    <%ArrayList<Utilisateur> listeUtilisateurs = DaoUtilisateur.getUtilisateurs();%>



    <title>Administration des utilisateurs Xtrem Nourriture</title>
    <h1 class="blog-post-title">Page d'administration des utilisateurs:</h1>
    <table class="table table-striped table-bordered" cellspacing="0" id="utilisateur" width="100%">
        <thead>
            <tr>
                <th>id</th>
                <th>Prenom</th>
                <th>Nom</th>
                <th>Mail</th>
                <th>Sexe</th>
                <th>Adresse livraison</th>
                <th>Privilege</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%for(Utilisateur user : listeUtilisateurs){
            %>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getPrenom()%></td>
                    <td><%=user.getNom()%></td>
                    <td><%=user.getMail()%></td>
                    <td><%=user.getSexe()%></td>
                    <td><%=user.getAdresseLivraison()%></td>
                    <td><%=user.getPrivilege()%></td>
                    <td>
                        <form action="" method="POST">
                            <button class="btn btn-danger" id="idSupprUser" name="idSupprUser" value="<%=user.getId()%>">Supprimer</button>
                    </td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
</body>
