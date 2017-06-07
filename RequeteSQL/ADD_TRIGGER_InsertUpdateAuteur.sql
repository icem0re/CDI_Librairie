
/* InsertUpdateAuteur :
* Vérification que la date de dècès d'un auteur 
* ne préccéde pas ca date de naissance
*/

GO;
/**
 TRIGGER INSERT UPDATE ON TABLE Expedition
**/
CREATE TRIGGER InsertUpdateAuteur
ON Auteur
INSTEAD OF INSERT, UPDATE
AS
	DECLARE @dateNaissance AS DATE = (SELECT dateNaissanceAuteur FROM inserted);
	DECLARE @dateDeces AS DATE = (SELECT dateDecesAuteur FROM inserted);
	DECLARE @hasError AS BIT = 0;

	IF (@dateDeces != NULL)
	BEGIN
		IF (@dateNaissance > @dateDeces)
		BEGIN
			RAISERROR ('Error : Date de naissance supérieure à date de dècès.', -- Message text.  
				16, -- Severity.  
				1 -- State.  
				);
			SET @hasError = 1;
		END
	END

	-- N'executer le reste que s'il n'ya pas d'erreur
	IF (@hasError = 0)
	BEGIN
		
		-- Pour les UPDATE
		IF EXISTS (SELECT * FROM DELETED)
		BEGIN
			UPDATE Auteur
				SET nomAuteur = ins.nomAuteur,
					prenomAuteur = ins.prenomAuteur,
					dateNaissanceAuteur = ins.dateNaissanceAuteur,
					dateDecesAuteur = ins.dateDecesAuteur,
					nationaliteAuteur = ins.nationaliteAuteur,
					photoAuteur = ins.photoAuteur,
					bioAuteur = ins.bioAuteur
				FROM Auteur aute
				JOIN inserted ins
				ON aute.idAuteur = ins.idAuteur
		END
		ELSE
		-- Pour les INSERT
		BEGIN
			INSERT INTO Auteur
					(nomAuteur,
						prenomAuteur,
						dateNaissanceAuteur,
						dateDecesAuteur,
						nationaliteAuteur,
						photoAuteur,
						bioAuteur
					)
				SELECT nomAuteur,
					prenomAuteur,
					dateNaissanceAuteur,
					dateDecesAuteur,
					nationaliteAuteur,
					photoAuteur,
					bioAuteur
				FROM INSERTED
		END

	END

END
GO;
