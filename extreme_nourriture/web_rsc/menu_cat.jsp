<%@ page language="java" import="db.*" %>
<%@ page language="java" import="bean.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8"%>

<% ArrayList<Categorie> listeCategories = DaoCategorie.getCategories(); %>

<!doctype html><html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
<body>
    <title>Menu Xtrem Nourriture</title>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="Accueil">XTREM NOURRITURE</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="Accueil">Accueil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="Menu">Menu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Panier">Panier</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Avis">Avis</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="Login">Espace Client</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <table class="table table-striped table-bordered" cellspacing="0" id="categorie" width="100%">
        <thead>
            <tr>
                <th>Catégorie</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <%for(Categorie cat : listeCategories){
            %>
                <tr>
                    <td><a href="Menu?id=<%=cat.getId()%>"><%=cat.getNom()%></a></td>
                    <td><%=cat.getDescription()%></td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
    <jsp:useBean id="panier" class="bean.Panier" scope="session"/>
</body>