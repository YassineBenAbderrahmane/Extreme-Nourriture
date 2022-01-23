    <%@ page language="java" import="db.*" %>
    <%@ page language="java" import="bean.*" %>
    <%@ page language="java" import="bean.Commande" %>
    <%@ page language="java" import="java.util.*" %>
    <%@ page contentType="text/html; charset=utf-8"%>

    <%  ArrayList<Commande> listeCommandes = DaoCommande.getCommandes();
        Utilisateur user;
    %>
    <title>Administration des commandes Xtrem Nourriture</title>
    <h1 class="blog-post-title">Pages d'administration des commandes en cours:</h1>
    <table class="table table-striped table-bordered" cellspacing="0" id="commande" width="100%">
        <thead>
            <tr>
                <th>idCommande</th>
                <th>idClient</th>
                <th>Produit</th>
                <th>Prix</th>
                <th>Adresse Livraison</th>
                <th>Etat</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%for(Commande commande : listeCommandes){
                user = DaoUtilisateur.getUtilisateur(commande.getIdUser());
            %>
                <tr>
                    <td><%=commande.getIdCommande()%></td>
                    <td><%=commande.getIdUser()%> (<%=user.getPrenom()%> <%=user.getNom()%>)</td>
                    <td><%for(Produit produit : commande.getProduits()){%>
                        -<%=produit.getNom()%></br>
                        <%
                        }
                        %>
                    </td>
                    <td><%=commande.getPrix()%></td>
                    <td><%=commande.getAdresseLivraison()%></td>
                    <td><%=commande.getEtat()%></td>
                    <td>
                        <form action="" method="POST">
                            <button class="btn btn-danger" id="idSupprCommande" name="idSupprCommande" value="<%=commande.getIdCommande()%>">Supprimer</button>
                        </form>
                    </td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
</body>
