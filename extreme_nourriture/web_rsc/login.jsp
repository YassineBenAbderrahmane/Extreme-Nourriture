<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="db.DaoUtilisateur" %>
<%@ page import="bean.Utilisateur" %>

<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Authentification Xtrem Nourriture</title>
    <script>
      function validate(){
        var mail = document.form.mail.value;
        var password = document.form.password.value; 
        if (mail==null || mail==""){ 
          alert("Le mail est obligatoire"); 
          return false; 
        }else if(password==null || password==""){
          alert("Ce mot de passe est obligatoire");
          return false; 
        }
      }
    </script>
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
                      <a class="nav-link" href="Avis">Avis</a>
                  </li>
              </ul>
              <ul class="navbar-nav">
                  <li class="nav-item">
                      <a class="nav-link active" href="/extremenourriture/Login">Espace Client</a>
                  </li>
              </ul>
          </div>
      </div>
  </nav>
    <%
      String mail = request.getParameter("mail");
      String password = request.getParameter("password");
      if(mail!=null && password!=null){
        if (DaoUtilisateur.utilisateurExiste(mail)){
          if (DaoUtilisateur.verificationMotDePasse(mail,password)){
            session.setAttribute("mail",mail);
            session.setAttribute("password",password);
            
          }else{
          %>
            <p>Le mot de passe est invalide</p>
          <%}
        }else{
          %>
          <p>L'utilisateur n'existe pas</p>
          <%
        }
      }
      if (session.getAttribute("mail") != null){
          %>
          <p>Vous êtes connecté en tant que <%= session.getAttribute( "mail" ) %></p>
          <a href="/extremenourriture/Accueil">Retour à L'Accueil</a>
          <a href="/extremenourriture/Logout">Déconnexion</a>
          <%}else{ %>
    <hr><form name="form" method="POST" action="/extremenourriture/Login" onsubmit="return validate()">
      <p> Un simple visiteur ne pourra pas ajouter des produits dans le panier ni laisser un avis :( Vous devez être connecté en tant que client </p>
      Email : <input type="text" name="mail" id="mail"><br/><br/>
      Mot de passe : <input type="password" name="password"><br/>
      <input type="submit" value="login">
    </form><hr/>
    <a href="/extremenourriture/Register">Inscription</a>
    <% }%>
  </body>
</html>


