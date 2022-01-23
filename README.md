# Extrême Nourriture

Projet TW2 2021-2020 par Damien GENS, Yassine BEN ABDERRAHMANE, Florin CROITORU et Rémy Fneich

### Comment utiliser le livrable
1)Placer wildfly-14.0.1.Final dans /tmp

1)Lancer le script bash: launch_serv.sh

2)Accéder à l'url localhost:8080 pour vérifier que le serveur est prêt

3)Lancer le second script bash quand le serveur est prêt: compile.sh

4)Créer une bd nommé test.db dans /tmp grâce à sqlite3 (Attention elle sera supprimer si l'ordinateur est éteint si elle reste dans /tmp)

5)Faire .read chemin/jusque/extreme-nourriture/extreme_nourriture/creation_db.sql afin de créer les tables

Si tout c'est bien déroulé le serveur et le site sont prêt. 

### ACCES SITE
1)Lancer le serveur comme expliqué dans la partie précédente

2)Utiliser votre navigateur pour aller sur la page suivante: http://localhost:8080/extremenourriture/Accueil pour y accéder

### ACCES PAGE ADMIN
1) Se connecter avec un compte admin => Par exemple: login: remy.fneich@insa-rouen.fr mdp: admin123

2) Aller à l'URL: http://localhost:8080/extremenourriture/Admin (Page caché)

### MODIF MANUEL DE LA BD
Si vous voulez modifier la BD n'oubliez pas que sqlite ne gère pas les foreign keyspar défaut, à chaque connexion il sera nécessaire d'entrer: PRAGMA foreign_keys = ON; avant d'effectuer une modification.
