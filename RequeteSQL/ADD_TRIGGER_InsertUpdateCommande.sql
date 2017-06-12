
/* InsertUpdateCommande :
* 
*/

GO;
/**
 TRIGGER INSERT UPDATE ON TABLE Commande
**/
CREATE TRIGGER InsertUpdateCommande
ON Commande
INSTEAD OF INSERT, UPDATE
AS
	-- STOPS IF NO ROW AFFECTED
	IF @@rowcount = 0 RETURN;
	
	DECLARE @hasError AS BIT = 0;
	DECLARE @dateCommande AS DATE;
	DECLARE @datePaiementCommande AS DATE;
	DECLARE @datePreparationCommande AS DATE;
	DECLARE @dateAnnulationCommande AS DATE;
	DECLARE @dateExpeditionCommande AS DATE;
	DECLARE @dateAccuseReceptionCommande AS DATE;
	DECLARE insertedCursor CURSOR
		FOR SELECT dateCommande, 
			datePaiementCommande,
			datePreparationCommande,
			dateAnnulationCommande,
			dateExpeditionCommande,
			dateAccuseReceptionCommande
		FROM inserted;
	
	-- Verification de la validiter de l'ensemble des tuples
	OPEN insertedCursor;
		FETCH NEXT FROM insertedCursor   
		INTO @dateCommande, 
			@datePaiementCommande,
			@datePreparationCommande,
			@dateAnnulationCommande,
			@dateExpeditionCommande,
			@dateAccuseReceptionCommande
		

		WHILE @@FETCH_STATUS = 0  
		BEGIN
			
			IF (@datePaiementCommande IS NOT NULL)
			BEGIN
				IF (@dateCommande > @datePaiementCommande)
				BEGIN
					RAISERROR ('Error : Commande Payé avant d''être commander.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
			END
			
			IF (@datePreparationCommande IS NOT NULL)
			BEGIN
				IF (@dateCommande > @datePreparationCommande)
				BEGIN
					RAISERROR ('Error : Commande Preparé avant d''être commander.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
			END
			
			IF (@dateAnnulationCommande IS NOT NULL)
			BEGIN
				IF (@dateCommande > @dateAnnulationCommande)
				BEGIN
					RAISERROR ('Error : Commande Annulé avant d''être commander.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
			END
			
			IF (@dateExpeditionCommande IS NOT NULL)
			BEGIN
				IF (@dateCommande > @dateExpeditionCommande)
				BEGIN
					RAISERROR ('Error : Commande Expedié avant d''être commander.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
				
				-- Une commande expedié doit d'abord être préparé avant
				IF ((@datePreparationCommande = NULL) OR(@datePreparationCommande > @dateExpeditionCommande))
				BEGIN
					RAISERROR ('Error : Commande Expedié avant d''être préparer.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
			END
			
			IF (@dateAccuseReceptionCommande IS NOT NULL)
			BEGIN
				IF (@dateCommande > @dateAccuseReceptionCommande)
				BEGIN
					RAISERROR ('Error : Commande Reçu avant d''être commander.', -- Message text.  
						16, -- Severity.  
						1 -- State.  
						);
					SET @hasError = 1;
					CLOSE insertedCursor;  
					DEALLOCATE insertedCursor;
					RETURN;
				END
				
				-- Une commande receptionner doit d'abord avoir été expedier
				IF ((@dateExpeditionCommande IS NULL) OR (@dateExpeditionCommande > @dateAccuseReceptionCommande))
				BEGIN
					RAISERROR ('Error : Commande Reçu avant d''être expedier.', -- Message text.  
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
			INTO @dateCommande, 
				@datePaiementCommande,
				@datePreparationCommande,
				@dateAnnulationCommande,
				@dateExpeditionCommande,
				@dateAccuseReceptionCommande
		END
	CLOSE insertedCursor;  
	DEALLOCATE insertedCursor;
	
	-- N'executer le reste que s'il n'ya pas d'erreur
	IF (@hasError = 0)
	BEGIN
		
		-- Pour les UPDATE
		IF EXISTS (SELECT * FROM DELETED)
		BEGIN
			UPDATE Commande
				SET idAdresseFacturation = ins.idAdresseFacturation,
						idAdresseLivraison = ins.idAdresseLivraison,
						idClient = ins.idClient,
						nomLivreur = ins.nomLivreur,
						dateCommande = ins.dateCommande,
						datePaiementCommande = ins.datePaiementCommande,
						datePreparationCommande = ins.datePreparationCommande,
						dateExpeditionCommande = ins.dateExpeditionCommande,
						dateAccuseReceptionCommande = ins.dateAccuseReceptionCommande,
						statutCommande = ins.statutCommande,
						ipCommande = ins.ipCommande
				FROM Commande cmde
				JOIN inserted ins
				ON cmde.numCommande = ins.numCommande
		END
		ELSE
		-- Pour les INSERT
		BEGIN
			INSERT INTO Commande
					(numCommande,
						idAdresseFacturation,
						idAdresseLivraison,
						idClient,
						nomLivreur,
						dateCommande,
						datePaiementCommande,
						datePreparationCommande,
						dateExpeditionCommande,
						dateAccuseReceptionCommande,
						statutCommande,
						ipCommande
					)
				SELECT numCommande,
						idAdresseFacturation,
						idAdresseLivraison,
						idClient,
						nomLivreur,
						dateCommande,
						datePaiementCommande,
						datePreparationCommande,
						dateExpeditionCommande,
						dateAccuseReceptionCommande,
						statutCommande,
						ipCommande
				FROM INSERTED
		END

	END

END
GO;
