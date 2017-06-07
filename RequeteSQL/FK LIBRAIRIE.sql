use Librairie

/*ADRESSE*/
ALTER TABLE Adresse
ADD CONSTRAINT fkClientAdresse
FOREIGN KEY (idClient)
REFERENCES Client(idClient)

/*COMMANDE*/
ALTER TABLE Commande
ADD CONSTRAINT fkLivreurCommande
FOREIGN KEY (nomLivreur)
REFERENCES Livreur(nomLivreur)

ALTER TABLE Commande
ADD CONSTRAINT fkAdresseCommande   -- facturation
FOREIGN KEY (idAdresseFacturation)
REFERENCES Adresse(idAdresseFacturation)

ALTER TABLE Commande
ADD CONSTRAINT fkClientCommande
FOREIGN KEY (idClient)
REFERENCES Client(idClient)

ALTER TABLE Commande
ADD CONSTRAINT fkAdresseCommande   -- livraison
FOREIGN KEY (idAdresseLivraison)
REFERENCES Adresse(idAdresseLivraison)

/*LIGNE DE COMMANDE*/
ALTER TABLE LigneDeCommande
ADD CONSTRAINT fkCommandeLigneDeCommande
FOREIGN KEY (numCommande)
REFERENCES Commande(numCommande)

ALTER TABLE LigneDeCommande
ADD CONSTRAINT fkLivreLigneDeCommande
FOREIGN KEY (isbnLivre)
REFERENCES Livre(isbnLivre)

/*LIVRE*/
ALTER TABLE Livre
ADD CONSTRAINT fkTVALivre
FOREIGN KEY (nomTVA)
REFERENCES TVA(nomTVA)

ALTER TABLE Livre
ADD CONSTRAINT fkEditeurLivre
FOREIGN KEY (nomEditeur)
REFERENCES Editeur(nomEditeur)

/*AVIS*/
ALTER TABLE Avis
ADD CONSTRAINT fkLivreAvis
FOREIGN KEY (isbnLivre)
REFERENCES Livre(isbnLivre)

ALTER TABLE Avis
ADD CONSTRAINT fkLigneDeCommandeAvis
FOREIGN KEY (nomCommande)
REFERENCES LigneDeCommande(nomCommande)

/*GENRE*/
ALTER TABLE Genre
ADD CONSTRAINT fkSousThematiqueGenre
FOREIGN KEY(idSousThematique)
REFERENCES SousThematique(idSousThematique)

ALTER TABLE Genre
ADD CONSTRAINT fkLivreGenre
FOREIGN KEY(isbnLivre)
REFERENCES SLivre(isbnLivre)

/*PRESENTATION*/
ALTER TABLE Presentation
ADD CONSTRAINT fkLivrePresentation
FOREIGN KEY (isbnLivre)
REFERENCES Livre(isbnLivre)

ALTER TABLE Presentation
ADD CONSTRAINT fkEvenementPresentation
FOREIGN KEY (idEvenement)
REFERENCES Evenement(idEvenement)

/*SOUS THEMATIQUE*/
ALTER TABLE SousThematique
ADD CONSTRAINT fkThematiqueSousThematique
FOREIGN KEY(nomThematique)
REFERENCES Thematique(nomThematique)

/*THEMATIQUE*/
ALTER TABLE Thematique
ADD CONSTRAINT fkSousThematiqueThematique
FOREIGN KEY (idSousThematique)
REFERENCES SousThematique(idSousThematique)

ALTER TABLE Thematique
ADD CONSTRAINT fkEvenementThematique
FOREIGN KEY (idEvenement)
REFERENCES Evenement(idEvenement)

/*INVITATION*/
ALTER TABLE Invitation
ADD CONSTRAINT fkAuteurInvitation
FOREIGN KEY(idAuteur)
REFERENCES Auteur(idAuteur)

ALTER TABLE Invitation
ADD CONSTRAINT fkEvenementInvitation
FOREIGN KEY(idEvenement)
REFERENCES Evenement(idEvenement)

/*CONCERNE*/
ALTER TABLE Concerne
ADD CONSTRAINT fkEditeurConcerne
FOREIGN KEY(nomEditeur)
REFERENCES Editeur(nomEditeur)

ALTER TABLE Concerne
ADD CONSTRAINT fkEvenementConcerne
FOREIGN KEY(idEvenement)
REFERENCES Evenement(idEvenement)






















