
GO;
/**
 TRIGGER INSERT UPDATE ON TABLE Expedition
**/
CREATE TRIGGER InsertUpdateEmploye
ON Auteur
INSTEAD OF INSERT, UPDATE
AS
	DECLARE @debutPoste AS DATE = (SELECT debutPosteEmploye FROM inserted);
	DECLARE @finPoste AS DATE = (SELECT finPosteEmploye FROM inserted);
	DECLARE @hasError AS BIT = 0;

	IF (@finPoste != NULL)
	BEGIN
		IF (@debutPoste > @finPoste)
		BEGIN
			RAISERROR ('Error : Date de fin de poste supérieure à la date de debut de poste.', -- Message text.  
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
			UPDATE Employe
				SET nomEmploye = ins.nomEmploye,
					prenomEmploye = ins.prenomEmploye,
					loginEmploye = ins.loginEmploye,
					mdpEmploye = ins.mdpEmploye,
					emailEmploye = ins.emailEmploye,
					debutPosteEmploye = ins.debutPosteEmploye,
					finPosteEmploye = ins.finPosteEmploye
				FROM Employe empl
				JOIN inserted ins
				ON empl.idEmploye = ins.idEmploye
		END
		ELSE
		-- Pour les INSERT
		BEGIN
			INSERT INTO Auteur
					(nomEmploye,
						prenomEmploye,
						loginEmploye,
						mdpEmploye,
						emailEmploye,
						debutPosteEmploye,
						finPosteEmploye
					)
				SELECT nomEmploye,
						prenomEmploye,
						loginEmploye,
						mdpEmploye,
						emailEmploye,
						debutPosteEmploye,
						finPosteEmploye
				FROM INSERTED
		END

	END

END
GO;
