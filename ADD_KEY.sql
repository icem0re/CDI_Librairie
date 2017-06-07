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

/*ADRESSE*/
alter table Adresse
add constraint fkAdresseClient
foreign key (idClient)
references Client(idClient)

/*COMMANDE*/
alter table Commande
add constraint fkCommandeLivreur
foreign key (nomLivreur)
references Livreur(nomLivreur)

alter table Commande
add constraint fkCommandeAdresse   -- facturation
foreign key (idAdresseFacturation)
references Adresse(idAdresse)

alter table Commande
add constraint fkCommandeClient
foreign key (idClient)
references Client(idClient)

alter table Commande
add constraint fkAdresseCommande   -- livraison
foreign key (idAdresseLivraison)
references Adresse(idAdresse)

/*LIGNE DE COMMANDE*/
alter table LigneDeCommande
add constraint fkLigneDeCommandeCommande
foreign key (numCommande)
references Commande(numCommande)

alter table LigneDeCommande
add constraint fkLigneDeCommandeLivre
foreign key (isbnLivre)
references Livre(isbnLivre)

/*LIVRE*/
alter table Livre
add constraint fkLivreTVA
foreign key (nomTVA)
references TVA(nomTVA)

alter table Livre
add constraint fkLivreEditeur
foreign key (nomEditeur)
references Editeur(nomEditeur)

/*AVIS*/
alter table Avis
add constraint fkAvisLivre
foreign key (isbnLivre)
references Livre(isbnLivre)

alter table Avis
add constraint fkAvisLigneDeCommande
foreign key (idLigneDeCommande)
references LigneDeCommande(idLigneDeCommande)

/*GENRE*/
alter table Genre
add constraint fkGenreSousThematique
foreign key(idSousThematique)
references SousThematique(idSousThematique)

alter table Genre
add constraint fkGenreLivre
foreign key(isbnLivre)
references  Livre(isbnLivre)

/*PRESENTATION*/
alter table Presentation
add constraint fkPresentationLivre
foreign key (isbnLivre)
references Livre(isbnLivre)

alter table Presentation
add constraint fkPresentationEvenement
foreign key (idEvenement)
references Evenement(idEvenement)

/*SOUS THEMATIQUE*/
alter table SousThematique
add constraint fkSousThematiqueThematique
foreign key(nomThematique)
references Thematique(nomThematique)

/*INVITATION*/
alter table Invitation
add constraint fkInvitationAuteur
foreign key(idAuteur)
references Auteur(idAuteur)

alter table Invitation
add constraint fkInvitationEvenement
foreign key(idEvenement)
references Evenement(idEvenement)

/*CONCERNE*/
alter table Concerne
add constraint fkConcerneEditeur
foreign key(nomEditeur)
references Editeur(nomEditeur)

alter table Concerne
add constraint fkConcerneEvenement
foreign key(idEvenement)
references Evenement(idEvenement)






















