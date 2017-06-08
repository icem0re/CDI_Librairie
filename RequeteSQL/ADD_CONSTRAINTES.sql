

/**
* TABLE Client
*/
ALTER TABLE Client ADD CONSTRAINT unLoginClient UNIQUE(loginClient);
ALTER TABLE Client ADD CONSTRAINT unEmailClient UNIQUE(emailClient);

ALTER TABLE Client ADD CONSTRAINT dfActiviteClient DEFAULT 1 FOR activiteClient;

/**
* TABLE Employe
*/
ALTER TABLE Employe ADD CONSTRAINT unLoginEmploye UNIQUE(loginEmploye);
ALTER TABLE Employe ADD CONSTRAINT unEmailEmploye UNIQUE(emailEmploye);


/**
* TABLE SousThematique
*/
ALTER TABLE SousThematique ADD CONSTRAINT unThematique UNIQUE(nomThematique, nomSousThematique);


/**
* TABLE Evenement
*/
ALTER TABLE Evenement ADD CONSTRAINT unCodePromo UNIQUE(codePromoEvenement);
