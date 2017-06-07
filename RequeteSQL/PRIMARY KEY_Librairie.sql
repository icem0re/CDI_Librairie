


ALTER TABLE Client
ADD CONSTRAINT pkClient
PRIMARY KEY(idClient)

ALTER TABLE Adresse
ADD CONSTRAINT pkAdresse
PRIMARY KEY(idAdresse)

ALTER TABLE TVA
ADD CONSTRAINT pkTVA
PRIMARY KEY(nomTVA)

ALTER TABLE Livre
ADD CONSTRAINT pkLivre
PRIMARY KEY(isbnLivre)

ALTER TABLE Auteur
ADD CONSTRAINT pkAuteur
PRIMARY KEY(idAuteur)

ALTER TABLE Editeur
ADD CONSTRAINT pkEditeur
PRIMARY KEY(nomEditeur)

ALTER TABLE Thematique
ADD CONSTRAINT pkThematique
PRIMARY KEY (nomThematique)

ALTER TABLE SousThematique
ADD CONSTRAINT pkSousThematique
PRIMARY KEY (idSousThematique)

ALTER TABLE Evenement
ADD CONSTRAINT pkEvenement
PRIMARY KEY (idEvenement)

ALTER TABLE Livreur
ADD CONSTRAINT pkLivreur
PRIMARY KEY (nomLivreur)

ALTER TABLE Commande
ADD CONSTRAINT pkCommande
PRIMARY KEY (numCommande)

ALTER TABLE LigneDeCommande
ADD CONSTRAINT pkLigneDeCommande
PRIMARY KEY (idLigneDeCommande)

ALTER TABLE Employe
ADD CONSTRAINT pkEmploye
PRIMARY KEY (idEmploye)

ALTER TABLE Parametre
ADD CONSTRAINT pkParametre
PRIMARY KEY(nomParametre)

ALTER TABLE Avis
ADD CONSTRAINT pkAvis
PRIMARY KEY(isbnLivre)

ALTER TABLE Avis
ADD CONSTRAINT pkAvis
PRIMARY KEY(idLigneDeCommande)

ALTER TABLE Genre
ADD CONSTRAINT pkGenre
PRIMARY KEY (idSousThematique)

ALTER TABLE Genre
ADD CONSTRAINT pkGenre
PRIMARY KEY (isbnLivre)

ALTER TABLE Redaction
ADD CONSTRAINT pkRedaction
PRIMARY KEY (idAuteur)

ALTER TABLE Redaction
ADD CONSTRAINT pkRedaction
PRIMARY KEY (isbnLivre)

ALTER TABLE Concerne
ADD CONSTRAINT pkConcerne
PRIMARY KEY (nomEditeur)

ALTER TABLE Concerne
ADD CONSTRAINT pkConcerne
PRIMARY KEY (idEvenement)

ALTER TABLE Presentation
ADD CONSTRAINT pkPresentation
PRIMARY KEY (isbnLivre)

ALTER TABLE Presentation
ADD CONSTRAINT pkPresentation
PRIMARY KEY (idEvenement)

ALTER TABLE Theme
ADD CONSTRAINT pkTheme
PRIMARY KEY (idSousThematique)

ALTER TABLE Theme
ADD CONSTRAINT pkTheme
PRIMARY KEY (idEvenement)

ALTER TABLE Invitation
ADD CONSTRAINT pkInvitation
PRIMARY KEY (idAuteur)

ALTER TABLE Invitation
ADD CONSTRAINT pkInvitation
PRIMARY KEY (idEvenement)
