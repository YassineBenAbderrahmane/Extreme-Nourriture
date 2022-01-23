<%@ page contentType="text/html; charset=utf-8" %>


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
        <title>Inscription Xtrem Nourriture</title>
        <div id="app">
            <hr><form method="POST" action="register.jsp" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
                <label>Email : </label><input type='text'  v-model="champemail" size='50'/><br/>
                <p v-if="verifieChampemail()">Vous devez remplir le champ</p>
                <label>Prenom : </label><input type='text'  v-model="champprenom" size='50'/><br/>
                <p v-if="verifieChampprenom()">Vous devez remplir le champ</p>
                <label>Nom : </label><input type='text'  v-model="champnom" size='50'/><br/>
                <p v-if="verifieChampnom()">Vous devez remplir le champ</p>
                <label>Sexe :</label>
                <select type='text'  v-model="champsexe">
                    <option>M</option>  
                    <option>F</option>   
                    </select><br/>
                <p v-if="verifieChampsexe()">Vous devez choisir un sexe</p>
                <label>Adresse : </label><input type='text'  v-model="champadresse" size='50'/><br/>
                <p v-if="verifieChampadresse()">Vous devez remplir le champ</p>
                <label>Password : </label><input type='password'  v-model="champpassword" size='50'/>
                <p v-if="verifieChamppass()">Le mot de passe doit contenir au moins 6 caractères</p>
                <button v-on:click="soumettreFormulaire()" type="button" v-if="verifFormulaire()">Envoyer</button>
            </form><hr/>
            <utilisateurexiste  v-bind:texte="texte"></utilisateurexiste>
        </div>
    <script>

        Vue.component("utilisateurexiste",{
            template: `<tr>
                        <td>{{ texte }}</td>
                        </tr>`,
            props: ["texte"]
        });



        const app = new Vue({
				el:"#app",
                data: { champemail: "",
                        champpassword:"",
                        champprenom:"",
                        champnom:"",
                        champadresse:"",
                        champsexe:"",
                        champs: [],
                        texte: "",
                },
                methods: {
					soumettreFormulaire:  function() {
                        
                        
                        this.champs.push({
                            "email": this.champemail,
                            "pass" : this.champpassword,
                            "prenom" : this.champprenom,
                            "nom": this.champnom,
                            "sexe": this.champsexe,
                            "adresse":this.champadresse,
                            
                        })
                        this.enregistrerEnBD(this.champs.at(this.champs.length-1));

                        this.champemail='';
                        this.champpassword='';
                        this.champprenom='';
                        this.champnom='';
                        this.champadresse='';
                        
                    },
                    verifFormulaire: function() {
                        return (this.champemail != '' && this.champpassword.length>=6 && this.champprenom!= '' && this.champnom != '' && this.champadresse!= '' && this.champsexe !='');
                    },
                    enregistrerEnBD:  function(champs) {
					$.ajax ({
						type:'POST',
						url:'/extremenourriture/RegisterServlet',
						data:champs,
                        success: function(received){
                            if (received=="true"){
                                app.texte="L'inscription est réussie, vous serez redirigé vers la page d'accueil dans 3 secondes";

                                window.setTimeout(function(){
                                    window.location.href = 'http://localhost:8080/extremenourriture/Accueil';
                                }, 3000);
                            }else{
                                app.texte="L'utilisateur existe déjà";

                            }
                            return received
                        },
                        error: function() {
                        alert('Problème AJAX');
                        }
					    });
				    },
                    verifieChamppass : function() {
                        return !(this.champpassword.length>=6);
                    },
                    verifieChampemail: function() {
                        return !(this.champemail != '');
                    },
                    verifieChampprenom: function() {
                        return !(this.champprenom!= '');
                    },
                    verifieChampnom: function() {
                        return !(this.champnom!= '');
                    },
                    verifieChampadresse: function() {
                        return !(this.champadresse!= '');
                    },
                    verifieChampsexe: function() {
                        return !( this.champsexe !='');
                    },
                }
        })
    </script>
    </body>
</html>    