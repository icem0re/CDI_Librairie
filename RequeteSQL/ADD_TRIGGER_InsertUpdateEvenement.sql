
/* InsertUpdateEvenement :
* Vérification que la date de fin d'un Evenement 
* ne préccéde pas la date de debut
*/

GO;
/**
 TRIGGER INSERT UPDATE ON TABLE Evenement
**/
CREATE TRIGGER InsertUpdateEvenement
ON Evenement
INSTEAD OF INSERT, UPDATE
AS
	-- STOPS IF NO ROW AFFECTED
	IF @@rowcount = 0 RETURN;
	
	DECLARE @hasError AS BIT = 0;
	DECLARE @dateDebut AS DATE;
	DECLARE @dateFin AS DATE;
	DECLARE insertedCursor CURSOR
		FOR SELECT dateDebutEvenement, 
			dateFinEvenement
		FROM inserted;
	
	-- Verification de la validiter de l'ensemble des tuples
	OPEN insertedCursor;
		FETCH NEXT FROM insertedCursor   
		INTO @dateDebut, @dateFin;

		WHILE @@FETCH_STATUS = 0  
		BEGIN
			
			IF (@dateFin != NULL)
			BEGIN
				IF (@dateDebut > @dateFin)
				BEGIN
					RAISERROR ('Error : Date de debut événement supérieure à date de fin.', -- Message text.  
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
			INTO @dateDebut, @dateFin;
		END
	CLOSE insertedCursor;  
	DEALLOCATE insertedCursor;
	
	-- N'executer le reste que s'il n'ya pas d'erreur
	IF (@hasError = 0)
	BEGIN
		
		-- Pour les UPDATE
		IF EXISTS (SELECT * FROM DELETED)
		BEGIN
			UPDATE Evenement
				SET nomEvenement= ins.nomEvenement,
						descriptionEvenement= ins.descriptionEvenement,
						dateDebutEvenement= ins.dateDebutEvenement,
						dateFinEvenement= ins.dateFinEvenement,
						lieuEvenement= ins.lieuEvenement,
						reductionEvenement= ins.reductionEvenement,
						codePromoEvenement= ins.codePromoEvenement,
						promoImmediatEvenement= ins.promoImmediatEvenement,
						commentaireEvenement= ins.commentaireEvenement
				FROM Evenement even
				JOIN inserted ins
				ON even.idEvenement = ins.idEvenement
		END
		ELSE
		-- Pour les INSERT
		BEGIN
			INSERT INTO Evenement
					(nomEvenement,
						descriptionEvenement,
						dateDebutEvenement,
						dateFinEvenement,
						lieuEvenement,
						reductionEvenement,
						codePromoEvenement,
						promoImmediatEvenement,
						commentaireEvenement
					)
				SELECT nomEvenement,
					descriptionEvenement,
					dateDebutEvenement,
					dateFinEvenement,
					lieuEvenement,
					reductionEvenement,
					codePromoEvenement,
					promoImmediatEvenement,
					commentaireEvenement
				FROM INSERTED
		END

	END

END
GO;

