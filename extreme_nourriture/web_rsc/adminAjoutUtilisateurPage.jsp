    <%@ page contentType="text/html; charset=utf-8"%>

    <h1 class="blog-post-title">Page d'administration: Ajout des utilisateurs</h1>
    <title>Administration ajout d'utilisateur Xtrem Nourriture</title>

    <form name="formulaire" action="" method="POST">
            <fieldset>
                <legend>Coordonnées :</legend>
                <label for="mail">E-mail: </label><input placeholder="Saisissez votre mail" id="mail" name="mail" type="text" size="30" /><br/><br/>
                <label for="mdp">Mot de passe: </label><input placeholder="Saisissez votre mot de passe"  id="mdp" name="mdp" type="password" size="30" /><br /><br/>
                <label for="prenom">Prenom: </label><input placeholder="Saisissez votre prenom"  id="prenom" name="prenom" type="text" size="30" /><br /><br/>
                <label for="nom">Nom: </label><input placeholder="Saisissez votre nom"  id="nom" name="nom" type="text" size="30" /><br /><br/>
                <label for="adresse">Adresse: </label><input placeholder="Saisissez votre adresse"  id="adresse" name="adresse" type="text" size="30" /><br /><br/>
                <label for="sexe">Sexe :</label>
                <select type='text' id="sexe" name="sexe">
                    <option>M</option>  
                    <option>F</option>   
                </select><br/><br/>
                <label for="privilege">Privilege: </label>
                <select type='text' id="privilege" name="privilege">
                    <option selected>client</option>  
                    <option>admin</option>   
                </select><br/><br/>
            </fieldset>
            <button id="AjoutUser" name="AjoutUser" value="True">Ajouter utilisateur</button>
        </div>
    </form>
</body>