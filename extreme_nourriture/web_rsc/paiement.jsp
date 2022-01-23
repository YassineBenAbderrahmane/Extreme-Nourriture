<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="db.*" %>
<%@ page language="java" import="bean.*" %>
<!DOCTYPE html>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Paiement</title>
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
                            <a class="nav-link active" href="/extremenourriture/Panier">Panier</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Avis</a>
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
        <jsp:useBean id="panier" class="bean.Panier" scope="session"/>
        <%
            float prix = panier.getPrix();
            if (prix!= 0){
        %>
            <p>Vous devez payer <%= prix %> euro</p>
        <%   
            }else{
        %>
            <p>Pas de produits</p>
        <% 
            }
        %>


        

        <div id="app" >
            <form method="GET" action="/extremenourriture/Paiement" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
                <hr>
                <label>A emporter/Livraison :</label>
                <select type='text' value='1'  v-model="champecommande">
                    <option value='1'>A emporter</option>
                    <option value='2'>Livraison</option>
                </select><br/><br/>
                <label v-if="checkcommande()" >Saisir votre addresse postale: </label>
                <input type='text'  v-model="champcommande_cache" v-if="checkcommande()" size='50'/><br/><br/>
                <p v-if="champ_cache_pour_commande">Vous devez remplir le champ</p>
                <hr/>

                <label >Numéro de carte bancaire : </label><input type='text'  v-model="champnumero" size='50'/><br/><br/>
                <p v-if="champ_cache_numeroint">Le numéro doit être composé uniquement de chiffres</p>
                <p v-if="champ_cache_numerolen">Le numéro de carte n'est pas composé de  16 caractères</p>
                <label>Card Expiration :</label>
                <select type='text'  v-model="champexpireM">
                    <option value=''>Month</option>
                    <option value='01'>January</option> 
                    <option value='02'>February</option>
                    <option value='03'>March</option>
                    <option value='04'>April</option>
                    <option value='05'>May</option>
                    <option value='06'>June</option>
                    <option value='07'>July</option>
                    <option value='08'>August</option>
                    <option value='09'>September</option>
                    <option value='10'>October</option>
                    <option value='11'>November</option>
                    <option value='12'>December</option> 
                </select>
                <select type='text'  v-model="champexpireA">
                    <option value=''>Year</option>
                    <!-- boucle for ici si tu veux mettre plus d'année-->
                    <option value='21'>2021</option>
                    <option value='22'>2022</option>
                    <option value='23'>2023</option>
                    <option value='24'>2024</option>
                    <option value='25'>2025</option>
                </select><br/><br/>
                <p v-if="champ_cache_expire">Vous avez oublié de choisir une date d'expiration</p>    
                <label>Code de vérification: </label><input type='text'  v-model="champcode" size='50'/><br/><br/>
                <p v-if="champ_cache_codeint">Le code doit être composé uniquement de chiffres</p>
                <p v-if="champ_cache_codelen">Le code n'est pas composé de 3 caractères</p>
                <button v-on:click="soumettreFormulaire()" type="button">Envoyer</button> 
                <p v-if="champ_cache_succes">Paiement passé avec succès,vous serez redirigé vers la page d'accueil dans 3 secondes </p>
                <!-- <button type="button">Envoyer</button> -->
            </form><hr/>
        </div>
        <script>
             const app = new Vue({
				el:"#app",
                data: { champecommande: "",
                        champnumero: "",
                        champexpireM:"",
                        champexpireA:"",
                        champcode:"",
                        champcommande_cache:"",
                        champs: [],
                        champ_cache_numeroint: false,
                        champ_cache_numerolen: false,
                        champ_cache_succes: false,
                        champ_cache_codeint :false,
                        champ_cache_codelen : false,
                        champ_cache_expire : false,
                        champ_cache_commande: false,
                        champ_cache_pour_commande: false,
                        
                },
                methods: {
                    soumettreFormulaire: async function() { 
                        this.champs.push({
                            "numero": this.champnumero,
                            "expireM" : this.champexpireM,
                            "expireA" : this.champexpireA,
                            "code": this.champcode,
                            "typeCommande":this.champecommande,
                            "adresse":this.champcommande_cache,

                        })
                        checknumero=this.checknumero();
                        checkcode=this.checkcode();
                        checkcard=this.checkcard();
                        checkcomm=this.checkchampcommande_cache();

                        if (checknumero && checkcode && checkcard &&  checkcomm){
                        this.enregistrerEnBD(this.champs[this.champs.length-1]);
                        this.passerfalse();
                        this.champ_cache_succes=true;
                        const sleep = (waitTimeInMs) => new Promise(resolve => setTimeout(resolve, waitTimeInMs));
                        await sleep(3000);
                        window.location.href = 'http://localhost:8080/extremenourriture/Accueil';
                        }else{
                            this.champ_cache_succes=false;
                        }
                        
                    },
                    checknumero: function(){
                        if (!Number.isInteger(parseInt(this.champnumero))){
                            this.champ_cache_numeroint=true;
                            return false
                        }else if(this.champnumero.length !=16 ){
                            this.champ_cache_numerolen=true;
                            return false
                        }
                        return true
                        
                    },
                    checkcode: function(){
                        if (!Number.isInteger(parseInt(this.champcode))){
                            this.champ_cache_codeint=true;
                            return false
                        }else if(this.champcode.length !=3 ){
                            this.champ_cache_codelen=true;
                            return false
                        }
                        return true
                        
                    },
                    checkcard: function(){
                        if (this.champexpireM=="" || this.champexpireA=="" ){
                            this.champ_cache_expire=true;
                            return false
                        }
                        return true
                        
                    },
                    passerfalse: function(){
                        this.champ_cache_numeroint = false;
                        this.champ_cache_numerolen = false;
                        this.champ_cache_codeint = false;
                        this.champ_cache_codelen = false;
                        this.champ_cache_expire= false;
                        this.champ_cache_pour_commande= false;
                    },
                    checkcommande : function(){
                        return this.champecommande==2
                    },
                    enregistrerEnBD: function(champs) {
                        $.ajax ({
                            type:'POST',
                            url:'/extremenourriture/EnregistrerCommande',
                            data:champs,
                        });
				    },
                    checkchampcommande_cache: function() {
                        if (this.checkcommande()){
                            if (this.champcommande_cache==""){
                                this.champ_cache_pour_commande=true;
                                return false
                            }
                            return true
                        }
                        return true
                    }

                }
            })
        </script>

    </body>  
</html>  