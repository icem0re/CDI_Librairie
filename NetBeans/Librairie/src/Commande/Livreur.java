
package Commande;

import Exception.IdSQLException;
import SqlManager.SqlManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Livreur {
    
    String nomLivreur;
    float fraisPortLivreur;
    int delaiLivraisonLivreur;
    String statutLivreur;
    

public Livreur(){
}

public Livreur(String nomLivreur)throws IdSQLException, SQLException{
    this();
    setNomLivreur(nomLivreur);

}

    public String getNomLivreur() {
        return nomLivreur;
    }

    public float getFraisPortLivreur() {
        return fraisPortLivreur;
    }

    public int getDelaiLivraisonLivreur() {
        return delaiLivraisonLivreur;
    }

    public String getStatutLivreur() {
        return statutLivreur;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }

    public void setFraisPortLivreur(float fraisPortLivreur) {
        this.fraisPortLivreur = fraisPortLivreur;
    }

    public void setDelaiLivraisonLivreur(int delaiLivraisonLivreur) {
        this.delaiLivraisonLivreur = delaiLivraisonLivreur;
    }

    public void setStatutLivreur(String statutLivreur) {
        this.statutLivreur = statutLivreur;
    }

    @Override
    public String toString() {
        return "Livreur{" + "nomLivreur=" + nomLivreur + ", fraisPortLivreur=" + fraisPortLivreur + ", delaiLivraisonLivreur=" + delaiLivraisonLivreur + ", statutLivreur=" + statutLivreur + '}';
    }
    
    
     private void getLivreurFromSQL() throws IdSQLException, SQLException {
        
        String req = "SELECT "
                + "fraisPortLivreur"
                + "delaiLivraisonLivreur"
                + "statutLivreur"
                + " FROM Livreur "
                + " WHERE nomLivreur = ?";

        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setString(1, nomLivreur);

            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                setNomLivreur(rs.getString("nomLivreur"));
                setFraisPortLivreur(rs.getFloat("fraisPortLivreur"));
                setDelaiLivraisonLivreur(rs.getInt("delaiLivraisonLivreur"));
                setStatutLivreur(rs.getString("statutLivreur"));
                
            } else {
                throw new IdSQLException("Nom de livreur inconnu");
            }
            
        } catch (IdSQLException ex){
            throw ex;
        }
        catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

    }
     
     public void insererLivreurSQL() {

        String req = "INSERT INTO Livreur( "
                + "nomLivreur"
                + "fraisPortLivreur"
                + "delaiLivraisonLivreur"
                + "statutLivreur"
                + "VALUES(?,?,?,?)";
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);) {

           int i = 1;
            pstm.setString(i++, getNomLivreur());
            pstm.setFloat(i++, getFraisPortLivreur());
            pstm.setInt(i++, getDelaiLivraisonLivreur());
            pstm.setString(i++, getStatutLivreur());

            if (pstm.executeUpdate() > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                setNomLivreur(rs.getString(""));
            }

        } catch (SQLException ex) {
            System.err.println("3) erreur sql : " + ex.getMessage());
        }
    }
    

    






}