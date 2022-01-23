<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
    <title>Deconnexion Xtrem Nourriture</title>
    <%session.invalidate();%>
    <p>Vous êtes maintenant déconnecté .</p>
</body>
</html>