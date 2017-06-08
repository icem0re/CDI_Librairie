use Librairie


/* THEMATIQUE*/

INSERT INTO Thematique ( nomThematique )

VALUES ( 'Science-Fiction'), 
		('Esot�risme'),
		('Roman Policier'),
		('Fantastique'),
		('Heroic-fantasy')


/* SOUS THEMATIQUE*/

INSERT INTO SousThematique (nomThematique, nomSousThematique, descriptionSousThematique)

VALUES ('Science-Fiction', 'Robotique', 'C''est une th�matique sur le futur(science-fiction) concernant les robots...( si,si!)'),
		('Esot�risme', 'Bouddhisme', ' Cette rubrique concerne tout ce qui n''est pas sciences �xactes, particuli�rement chez les bouddhistes'),
		('Fantastique','Horreur-Fantastique',' De l''horreur m�lang� a du fantastique: c''est pas beau et ca n''�xiste pas!')


/* EVENEMENT*/

INSERT INTO Evenement (nomEvenement,descriptionEvenement,dateDebutEvenement,dateFinEvenement,lieuEvenement,reductionEvenement,codePromoEvenement,promoImmediatEvenement,commentaireEvenement)

VALUES( 'SALON DU LIVRE 2017', 'Grand public, professionnels du livre et amoureux de la lecture se donnent rendez-vous au Salon Livre Paris.','24/03/2017', '27/03/2017', 'Porte de Versailles', '10%','GRATTEUR','','Il y a trop de monde � Paris!'),
	( 'LECTURE EN FETE', 'Evenement culturel autour de la lecture', '02/12/2017', '03/12/2017', 'Roquebrune-Cap-Martin', NULL,'CREVAR','','Il faut savoir lire vite!'),
	( '28EME SALON DU LIVRE', 'Le salon du livre � Colmar!', '25/11/2017', '26/11/2017', 'Colmar', '5%',NULL,NULL,NULL)

/*EDITEUR*/

INSERT INTO Editeur ( nomEditeur, logoEditeur)

VALUES ('Vintage Books',''),
		('Le livre de poche',''),
		('J''ai lu','')


/*LIVREUR*/

INSERT INTO Livreur (nomLivreur, fraisPortLivreur, delaiLivraisonLivreur)

VALUES ('Chronopost','10�',NULL),
		('DPD','12�','1'),
		('DHL','8�','2')


/*LIVRE*/

INSERT INTO Livre (isbnLivre, nomTVA, nomEditeur, titreLivre, sousTitreLivre, dateParutionLivre, resumeLivre, extraitLivre, imageLivre, prixHTLivre, poidLivre, affichageLivre)

VALUES('0375705106','TVA5%', 'Vintage Books',' Way of Zen','','24/07/1972', 'enseignements sur le zen', 'blablabla, blablabla', '', '3.99', '0.114', ''),
	('2811210210','TVA5%', 'Broch�',' Le cycle des d�mons T1','l''homme-rune','05/04/2017', '', 'Il y a parfois de tr�s bonnes raisons d''avoir peur du noir. Dans le monde du jeune Arlen, d�s que le soleil se couche, les d�mons sortent de terre et d�vorent... ', '', '3.89', '0.122', NULL),
	('9782253083177 ','TVA5%', 'Le livre de poche','Revival','','01/02/2017','', 'Il a suffi de quelques jours au charismatique r�v�rend Charles Jacobs pour ensorceler les habitants de Harlow, dans le Maine.', '', '8.90', '142', ''),
	('2963856479821','TVA5%', '','',NULL,'', '', '', '', '7.25', '114', ''),
	('2290317152','TVA5%', 'J''ai lu','Le robot qui r�vait','','01/09/2002', 'Nono est un petit robot, ami d''Ulysse!', 'Science-fiction Bienvenue dans le futur, ou plut�t dans une multitude de futurs ! O� un savant d�couvre un jour l''anti-gravit�, o� un autre pose au plus... ', NULL, '9.50', '154', '')
