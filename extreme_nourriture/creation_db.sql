DROP TABLE IF EXISTS UTILISATEURS;

CREATE TABLE UTILISATEURS(
    id INTEGER PRIMARY KEY,
    prenom varchar(255),
    nom varchar(255),
    mail varchar(255) UNIQUE,
    sexe varchar(255),
    motDePasse varchar(255),
    adresseLivraison varchar(255),
    privilege varchar(255),
    cheminAvatar varchar(255)
);

INSERT INTO UTILISATEURS
    VALUES (1,'Rémy','Fneich','remy.fneich@insa-rouen.fr','H','admin123','450 Rue des Cateliers blabla', 'admin',NULL);

DROP TABLE IF EXISTS CATEGORIES;

CREATE TABLE CATEGORIES(
    id INTEGER PRIMARY KEY,
    nom varchar(255) UNIQUE,
    description varchar(255)
);

INSERT INTO CATEGORIES
    VALUES(1,'Les Pizzas','Un mets rond dans une boîte carrée qui se consomme en part triangulaire');
INSERT INTO CATEGORIES
    VALUES(2,'Tex-Mex','Parce que la gastronomie américaine ça existe pas');
INSERT INTO CATEGORIES
    VALUES(3,'Les Frites','Petit morceau allongé de pomme de terre.');
INSERT INTO CATEGORIES
    VALUES(4,'Dessert','Comme une entrée, mais à la fin.');



DROP TABLE IF EXISTS PRODUITS;

CREATE TABLE PRODUITS(
    idProduit INTEGER PRIMARY KEY,
    idCategorie INTEGER,
    nom varchar(255),
    prix FLOAT,
    descr varchar(255),
    cheminImage varchar(255),
    FOREIGN KEY(idCategorie) REFERENCES CATEGORIES(id) ON DELETE CASCADE,
    CONSTRAINT e UNIQUE(idCategorie,nom)
);

INSERT INTO PRODUITS
    VALUES(1,1,'Pizza Chicken Barbecue',9.50,'Sauce barbecue, mozzarella, filet de poulet rôti, oignons rouges frais, champignons frais et poivrons verts frais','/extremenourriture/web_rsc/img/pizza_chicken_barbecue.jpg');
INSERT INTO PRODUITS
    VALUES(2,1,'Pizza 4 Fromages',10.50,'Sauce tomate à l''origan ou crème fraîche légère, mozzarella, fromage de chèvre, emmental et Fourme d''Ambert AOP','/extremenourriture/web_rsc/img/pizza_4_fromages.jpg');
    
INSERT INTO PRODUITS
    VALUES(3,1,'Pizza BPM',10.50,'Sauce barbecue, mozzarella, haché au bœuf, filet de poulet rôti et merguez','/extremenourriture/web_rsc/img/pizza_bpm.jpg');
INSERT INTO PRODUITS
    VALUES(4,1,'Pizza Queen',10,'Sauce tomate à l''origan, mozzarella, jambon et champignons frais','/extremenourriture/web_rsc/img/pizza_queen.jpg');
INSERT INTO PRODUITS
    VALUES(5,1,'Pizza Suprême',10,'Sauce tomate à l''origan, mozzarella, haché au bœuf, saucisse pepperoni, champignons frais, oignons rouges frais et poivrons verts frais','/extremenourriture/web_rsc/img/pizza_supreme.jpg');
INSERT INTO PRODUITS
    VALUES(6,1,'Pizza Montagnarde',10,'Crème fraîche légère, mozzarella, jambon cru, fromage à raclette et champignons frais','/extremenourriture/web_rsc/img/pizza_montagnard.jpg');
INSERT INTO PRODUITS
    VALUES(7,1,'Pizza Raclette',15,'Crème fraîche légère, mozzarella, pommes de terre, lardons et fromage à raclette','/extremenourriture/web_rsc/img/pizza_raclette.jpg');
INSERT INTO PRODUITS
    VALUES(8,1,'Pizza Chèvre Miel',15,'Crème fraîche légère, mozzarella, fromage de chèvre, miel','/extremenourriture/web_rsc/img/pizza_chevre_miel.jpeg');
INSERT INTO PRODUITS
    VALUES(9,1,'Pizza Orientale',15,'Sauce tomate à l''origan, mozzarella, merguez et champignons frais','/extremenourriture/web_rsc/img/pizza_orientale.jpg');
INSERT INTO PRODUITS
    VALUES(10,1,'Pizza Provençale',15,'Sauce tomate à l''origan, mozzarella, thon, tomates fraîches, oignons rouges frais et olives noires','/extremenourriture/web_rsc/img/pizza_provencale.png');
INSERT INTO PRODUITS
    VALUES(11,1,'Pizza Margherita',15,'Sauce tomate à l''origan et mozzarella','/extremenourriture/web_rsc/img/pizza_margherita.jpeg');
INSERT INTO PRODUITS
    VALUES(12,1,'Pizza Chicken Parmesan',15,'Sauce tomate à l''origan, mozzarella, filet de poulet rôti, tomates fraîches, parmigiano reggiano AOP','/extremenourriture/web_rsc/img/pizza_chicken_parmesan.jpeg');
INSERT INTO PRODUITS
    VALUES(13,1,'Pizza Kasbah',15,'Sauce tomate à l''origan, mozzarella, émincé de poulet kebab, tomates fraîches, oignons rouges frais et sauce blanche kebab','/extremenourriture/web_rsc/img/pizza_kasbah.jpg');
INSERT INTO PRODUITS
    VALUES(15,2,'BIG BOX',15,'2 tenders | 5 nuggets | 5 jalapeno & cheese nuggets','/extremenourriture/web_rsc/img/big_box.jpg');
INSERT INTO PRODUITS
    VALUES(16,3,'Frites',3,'Petite Barquette','/extremenourriture/web_rsc/img/frite.png');
INSERT INTO PRODUITS
    VALUES(17,3,'Grands Frites',5,'Grande Barquette','/extremenourriture/web_rsc/img/grande_frite.jpg');
INSERT INTO PRODUITS
    VALUES(18,4,'Tiramisu',4,'Goût Chocolat noisette','/extremenourriture/web_rsc/img/tiramisu.gif');
INSERT INTO PRODUITS
    VALUES(19,4,'Crêpe',3,'Goût Chocolat noisette','/extremenourriture/web_rsc/img/crepe.jpg');
INSERT INTO PRODUITS
    VALUES(20,4,'Boisson',2,'Eau Minerale | Ice Tea | Coca Cola','/extremenourriture/web_rsc/img/boisson.jpeg');

DROP TABLE IF EXISTS COMMANDES;

CREATE TABLE COMMANDES(
    idCommande INTEGER PRIMARY KEY,
    idUser INTEGER,
    prix INTEGER CHECK (prix>=0),
    adresseLivraison varchar(255),
    etat varchar(255),
    FOREIGN KEY(idUser) REFERENCES UTILISATEURS(id) ON DELETE CASCADE
);

INSERT INTO COMMANDES
    VALUES(1,1,9,'450 Rue des Cateliers','En attente de livraison');

DROP TABLE IF EXISTS QUANTITES;


CREATE TABLE QUANTITES(
    idQuantite INTEGER PRIMARY KEY,
    idCommande INTEGER,
    idProduit INTEGER REFERENCES PRODUITS(idProduit) ON DELETE CASCADE,
    quantite INTEGER CHECK (quantite>0),
    FOREIGN KEY(idCommande) REFERENCES COMMANDES(idCommande) ON DELETE CASCADE,
    FOREIGN KEY(idProduit) REFERENCES PRODUITS(idProduit) ON DELETE CASCADE
);

INSERT INTO QUANTITES
    VALUES(1,1,1,1);
    
CREATE TABLE COMMENTAIRES(
	idCommentaire INTEGER PRIMARY KEY,
	idUser INTEGER,
	note INTEGER,
	dateCommentaire varchar(255),
	texte varchar(255),
	FOREIGN KEY(idUser) REFERENCES UTILISATEURS(id) ON DELETE CASCADE
);







