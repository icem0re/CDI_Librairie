use Librairie

delete from LigneDeCommande
delete from Commande
DBCC CHECKIDENT ('Commande', RESEED, 0);
delete from Theme
delete from Invitation
delete from Genre
delete from Presentation
delete from Redaction
DELETE  FROM Auteur;
DBCC CHECKIDENT ('Auteur', RESEED, 0);
DELETE FROM Adresse;
DBCC CHECKIDENT ('Adresse', RESEED, 0);
delete from Client
DBCC CHECKIDENT ('Client', RESEED, 0);
delete from Livre
delete from TVA
delete from Livreur
DELETE FROM Editeur;
DELETE FROM Statut;
DELETE FROM Evenement;
DBCC CHECKIDENT ('Evenement', RESEED, 0);
delete from SousThematique
delete from Thematique

INSERT INTO Thematique ( nomThematique )

VALUES ( 'Romans & Fiction'), 
		('Esotérisme'),
		('Roman Policier'),
		('Fantastique'),
		('Santé & bien-être'),
		('Jeunesse'),
		('Religion & Spiritualité'),
		('Sciences humaine'),
		('Sciences & techniques'),
		('Loisir, vie pratique & société'),
		('Heroic-fantasy')



INSERT INTO SousThematique (idSousThematique,nomThematique, nomSousThematique, descriptionSousThematique)

VALUES ('SciFi','Romans & Fiction', 'Robotique', 'C''est une thématique sur le futur(science-fiction) concernant les robots...( si,si!)'),
		('Bou','Esotérisme', 'Bouddhisme', ' Cette rubrique concerne tout ce qui n''est pas sciences éxactes, particulièrement chez les bouddhistes'),
		('HoFa','Fantastique','Horreur-Fantastique',' De l''horreur mélangé a du fantastique: c''est pas beau et ca n''éxiste pas!'),
		('West','Romans & Fiction','Western','Le système du western repose essentiellement sur la notion américaine de frontier, que le mot français frontière traduit imparfaitement.'),
		('Bric','Loisir, vie pratique & société','Bricolage','Comment entretenir sa maison et prendre soin de soi sans s''empoisonner, ni polluer la planète, tout en faisant des économies ?'),
		('Cont','Jeunesse','Contes','l y a deux pratiques du genre littéraire qu''est le conte : orale et écrite. Ces deux pratiques se différenciant par leur mode de création et de diffusion comme par leur contenu, il convient de les distinguer.'),
		('Astr','Sciences & techniques','Astrologie','Vous voulez tout savoir du Cosmos et de l''univers connus ?'),
		('Psyc','Sciences humaine','Psychologie','Trouvez le tueur en série qui sommeil en vous !! ')


INSERT INTO Evenement (nomEvenement,descriptionEvenement,dateDebutEvenement,dateFinEvenement,lieuEvenement,reductionEvenement,codePromoEvenement,promoImmediatEvenement,commentaireEvenement)

VALUES( 'SALON DU LIVRE 2017', 'Grand public, professionnels du livre et amoureux de la lecture se donnent rendez-vous au Salon Livre Paris.','24/03/2017', '27/03/2017', 'Porte de Versailles', '10','GRATTEUR','','Il y a trop de monde à Paris!'),
	( 'LECTURE EN FETE', 'Evenement culturel autour de la lecture', '02/12/2017', '03/12/2017', 'Roquebrune-Cap-Martin', '95','CREVAR','','Il faut savoir lire vite!'),
	( '28EME SALON DU LIVRE', 'Le salon du livre à Colmar!', '25/11/2017', '26/11/2017', 'Colmar','5',NULL,NULL,NULL)



insert into Statut(code, info)

values
('cliAct','Client actif'),
('cliIna','Client inactif'),
('cliRP','Client en retard de paiement'),
('cliLiti','Client en litige'),
('comPrep','Commande en preparation'),
('comExp','Commande expediée'),
('comLiv','Commande livrée'),
('comConfP','Attente confirmation de paiement'),
('comVal','Commande validée'),
('comAnu','Commande annulée'),
('ediAct','Editeur actif'),
('ediIna','Editeur inactif'),
('ediPause','Editeur en pause dej'),
('cliSnap','Cliente chaude sur snapchat'),
('cliSka','Client squatteur en skatte'),
('comArr','Commande emballée a l''arrache'),
('comArg','Commande payée mais annuléé mais on garde l''argent !!'),
('LivER','Livreur en route'),
('LivP','Livreur perdu'),
('LivMPLF','Livreur mort pour la France !!!')



INSERT INTO Editeur ( nomEditeur, logoEditeur, statutEditeur)

VALUES ('Vintage Books','','EdiAct'),
		('Le livre de poche','','EdiPause'),
		('J''ai lu','','EdiAct'),
		('Gallimard','','EdiAct')



INSERT INTO Livreur (nomLivreur, fraisPortLivreur, delaiLivraisonLivreur,statutLivreur)

VALUES ('Chronopost','10',NULL,'LivER'),
		('DPD','12','1','LivP'),
		('DHL','8','2','LivMPLF')


insert into TVA(nomTVA, tauxTVA)

values
('TVA5%','5.5')



INSERT INTO Livre (isbnLivre, nomTVA, nomEditeur, titreLivre, sousTitreLivre, dateParutionLivre, resumeLivre, extraitLivre, imageLivre, prixHTLivre, poidLivre, affichageLivre)

VALUES('0375705106','TVA5%', 'Vintage Books',' Way of Zen','','24/07/1972', 'enseignements sur le zen', 'blablabla, blablabla', '', '3.99', '114', 'true'),
	('2811210210','TVA5%', 'J''ai lu',' Le cycle des démons T1','l''homme-rune','05/04/2017', '', 'Il y a parfois de très bonnes raisons d''avoir peur du noir. Dans le monde du jeune Arlen, dès que le soleil se couche, les démons sortent de terre et dévorent... ', '', '3.89', '122', NULL),
	('9782253083177 ','TVA5%', 'Le livre de poche','Revival','','01/02/2017','', 'Il a suffi de quelques jours au charismatique révérend Charles Jacobs pour ensorceler les habitants de Harlow, dans le Maine.', '', '8.90', '142', 'true'),
	('2963856479821','TVA5%', 'Le livre de poche','',NULL,'', '', '', '', '7.25', '114', 'false'),
	('2290317152','TVA5%', 'J''ai lu','Le robot qui rêvait','','01/09/2002', 'Nono est un petit robot, ami d''Ulysse!', 'Science-fiction Bienvenue dans le futur, ou plutôt dans une multitude de futurs ! Où un savant découvre un jour l''anti-gravité, où un autre pose au plus... ', NULL, '9.50', '154', 'true'),
	('2253111465','TVA5%','Le livre de poche','L''Arbre des possibles','','2002-10-01','Un recueil d’histoires courtes qui présentent toutes une hypothèse poussée à son extrême. Et s’il existait une école pour que les jeunes dieux apprennent à créer des sociétés humaines ? (L’école des jeunes dieux) Comment fonctionnerait un monde où les nombres seraient à la base de tous les savoirs ? (Le mystère des chiffres) Et si une météorite tombait sur le jardin du Luxembourg ? (Fragrance) Une main gauche peut-elle faire sécession ?','On détruit pour survivre. Alors que la violence des humains, je n''en comprend pas le sens. Peut-être parce que trop nombreux et destructeurs, il s''autorégulent en se tuant entre eux. Ou peut-être parce qu''ils s''ennuient. Depuis des siècles, nous ne vous intéressons que sous forme de bûches ou de pâte à papier. Nous ne sommes pas des objets. Comme tout ce qui est sur Terre, nous vivons, nous percevons ce qui se passe dans le monde, nous souffrons et nous avons nos petites joies à nous. J''aimerais parler avec vous. Un jour, nous discuterons peut-être ensemble. Le voulez-vous?','','6.30','260','true')



insert into Client(nomClient, prenomClient, loginClient, mdpClient, emailClient, dateNaissanceClient, statutClient, derniereConnexionClient)

values
('Gaillard','Jerome','WhYn0t','12345678','daddy4ever@gmail.com','1988-04-10','cliIna','2017-06-06T12:00:00'),
('LeBreton','Gregory','NickyLarson','00001111','OcculusUrMine@gUneGTX1080.over.clock.us','1982-04-24','cliSka','2017-05-23T22:14:00'),
('Bowie','David','MonCollonel','987654321','SecretDefense@phpLovers.com','1989-12-12',NULL,'2015-12-01T01:54:53'),
('Adjani','Isabelle','Zazza','01020304','jaimeLesLicornes@gmail.com','1986-07-12','cliLiti','2016-04-30T17:59:59')



insert into Adresse(nomAdresse, destinataireAdresse, typeVoie, numVoie, nomVoie, complement, codePostal, ville, pays)

values
('Adresse 1','Le Pape','Rue','13','de l''espoir perdue','666 étages','75666','Paris','France'),
('Adresse 2','Le Pape','Avenue','17','Henri IV','Voir Accueil','75002','Paris','France'),
('Adresse 3','Le Pape','Boulevard','30 bis','des Gauffres Belges','','69560','Lyon','France'),
('Adresse 1','Moi meme','Rue','21','Dieu','Batiment 4','75011','Paris','France'),
('Maman','Mme Pierette','impasse','2','des garriguettes','Maison bleue','13124','Marseille','France'),
('Acme inc.','Mr Pino','Boulevard','77','de l''avoine','','75015','Paris','France'),
('Adresse 1','Mr Valmont','Rue','1','du XV de de Rose','A gauche du Kebab','93120','St Denis','France'),
('Adresse 2','Mr Valmont','Avenue','39 ter','des Cigognes','Sonner a Sarah Croche','75012','Paris','France'),
('Adresse 1','Mlle Causette','Allée','5','de la Manche','pas de digicode','85180','Les Sables D''Olonnes','France'),
('Adresse 1','Emma Huss','Rue','22','des Dons en nature','Interphone 3b','75019','Paris','France'),
('Adresse 2','Emma Huss','Avenue','14','Du Marechal Pwent','','75001','Paris','France'),
('Adresse de Papa','Al Papaccino','Rue','65','des mandarines','code : 24b13','44200','Nantes','France'),
('Adresse 1','Yves & Jacques Ules','Boulevard','54','St Michel','Batiment : Les 2 melons','75014','Paris','France'),
('Adresse 2','Yves & Jacques Ules','Rue','42 bis','Du Kremlin','Laisser au gardien','75013','Paris','France'),
('Adresse 1','Mme Irma','Ruelle','5','du SpaceCake','','75003','Paris','France'),
('Bureau ','Mme Irma','Place','2','de la boule de crystal','Caravane arc-en-ciel','75004','Paris','France')




insert into Auteur(nomAuteur, prenomAuteur, dateNaissanceAuteur, dateDecesAuteur, nationaliteAuteur, photoAuteur, bioAuteur)

values
('Werber','Bernard','1961-09-18','','Français','','Il naît à Toulouse le 18 septembre 19612. Il fut tout d’abord un enfant inspiré par le dessin. À 5 ans, il veut devenir dessinateur professionnel. Encouragé par son professeur, il a le droit de rester à part des autres élèves pour dessiner de grandes aquarelles. Mais à 8 ans, il écrit, dans le cadre d''un travail scolaire, l''histoire d''une puce se promenant sur un corps humain, ce qui le fait dévier de son rêve original : dès l’âge de 13 ans, il fait ses premières armes en écrivant des histoires pour un fanzine, mais aussi tout au long de sa scolarité il se consacre à la lecture ainsi qu’à l’écriture en montant un journal pour son lycée qu’il nomme Euphorie.'),
('King','Stephen','1947-09-21','','Americain','','Les parents de Stephen King sont Donald Edwin King, né le 11 mars 1914 sous le nom de Donald Pollock, ancien capitaine de la marine marchande devenu représentant, et Nellie Ruth Pillsbury, née le 3 février 1913, pianiste. Ils se marient le 23 juillet 1939. Le 14 septembre 1945, le couple, qui pense ne pas pouvoir avoir d''enfant, adopte un nouveau-né, David Victor King.'),
('Hugo','Victor','1802-02-26','1885-05-22','Français','','Victor-Marie Hugo est le fils du général d''Empire Joseph Léopold Sigisbert Hugo (1773‑1828), créé comte, selon la tradition familiale, par Joseph Bonaparte, roi d''Espagne et en garnison dans le Doubs au moment de la naissance de son fils, et de Sophie Trébuchet (1772‑1821), jeune femme issue de la bourgeoisie nantaise (voir maison natale de Victor Hugo).'),
('Shakespeare','William','1564-04-23','1616-05-03','Anglais','','William Shakespeare naît à Stratford-upon-Avon dans le Warwickshire, au centre de l''Angleterre. Son acte de baptême date du 26 avril 1564. Vu la fréquente mortalité infantile, on baptisait jadis les nourrissons dès leur naissance, soit le jour-même, soit les jours suivants. On s’accorde à citer le 23 avril comme la date de naissance du dramaturge. Il est le troisième enfant de la famille et l''aîné des garçons.'),
('Wilde','Oscar','1854-10-16','1900-11-30','Irlande','','Né dans la bourgeoisie irlandaise et protestante de Dublin, d’un père ophtalmologiste renommé et d’une mère poétesse, Oscar Wilde se distingue par un parcours scolaire brillant. Nourri de culture classique, couronné de prix au sein du Trinity College de Dublin, il intègre le Magdalene College de l''université d''Oxford, où il se construit un personnage d’esthète et de dandy, sous l’influence des préraphaélites et des théories de L''art pour l''art de Walter Pater, John Ruskin ou Whistler.'),
('De Beauvoir','Simone','1908-01-09','1986-04-14','Française','','Elle voit le jour dans un appartement cossu du boulevard Raspail et entre à l''âge de cinq ans au Cours Desir1 où sont scolarisées les filles de '' bonnes familles ''. Sa sœur cadette, Hélène (dite Poupette), l''y rejoint deux ans plus tard. Dès le plus jeune âge, Simone se distingue par ses capacités intellectuelles et se partage chaque année la première place avec Élisabeth Lacoin (dite Élisabeth Mabille, ou '' Zaza '' dans son autobiographie). Zaza devient rapidement sa meilleure amie.'),
('Verne','Jules','1828-02-08','1905-03-24','Français','','Jules-Gabriel Verne3 naît au 4 de la rue Olivier de Clisson (actuel cours Olivier de Clisson) à l''angle de la rue Kervégan dans le quartier nantais de l''île Feydeau à Nantes, au domicile de sa grand-mère maternelle, Sophie Marie Adelaïde-Julienne Allotte de la Fuÿe (née Guillochet de La Perrière). Il est le fils de Pierre Verne, avoué, originaire de Provins, et de Sophie Allote de la Fuÿe, issue d''une famille nantaise de navigateurs et d''armateurs, d''ascendance écossaise.'),
('Beckett','Samuel','1906-04-13','1989-12-22','Irlandais','','Samuel Barclay Beckett naît le 13 avril 1906, jour du Vendredi Saint, dans une famille de la bourgeoisie protestante irlandaise, issue de huguenots français réfugiés en Irlande. La demeure familiale, Cooldrinagh, située dans une banlieue aisée de Dublin, Foxrock, est une vaste maison bourgeoise. Il est le deuxième fils de William Frank Beckett, métreur, et May Barclay Roe, infirmière.'),
('De Saint-Exupéry','Antoine','1900-06-29','1944-07-31','Français','','Fils du vicomte Martin Louis Marie Jean de Saint Exupéry (1863-1904), 37 ans et sans profession1, et de Andrée Marie Louise Boyer de Fonscolombe, 25 ans, Antoine Jean-Baptiste Marie Roger de Saint Exupéry naît le 29 juin 1900 au 8, rue du Peyrat, dans le 2e arrondissement de Lyon dans une famille issue de la noblesse française.'),
('Steel','Danielle','1947-08-14','','Americaine','','Avec près de 80 best-sellers publiés en France, plus d''un demi-milliard d''exemplaires vendus dans 69 pays et traduits en 43 langues, Danielle Steel est l''auteur contemporain le plus lu et le plus populaire au monde. Depuis 1981, ses romans figurent systématiquement en tête des meilleures ventes du New York Times. Elle est restée sur les listes des best-sellers pendant 390 semaines consécutives, ce qui lui vaut d''être citée dans le Livre Guinness des records. C''est en 2007 le 8e auteur le plus traduit dans le monde, derrière Barbara Cartland, mais devant Stephen King.'),
('Watts','Allan','1915-01-06','1973-11-16','Anglais','','Alan Watts, prêtre épiscopalien, philosophe, écrivain et conférencier, émigre aux États-Unis en 1938. Il est initié au bouddhisme zen rinzai en côtoyant Roshi Sokei-an Sasaki pendant trois ans, étudie la théologie et est ordonné prêtre en 1945, mais quitte la prêtrise en 1950 et rejoint la Californie, où il entre à l''Académie Américaine des Études Asiatiques – dont il sera brièvement le plus jeune doyen.'),
('Asimov','Isaac','1920-01-02','1992-04-06','Russe','','Issu d’une famille juive, fils de Judah Asimov et de Anna Rachel Berman, Isaac naquit à Petrovitchi — près de Smolensk, en Russie — à une date inconnue, entre le 4 octobre 1919 et le 2 janvier 1920 (c’est à cette date-ci qu''il célébrait son anniversaire, adulte). Pour des raisons mal définies et sur invitation de Joseph Berman, demi-frère de la mère d’Asimov, sa famille émigra aux États-Unis au début de l''année 1923, alors qu''il avait trois ans.'),
('V.Brett','Peter','1973-02-08','','Americain','','Peter V. Brett lit de la fantasy depuis son enfance. Il apprécie également les comics et les jeux de rôle. Il étudie la littérature anglaise et l''histoire de l''art à l''université de Buffalo et obtient son diplôme en 1995. Il travaille ensuite durant plus de dix ans dans le domaine de la publication pharmaceutique. En juin 2007, la maison d''édition Del Rey Books accepte d''éditer son quatrième roman : L''Homme-Rune. En octobre 2007, le succès international du livre permet à Peter de démissionner de son travail et de se consacrer pleinement à l''écriture de romans.')


insert into Redaction(idAuteur, isbnLivre)
values
('11','0375705106'),
('1','2253111465'),
('12','2290317152'),
('13','2811210210'),
('2','9782253083177')


insert into Presentation(isbnLivre, idEvenement)
values

('0375705106','1'),
('2253111465','1'),
('2290317152','1'),
('2290317152','2'),
('2253111465','2'),
('0375705106','2'),
('2253111465','3'),
('2811210210','3'),
('9782253083177','3'),
('2811210210','2'),
('2963856479821','3'),
('2963856479821','1'),
('9782253083177','1')


insert into Genre(idSousThematique,isbnLivre)
values

('Bou','0375705106'),
('HoFa','2811210210'),
('SciFi','2253111465'),
('HoFa','9782253083177'),
('SciFi','2290317152'),
('Psyc','2963856479821')

insert into Invitation(idAuteur, idEvenement)
values

('1','1'),
('10','1'),
('8','2'),
('2','2'),
('13','3')

insert into Theme(idSousThematique, idEvenement)
values
('Eso','1'),
('Fant','2'),
('SciFi','3')


insert into Commande(idClient, idAdresseFacturation, idAdresseLivraison, nomLivreur, dateCommande, datePaiementCommande, datePreparationCommande, dateExpeditionCommande, dateAccuseReceptionCommande, statutCommande, ipCommande, dateAnnulationCommande)
values

('1','10','11','DHL','2017-06-02T12:00:54','2017-06-02T12:01:34','2017-06-03T08:12:00','2017-06-04T10:02:59','','comArr','192.168.0.1',''),
('2','9','12','DPD','2017-05-29T23:25:01','2017-05-29T23:27:17','2017-06-01T09:42:01','2017-06-02T07:39:51','','comExp','192.168.0.2',''),
('3','13','14','Chronopost','2017-06-05T04:58:32',NULL,NULL,NULL,NULL,'comArg','192.168.0.3',''),
('4','15','16','DHL','2017-04-25T17:45:33','2017-04-25T17:46:13','2017-04-26T11:23:45','2017-04-26T18:41:22','2017-04-27T09:58:37','comLiv','192.168.0.4','')

INSERT INTO LigneDeCommande (numCommande,isbnLivre,quantiteLigneDeCommande,PrixUnitaireHTLigneDeCommande,TVALigneDeCommande,reductionLigneDeCommande)

VALUES('1','0375705106','2','24.99','5.5','0'),
	('2','2253111465','1','7.50','5.5','0'),
	('3','2290317152','1','10.9','5.5','5.0'),
	('4','9782253083177','2','21.75','5.5',NULL)

select * from Adresse
select * from Auteur
select * from Client
select * from Editeur
select * from Evenement
select * from Genre
select * from Livre
select * from Livreur
select * from Presentation
select * from Redaction
select * from SousThematique
select * from Statut
select * from Thematique
select * from TVA
select * from Commande
select * from Theme
select * from Invitation
select * from LigneDeCommande