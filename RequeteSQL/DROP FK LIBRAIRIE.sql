USE Librairie

ALTER TABLE Adresse
DROP CONSTRAINT fkClientAdresse

ALTER TABLE Commande
DROP CONSTRAINT fkLivreurCommande
ALTER TABLE Commande
DROP CONSTRAINT fkAdresseCommande
ALTER TABLE Commande
DROP CONSTRAINT fkClientCommande
ALTER TABLE Commande
DROP CONSTRAINT fkAdresseCommande

ALTER TABLE LigneDeCommande
DROP CONSTRAINT fkCommandeLigneDeCommande
ALTER TABLE LigneDeCommande
DROP CONSTRAINT fkLivreLigneDeCommande

ALTER TABLE Livre
DROP CONSTRAINT fkTVALivre
ALTER TABLE Livre
DROP CONSTRAINT fkEditeurLivre

ALTER TABLE Avis
DROP CONSTRAINT fkLivreAvis
ALTER TABLE Avis
DROP CONSTRAINT fkLigneDeCommandeAvis

ALTER TABLE Genre
DROP CONSTRAINT fkSousThematiqueGenre
ALTER TABLE Genre
DROP CONSTRAINT fkLivreGenre

ALTER TABLE Presentation
DROP CONSTRAINT fkLivrePresentation
ALTER TABLE  Presentation
DROP CONSTRAINT fkEvenementPresentation

ALTER TABLE SousThematique
DROP CONSTRAINT fkThematiqueSousThematique

ALTER TABLE Thematique
DROP CONSTRAINT fkSousThematiqueThematique
ALTER TABLE Thematique
DROP CONSTRAINT fkEvenementThematique

ALTER TABLE Invitation
DROP CONSTRAINT fkAuteurInvitation
ALTER TABLE Invitation
DROP CONSTRAINT fkEvenementInvitation

ALTER TABLE Concerne
DROP CONSTRAINT fkEditeurConcerne
ALTER TABLE Concerne
DROP CONSTRAINT fkEvenementConcerne
