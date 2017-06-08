
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
	-- STOPS IF NO ROW AFFECTED
	IF @@rowcount = 0 RETURN;
	
	DECLARE @hasError AS BIT = 0;
	DECLARE @dateNaissance AS DATE;--= (SELECT dateNaissanceAuteur FROM inserted);
	DECLARE @dateDeces AS DATE;-- = (SELECT dateDecesAuteur FROM inserted);
	DECLARE insertedCursor CURSOR
		FOR SELECT dateNaissanceAuteur, 
			dateDecesAuteur 
		FROM inserted;
	
	-- Verification de la validiter de l'ensemble des tuples
	OPEN insertedCursor;
		FETCH NEXT FROM insertedCursor   
		INTO @dateNaissance, @dateDeces;

		WHILE @@FETCH_STATUS = 0  
		BEGIN
			
			IF (@dateDeces != NULL)
			BEGIN
				IF (@dateNaissance > @dateDeces)
				BEGIN
					RAISERROR ('Error : Date de naissance supérieure à date de dècès.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
			END
			
			FETCH NEXT FROM insertedCursor  
			INTO @dateNaissance, @dateDeces;
		END
	CLOSE insertedCursor;  
	DEALLOCATE insertedCursor;
	
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
