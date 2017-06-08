use Librairie


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
PRIMARY KEY(nom)

ALTER TABLE Avis
ADD CONSTRAINT pkAvis
PRIMARY KEY(isbnLivre,idLigneDeCommande)

ALTER TABLE Genre
ADD CONSTRAINT pkGenre
PRIMARY KEY (idSousThematique,isbnLivre)

ALTER TABLE Redaction
ADD CONSTRAINT pkRedaction
PRIMARY KEY (idAuteur,isbnLivre)

ALTER TABLE Concerne
ADD CONSTRAINT pkConcerne
PRIMARY KEY (nomEditeur,idEvenement)

ALTER TABLE Presentation
ADD CONSTRAINT pkPresentation
PRIMARY KEY (isbnLivre,idEvenement)

ALTER TABLE Theme
ADD CONSTRAINT pkTheme
PRIMARY KEY (idSousThematique,idEvenement)

ALTER TABLE Invitation
ADD CONSTRAINT pkInvitation
PRIMARY KEY (idAuteur,idEvenement)

ALTER TABLE Domiciliation
ADD CONSTRAINT pkDomiciliation
PRIMARY KEY(idClient,idAdresse)

ALTER TABLE Facturation
ADD CONSTRAINT pkFacturation
PRIMARY KEY (idClient,idAdresse)

ALTER TABLE Statut
ADD CONSTRAINT pkStatut
PRIMARY KEY (code)
