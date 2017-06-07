use Librairie

CREATE TABLE Client(
idClient bigint identity(1,1) not null,
nomClient varchar(50) not null,
prenomClient varchar(50) not null,
loginClient varchar(50) not null,
mdpClient varchar(50) not null,
emailClient varchar(255) not null,
dateNaissanceClient Date not null,
activiteClient bit,
)

CREATE TABLE Adresse(
idAdresse bigint identity(1,1) not null,
idClient bigint not null,
typeAdresse varchar(50) not null,
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
)

CREATE TABLE Commande(
numCommande bigint identity(1,1) not null,
idClient bigint not null,
idAdresseFacturation bigint not null,
idAdresseLivraison bigint not null,
nomLivreur varchar(50) not null,
dateCommande datetime not null,
datePreparationCommande datetime,
dateExpeditionCommande datetime,
dateAccuseReceptionCommande Datetime,
)

CREATE TABLE TVA(
nomTVA varchar(50) not null,
tauxTVA float not null,
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
cessationEditeur bit,
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
