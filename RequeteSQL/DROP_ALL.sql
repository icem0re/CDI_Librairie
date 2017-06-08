USE Librairie

ALTER TABLE Client 
DROP CONSTRAINT unLoginClient 

ALTER TABLE Client 
DROP CONSTRAINT unEmailClient

ALTER TABLE Client 
DROP CONSTRAINT dfstatutClient 

/**
* TABLE Employe
*/
ALTER TABLE Employe 
DROP CONSTRAINT unLoginEmploye 

ALTER TABLE Employe
DROP CONSTRAINT unEmailEmploye 


/**
* TABLE SousThematique
*/
ALTER TABLE SousThematique 
DROP CONSTRAINT unThematique 


/**
* TABLE Evenement
*/
ALTER TABLE Evenement 
DROP CONSTRAINT unCodePromo

/*COMMANDE*/
ALTER TABLE Commande
DROP CONSTRAINT fkCommandeLivreur

ALTER TABLE Domiciliation
DROP CONSTRAINT fkDomiciliationClient

ALTER TABLE Domiciliation
DROP CONSTRAINT fkDomiciliationAdresse

ALTER TABLE Facturation
DROP CONSTRAINT fkFacturationClient

ALTER TABLE Facturation
DROP CONSTRAINT fkFacturationAdresse

ALTER TABLE Client
DROP CONSTRAINT fkClientStatut

ALTER TABLE Editeur
DROP CONSTRAINT fkEditeurStatut

ALTER TABLE Commande
DROP CONSTRAINT fkCommandeStatut

ALTER TABLE Livreur
DROP CONSTRAINT fkLivreurStatut

ALTER TABLE Commande
DROP CONSTRAINT fkCommandeAdresse   -- facturation

ALTER TABLE Commande
DROP CONSTRAINT fkCommandeClient

ALTER TABLE Commande
DROP CONSTRAINT fkAdresseCommande   -- livraison

/*LIGNE DE COMMANDE*/
ALTER TABLE LigneDeCommande
DROP CONSTRAINT fkLigneDeCommandeCommande

ALTER TABLE LigneDeCommande
DROP CONSTRAINT fkLigneDeCommandeLivre

/*LIVRE*/
ALTER TABLE Livre
DROP CONSTRAINT fkLivreTVA

ALTER TABLE Livre
DROP CONSTRAINT fkLivreEditeur

/*AVIS*/
ALTER TABLE Avis
DROP CONSTRAINT fkAvisLivre

ALTER TABLE Avis
DROP CONSTRAINT fkAvisLigneDeCommande

/*GENRE*/
ALTER TABLE Genre
DROP CONSTRAINT fkGenreSousThematique

ALTER TABLE Genre
DROP CONSTRAINT fkGenreLivre

/*PRESENTATION*/
ALTER TABLE Presentation
DROP CONSTRAINT fkPresentationLivre

ALTER TABLE Presentation
DROP CONSTRAINT fkPresentationEvenement

/*SOUS THEMATIQUE*/
ALTER TABLE SousThematique
DROP CONSTRAINT fkSousThematiqueThematique

/*INVITATION*/
ALTER TABLE Invitation
DROP CONSTRAINT fkInvitationAuteur

ALTER TABLE Invitation
DROP CONSTRAINT fkInvitationEvenement

/*CONCERNE*/
ALTER TABLE Concerne
DROP CONSTRAINT fkConcerneEditeur

ALTER TABLE Concerne
DROP CONSTRAINT fkConcerneEvenement

ALTER TABLE Client
DROP CONSTRAINT pkClient

ALTER TABLE Adresse
DROP CONSTRAINT pkAdresse

ALTER TABLE TVA
DROP CONSTRAINT pkTVA

ALTER TABLE Domiciliation
DROP CONSTRAINT pkDomiciliation

ALTER TABLE Facturation
DROP CONSTRAINT pkFacturation

ALTER TABLE Statut
DROP CONSTRAINT pkStatut

ALTER TABLE Livre
DROP CONSTRAINT pkLivre

ALTER TABLE Auteur
DROP CONSTRAINT pkAuteur

ALTER TABLE Editeur
DROP CONSTRAINT pkEditeur

ALTER TABLE Thematique
DROP CONSTRAINT pkThematique

ALTER TABLE SousThematique
DROP CONSTRAINT pkSousThematique

ALTER TABLE Evenement
DROP CONSTRAINT pkEvenement

ALTER TABLE Livreur
DROP CONSTRAINT pkLivreur

ALTER TABLE Commande
DROP CONSTRAINT pkCommande

ALTER TABLE LigneDeCommande
DROP CONSTRAINT pkLigneDeCommande

ALTER TABLE Employe
DROP CONSTRAINT pkEmploye

ALTER TABLE Parametre
DROP CONSTRAINT pkParametre

ALTER TABLE Avis
DROP CONSTRAINT pkAvis

ALTER TABLE Genre
DROP CONSTRAINT pkGenre

ALTER TABLE Redaction
DROP CONSTRAINT pkRedaction

ALTER TABLE Concerne
DROP CONSTRAINT pkConcerne

ALTER TABLE Presentation
DROP CONSTRAINT pkPresentation

ALTER TABLE Theme
DROP CONSTRAINT pkTheme

ALTER TABLE Invitation
DROP CONSTRAINT pkInvitation

DROP TABLE Avis
DROP TABLE Genre
DROP TABLE Theme
DROP TABLE Presentation
DROP TABLE Invitation
DROP TABLE Redaction
DROP TABLE Concerne
DROP TABLE Parametre
DROP TABLE Evenement
DROP TABLE Employe
DROP TABLE SousThematique
DROP TABLE Editeur
DROP TABLE Auteur
DROP TABLE LigneDeCommande
DROP TABLE Livre
DROP TABLE Thematique
DROP TABLE Facturation
DROP TABLE Domiciliation
DROP TABLE Statut
DROP TABLE TVA
DROP TABLE Commande
DROP TABLE Livreur
DROP TABLE Adresse
DROP TABLE Client