package Utilisateur;

import Exception.IdInconnuSQLException;
import SqlManager.SqlManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adresse {

    private int idAdresse;
    private String nomAdresse;
    private String destinataireAdresse;
    private String typeVoie;
    private String numVoie;
    private String nomVoie;
    private String complement;
    private String codePostal;
    private String ville;
    private String pays;

    public Adresse() {
    }
    
    /**
     * 
     * @param idAdresse
     * @throws SQLException
     * @throws IdInconnuSQLException 
     */
    public Adresse(int idAdresse) throws SQLException, IdInconnuSQLException {
        setIdAdresse(idAdresse);
        this.select();
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    private void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getNomAdresse() {
        return nomAdresse;
    }

    public void setNomAdresse(String nomAdresse) throws Exception {
        if (nomAdresse.equals("")){
            throw new Exception("Nom Vide");
        }
        this.nomAdresse = nomAdresse.trim();
    }

    public String getDestinataireAdresse()  {
         
        return destinataireAdresse;
    }

    public void setDestinataireAdresse(String destinataireAdresse) throws Exception {
        if (destinataireAdresse.equals("")){
            throw new Exception("destinataire Vide");
        }
        this.destinataireAdresse = destinataireAdresse.trim();
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public void setTypeVoie(String typeVoie) {
        if(typeVoie == ""){
            this.typeVoie = typeVoie;
        }else{
            this.typeVoie = typeVoie.trim();
        }
    }

    public String getNumVoie() {
        return numVoie;
    }

    public void setNumVoie(String numVoie) {
        if(numVoie == ""){
            this.numVoie = numVoie;
        }
        else {
        this.numVoie = numVoie.trim();
        }
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public void setNomVoie(String nomVoie)  {
        
        this.nomVoie = nomVoie.trim();
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement.trim();
    }

    public String getCodePostal()  {
        
        return codePostal;
    }

    public void setCodePostal(String codePostal) throws Exception {
        if (codePostal.equals("")){
            throw new Exception("code Postal Vide");
        }
        this.codePostal = codePostal.trim();
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) throws Exception {
        if (ville.equals("")){
            throw new Exception("ville Vide");
        }
        this.ville = ville.trim();
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays.trim();
    }

   

    private void select() throws SQLException {
        SqlManager sql3 = new SqlManager();

        String req = "SELECT "
                        + "nomAdresse, "
                        + "destinataireAdresse, "
                        + "typeVoie, "
                        + "numVoie, "
                        + "nomVoie, "
                        + "complement, "
                        + "codePostal, "
                        + "ville, "
                        + "pays "
                    + " FROM "
                        + "Adresse "
                    + " WHERE "
                        + " idAdresse = ?";

        //System.out.println("req = "+req);
        try (
                Connection cnt = sql3.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            // Définir l'idAdresse de ma requete
            pstm.setInt(1, idAdresse);
            
            // Executer ma requete sur le serveur MySQL
            // Puis récupérer le résultat de la requete dans rs
            ResultSet rs = pstm.executeQuery();

            // debut de recherche sur 1 SEUL resultat [rs.next]
            if (rs.next()) {
                setNomAdresse(rs.getString("nomAdresse"));
                setDestinataireAdresse(rs.getString("destinataireAdresse"));
                setTypeVoie(rs.getString("typeVoie"));
                setNumVoie(rs.getString("numVoie"));
                setNomVoie(rs.getString("nomVoie"));
                setComplement(rs.getString("complement"));
                setCodePostal(rs.getString("codePostal"));
                setVille(rs.getString("ville"));
                setPays(rs.getString("pays"));
            } else {
                // Aucun résultat de retourner
                // ERROR
                throw new IdInconnuSQLException("Id adresse non connue");
            }

        } catch (IdInconnuSQLException ex) {
            throw ex;
        } catch (SQLException ex) {
            System.err.println("erreur sql : " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("erreur sql : " + ex.getMessage());
        }

    }

         @Override
    public String toString() {
        String info = getIdAdresse()+" "+ getNomAdresse() + " " + getDestinataireAdresse() + " " + getTypeVoie() + " " + getNumVoie()
                + " " + getNomVoie() + " " + getComplement() + " " + getCodePostal() + " " + getVille() + " " + getPays();
        return info;
    }
}
