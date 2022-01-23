<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="db.DaoCommentaire" %>
<%@ page import="db.DaoUtilisateur" %>
<%@ page import="bean.Commentaire" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    </head>
    <body>
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
                            <a class="nav-link" href="/extremenourriture/Panier">Panier</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/extremenourriture/Avis">Avis</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link " href="/extremenourriture/Login">Espace Client</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <title>Avis</title>
        <%
        String mail = (String)session.getAttribute("mail");
        String pas = (String)session.getAttribute("password");
        if(mail != null && pas != null){
        %>
        <form action="/extremenourriture/Avis" method="POST" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
            <fieldset>
                <legend>Ajout d'un nouvel avis</legend>
                <label>Note :</label>
                <select type='text' name="note">
                    <option value="1">1/5</option>
                    <option value="2">2/5</option>
                    <option value="3">3/5</option>
                    <option value="4">4/5</option>
                    <option value="5" selected>5/5</option>
                </select><br/>
                <textarea name='texte' rows='10' cols='80' placeholder="Qu'avez-vous pensé de nos produits ?"></textarea><br/>
                <input type='submit' name='submit' value='Envoyer'/>
            </fieldset>
            <br/>
        <%
            String texte = request.getParameter("texte");
            int note = 0;
            if(request.getParameter("note") != null){
                note = Integer.parseInt(request.getParameter("note"));
            }
            int id = DaoUtilisateur.getUtilisateur(mail).getId();
            if(note != 0 && texte != null && !texte.equals("")){
                Commentaire new_commentaire = new Commentaire(texte,note,0,id);
                int res = DaoCommentaire.ajouterCommentaire(new_commentaire);
            }
        } else {
        %>
        <div>Il faut vous connecter en tant que client pour laisser un avis</div>
        <a href= "/extremenourriture/Login">Connexion</a>
        <%
        }
        ArrayList<Commentaire> lesCommentaires = DaoCommentaire.getCommentaires();
        %>
            <fieldset>
                <legend>Avis des clients</legend>
        <%
        if(lesCommentaires!=null && !lesCommentaires.isEmpty()) {
        %>
        <table class="table table-striped table-bordered" cellspacing="0" id="commentaires" width="100%">
        <thead>
            <tr>
                <th>Client(e)</th>
                <th>Date</th>
                <th>Note</th>
                <th>Commentaire</th>
            </tr>
        </thead>
        <%
            for(Object unCommentaire : lesCommentaires) {

        %>
                <tr>
                <td align = "left"><%=DaoUtilisateur.getUtilisateur(((Commentaire)unCommentaire).getIdUtilisateur()).getPrenom()%></td>
                <td align = "left"><%=((Commentaire)unCommentaire).getDate()%></td>
                <td align = "left"><%= ((Commentaire)unCommentaire).getNote()%>/5</td>
                <td align = "left"><%= ((Commentaire)unCommentaire).getTexte() %></td>
                </tr>
        <%
            }%>
            </table>
        <%
        } else {
        %>
            <div>Il n'y a pas encore d'avis, n'hésitez pas à en laisser un quand vous commanderez chez nous !</div>
        <% } %>
            </fieldset>
        </form>
    </body>  
</html>