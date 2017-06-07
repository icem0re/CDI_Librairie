USE Librairie

alter table Adresse
drop constraint fkClientAdresse

alter table Commande
drop constraint fkLivreurCommande
alter table Commande
drop constraint fkAdresseCommande
alter table Commande
drop constraint fkClientCommande
alter table Commande
drop constraint fkAdresseCommande

alter table LigneDeCommande
drop constraint fkCommandeLigneDeCommande
alter table LigneDeCommande
drop constraint fkLivreLigneDeCommande

alter table Livre
drop constraint fkTVALivre
alter table Livre
drop constraint fkEditeurLivre

alter table Avis
drop constraint fkLivreAvis
alter table Avis
drop constraint fkLigneDeCommandeAvis

alter table Genre
drop constraint fkSousThematiqueGenre
alter table Genre
drop constraint fkLivreGenre

alter table Presentation
drop constraint fkLivrePresentation
alter table  Presentation
drop constraint fkEvenementPresentation

alter table SousThematique
drop constraint fkThematiqueSousThematique

alter table Thematique
drop constraint fkSousThematiqueThematique
alter table Thematique
drop constraint fkEvenementThematique

alter table Invitation
drop constraint fkAuteurInvitation
alter table Invitation
drop constraint fkEvenementInvitation

alter table Concerne
drop constraint fkEditeurConcerne
alter table Concerne
drop constraint fkEvenementConcerne
