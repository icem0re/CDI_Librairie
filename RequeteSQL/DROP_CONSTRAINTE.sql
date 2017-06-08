
/**
* TABLE Client
*/
ALTER TABLE Client DROP CONSTRAINT unLoginClient;
ALTER TABLE Client DROP CONSTRAINT unEmailClient;

ALTER TABLE Client DROP CONSTRAINT dfActiviteClient;

/**
* TABLE Employe
*/
ALTER TABLE Employe DROP CONSTRAINT unLoginEmploye;
ALTER TABLE Employe DROP CONSTRAINT unEmailEmploye;


/**
* TABLE SousThematique
*/
ALTER TABLE SousThematique DROP CONSTRAINT unThematique;


/**
* TABLE Evenement
*/
ALTER TABLE Evenement DROP CONSTRAINT unCodePromo;
