use Librairie

/*ADRESSE*/
alter table Adresse
add constraint fkClientAdresse
foreign key (idClient)
references Client(idClient)

/*COMMANDE*/
alter table Commande
add constraint fkLivreurCommande
foreign key (nomLivreur)
references Livreur(nomLivreur)

alter table Commande
add constraint fkAdresseCommande   -- facturation
foreign key (idAdresseFacturation)
references Adresse(idAdresseFacturation)

alter table Commande
add constraint fkClientCommande
foreign key (idClient)
references Client(idClient)

alter table Commande
add constraint fkAdresseCommande   -- livraison
foreign key (idAdresseLivraison)
references Adresse(idAdresseLivraison)

/*LIGNE DE COMMANDE*/
alter table LigneDeCommande
add constraint fkCommandeLigneDeCommande
foreign key (numCommande)
references Commande(numCommande)

alter table LigneDeCommande
add constraint fkLivreLigneDeCommande
foreign key (isbnLivre)
references Livre(isbnLivre)

/*LIVRE*/
alter table Livre
add constraint fkTVALivre
foreign key (nomTVA)
references TVA(nomTVA)

alter table Livre
add constraint fkEditeurLivre
foreign key (nomEditeur)
references Editeur(nomEditeur)

/*AVIS*/
alter table Avis
add constraint fkLivreAvis
foreign key (isbnLivre)
references Livre(isbnLivre)

alter table Avis
add constraint fkLigneDeCommandeAvis
foreign key (nomCommande)
references LigneDeCommande(nomCommande)

/*GENRE*/
alter table Genre
add constraint fkSousThematiqueGenre
foreign key(idSousThematique)
references SousThematique(idSousThematique)

alter table Genre
add constraint fkLivreGenre
foreign key(isbnLivre)
references SLivre(isbnLivre)

/*PRESENTATION*/
alter table Presentation
add constraint fkLivrePresentation
foreign key (isbnLivre)
references Livre(isbnLivre)

alter table Presentation
add constraint fkEvenementPresentation
foreign key (idEvenement)
references Evenement(idEvenement)

/*SOUS THEMATIQUE*/
alter table SousThematique
add constraint fkThematiqueSousThematique
foreign key(nomThematique)
references Thematique(nomThematique)

/*THEMATIQUE*/
alter table Thematique
add constraint fkSousThematiqueThematique
foreign key (idSousThematique)
references SousThematique(idSousThematique)

alter table Thematique
add constraint fkEvenementThematique
foreign key (idEvenement)
references Evenement(idEvenement)

/*INVITATION*/
alter table Invitation
add constraint fkAuteurInvitation
foreign key(idAuteur)
references Auteur(idAuteur)

alter table Invitation
add constraint fkEvenementInvitation
foreign key(idEvenement)
references Evenement(idEvenement)

/*CONCERNE*/
alter table Concerne
add constraint fkEditeurConcerne
foreign key(nomEditeur)
references Editeur(nomEditeur)

alter table Concerne
add constraint fkEvenementConcerne
foreign key(idEvenement)
references Evenement(idEvenement)






















