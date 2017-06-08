USE Librairie;

CREATE TABLE Client(
idClient bigint identity(1,1) not null,
nomClient varchar(50) not null,
prenomClient varchar(50) not null,
loginClient varchar(50) not null,
mdpClient varchar(50) not null,
emailClient varchar(255) not null,
dateNaissanceClient Date not null,
statutClient varchar(30),
derniereConnexionClient datetime not null, 
)

CREATE TABLE Adresse(
idAdresse bigint identity(1,1) not null,
nomAdresse varchar(150) not null,
destinataireAdresse varchar(150) not null,
typeVoie varchar(20),
numVoie varchar(50),
nomVoie varchar(50),
complement text,
codePostal varchar(10) not null,
ville varchar(50) not null,
pays varchar(50)
)

CREATE TABLE Livreur(
nomLivreur varchar(50) not null,
fraisPortLivreur money not null,
delaiLivraisonLivreur int,
statutLivreur varchar(30),

)

CREATE TABLE Commande(
numCommande bigint identity(1,1) not null,
idClient bigint not null,
idAdresseFacturation bigint not null,
idAdresseLivraison bigint not null,
nomLivreur varchar(50) not null,
dateCommande datetime not null,
datePaiementCommande datetime,
datePreparationCommande datetime,
dateExpeditionCommande datetime,
dateAccuseReceptionCommande Datetime,
statutCommande varchar(30),
ipCommande varchar(20),
dateAnnulationCommande datetime,
)

CREATE TABLE TVA(
nomTVA varchar(50) not null,
tauxTVA float not null,
)

CREATE TABLE Statut(
code varchar(30) not null,
info text,
)

CREATE TABLE Domiciliation(
idClient bigint not null,
idAdresse bigint not null,
)

CREATE TABLE Facturation(
idClient bigint not null,
idAdresse bigint not null,
)

CREATE TABLE Livre(
isbnLivre varchar(13) not null,
nomTVA varchar(50) not null,
nomEditeur varchar(50) not null,
titreLivre varchar(50) not null,
sousTitreLivre varchar(50),
dateParutionLivre Date not null,
resumeLivre text not null,
extraitLivre text,
imageLivre varchar,
prixHTLivre money not null,
poidLivre integer,
affichageLivre bit,
)

CREATE TABLE Thematique(
nomThematique varchar(50)not null,
)

CREATE TABLE LigneDeCommande(
idLigneDeCommande bigint identity(1,1) not null,
numCommande bigint not null,
isbnLivre varchar(13) not null,
quantiteLigneDeCommande int not null,
PrixUnitaireHTLigneDeCommande money not null,
TVALigneDeCommande float not null,
reductionLigneDeCommande float,
)

CREATE TABLE Auteur(
idAuteur bigint identity(1,1) not null,
nomAuteur varchar(50) not null,
prenomAuteur varchar(50) not null,
dateNaissanceAuteur Date not null,
dateDecesAuteur Date,
nationaliteAuteur varchar(50),
photoAuteur varchar,
bioAuteur text,
)

CREATE TABLE Editeur(
nomEditeur varchar(50) not null,
logoEditeur varchar,
statutEditeur varchar(30),
)

CREATE TABLE SousThematique(
idSousThematique varchar(50) not null,
nomThematique varchar(50) not null,
nomSousThematique varchar(50),
descriptionSousThematique text,
)

CREATE TABLE Employe(
idEmploye bigint identity(1,1) not null,
nomEmploye varchar(50) not null,
prenomEmploye varchar(50) not null,
loginEmploye varchar(50) not null,
mdpEmploye varchar(50) not null,
emailEmploye varchar(255) not null,
debutPosteEmploye Date not null,
finPosteEmploye Date,
)

CREATE TABLE Evenement(
idEvenement bigint identity(1,1) not null,
nomEvenement varchar(50) not null,
descriptionEvenement text not null,
dateDebutEvenement Date not null,
dateFinEvenement Date,
lieuEvenement varchar(255),
reductionEvenement float,
codePromoEvenement varchar(15),
promoImmediatEvenement bit,
commentaireEvenement text,
)

CREATE TABLE Parametre(
nom varchar(50) not null,
propriete text,
)

CREATE TABLE Concerne(
nomEditeur varchar(50) not null,
idEvenement bigint not null,
)

CREATE TABLE Redaction(
idAuteur bigint not null,
isbnLivre varchar(13) not null,
)

CREATE TABLE Invitation(
idAuteur bigint not null,
idEvenement bigint not null,
)

CREATE TABLE Presentation(
isbnLivre varchar(13) not null,
idEvenement bigint not null,
)

CREATE TABLE Theme(
idSousThematique varchar(50) not null,
idEvenement bigint not null,
)

CREATE TABLE Genre(
idSousThematique varchar(50) not null,
isbnLivre varchar(13) not null,
)

CREATE TABLE Avis(
isbnLivre varchar(13) not null,
idLigneDeCommande bigint not null,
commentaireAvis text,
noteAvis int,
)

ALTER TABLE Client
ADD CONSTRAINT pkClient
PRIMARY KEY(idClient)

ALTER TABLE Adresse
ADD CONSTRAINT pkAdresse
PRIMARY KEY(idAdresse)

ALTER TABLE TVA
ADD CONSTRAINT pkTVA
PRIMARY KEY(nomTVA)

ALTER TABLE Domiciliation
ADD CONSTRAINT pkDomiciliation
PRIMARY KEY(idClient,idAdresse)

ALTER TABLE Facturation
ADD CONSTRAINT pkFacturation
PRIMARY KEY(idClient,idAdresse)

ALTER TABLE Statut
ADD CONSTRAINT pkStatut
PRIMARY KEY(code)

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

/*COMMANDE*/
alter table Commande
add constraint fkCommandeLivreur
foreign key (nomLivreur)
references Livreur(nomLivreur)

alter table Domiciliation
add constraint fkDomiciliationClient
foreign key(idClient)
references Client(idClient)

alter table Domiciliation
add constraint fkDomiciliationAdresse
foreign key(idAdresse)
references Adresse(idAdresse)

alter table Facturation
add constraint fkFacturationClient
foreign key(idClient)
references Client(idClient)

alter table Facturation
add constraint fkFacturationAdresse
foreign key(idAdresse)
references Adresse(idAdresse)

alter table Client
add constraint fkClientStatut
foreign key(statutClient)
references Statut(code)

alter table Editeur
add constraint fkEditeurStatut
foreign key(statutEditeur)
references Statut(code)

alter table Commande
add constraint fkCommandeStatut
foreign key(statutCommande)
references Statut(code)

alter table Livreur
add constraint fkLivreurStatut
foreign key(statutLivreur)
references Statut(code)

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

ALTER TABLE Client 
ADD CONSTRAINT unLoginClient 
UNIQUE(loginClient)

ALTER TABLE Client 
ADD CONSTRAINT unEmailClient 
UNIQUE(emailClient)

ALTER TABLE Client 
ADD CONSTRAINT dfstatutClient 
DEFAULT 1 FOR statutClient

/**
* TABLE Employe
*/
ALTER TABLE Employe 
ADD CONSTRAINT unLoginEmploye 
UNIQUE(loginEmploye)

ALTER TABLE Employe
ADD CONSTRAINT unEmailEmploye 
UNIQUE(emailEmploye)


/**
* TABLE SousThematique
*/
ALTER TABLE SousThematique 
ADD CONSTRAINT unThematique 
UNIQUE(nomThematique, nomSousThematique)


/**
* TABLE Evenement
*/
ALTER TABLE Evenement 
ADD CONSTRAINT unCodePromo 
UNIQUE(codePromoEvenement)

/**
* TABLE Adresse
*/
ALTER TABLE Adresse ADD CONSTRAINT unNomAdresseCient UNIQUE(nomAdresse, idClient);
