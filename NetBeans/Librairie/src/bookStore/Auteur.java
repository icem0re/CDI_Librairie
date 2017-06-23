/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookStore;

import SqlManager.SqlManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi407
 */
public class Auteur {

    private int idAuteur;
    private String nomAuteur = "";
    private String prenomAuteur = "";
    private LocalDate dateNaissanceAuteur;
    private LocalDate dateDecesAuteur;
    private String nationaliteAuteur = "";
    private String photoAuteur = "";
    private String bioAuteur = "";
    private String statutAuteur = "";


    public Auteur() {
    }

    public Auteur(int idAuteur) throws Exception {
        setIdAuteur(idAuteur);
        getSqlData();
    }

    
    public void getSqlData() throws SQLException, Exception {

        String req = "SELECT "
                    + " idAuteur, "
                    + " nomAuteur, "
                    + " prenomAuteur, "
                    + " dateNaissanceAuteur, "
                    + " dateDecesAuteur, "
                    + " nationaliteAuteur, "
                    + " photoAuteur, "
                    + " bioAuteur "
                    + " FROM "
                    + " Auteur"
                    + " WHERE idAuteur = ?";
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);
            ) {
            
            stm.setInt(1, getIdAuteur());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                setIdAuteur(rs.getInt("idAuteur"));
                setNomAuteur(rs.getString("nomAuteur"));
                setPrenomAuteur(rs.getString("prenomAuteur"));
                setDateNaissanceAuteur((rs.getDate("dateNaissanceAuteur")).toLocalDate());
                setDateDecesAuteur((rs.getDate("dateDecesAuteur")).toLocalDate());
                setNationaliteAuteur(rs.getString("nationaliteAuteur"));
                setPhotoAuteur(rs.getString("photoAuteur"));
                setBioAuteur(rs.getString("bioAuteur"));
                
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public String toString() {
        return "Auteur{" + "idAuteur=" + idAuteur + ",\n nomAuteur=" + nomAuteur + ",\n prenomAuteur=" + prenomAuteur + ",\n dateNaissanceAuteur=" + dateNaissanceAuteur + ",\n dateDecesAuteur=" + dateDecesAuteur + ",\n nationaliteAuteur=" + nationaliteAuteur + ",\n photoAuteur=" + photoAuteur + ",\n bioAuteur=" + bioAuteur + ",\n statutAuteur=" + statutAuteur + '}';
    }
            

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

    public LocalDate getDateNaissanceAuteur() {
        return dateNaissanceAuteur;
    }

    public void setDateNaissanceAuteur(LocalDate dateNaissanceAuteur) {
        this.dateNaissanceAuteur = dateNaissanceAuteur;
    }

    public LocalDate getDateDecesAuteur() {
        return dateDecesAuteur;
    }

    public void setDateDecesAuteur(LocalDate dateDecesAuteur) {
        this.dateDecesAuteur = dateDecesAuteur;
    }

    public String getNationaliteAuteur() {
        return nationaliteAuteur;
    }

    public void setNationaliteAuteur(String nationaliteAuteur) {
        this.nationaliteAuteur = nationaliteAuteur;
    }

    public String getPhotoAuteur() {
        return photoAuteur;
    }

    public void setPhotoAuteur(String photoAuteur) {
        this.photoAuteur = photoAuteur;
    }

    public String getBioAuteur() {
        return bioAuteur;
    }

    public void setBioAuteur(String bioAuteur) {
        this.bioAuteur = bioAuteur;
    }

    public static ArrayList<Auteur> AffichageAuteur(String isbnLivre) {

        ArrayList<Auteur> listeAuteur = new ArrayList();

        SqlManager sql1 = new SqlManager();
        
        String req = "select a.idAuteur"
                    + " from Auteur a"
                    + " join Redaction r"
                    + " on a.idAuteur = r.idAuteur "
                    + " join Livre l"
                    + " on r.isbnLivre = l.isbnLivre"
                    + " AND isbnLIvre = ? "
                    + " ORDER BY nomAuteur ";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);) {

            

            stm.setString(1, isbnLivre);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Auteur auteur = new Auteur(rs.getInt("idAuteur"));

                //System.out.println("Auteur = " + auteur.getNomAuteur() + " " + auteur.getPrenomAuteur());

                listeAuteur.add(auteur);
            }
            for (int i = 0; i < listeAuteur.size(); i++) {
                listeAuteur.get(i).toString();
            }
        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Auteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeAuteur;
    }

    public void CreerAuteur() {

        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        String req = "insert into Auteur"
                + "("
                + " nomAuteur,"
                + " prenomAuteur,"
                + " dateNaissanceAuteur,"
                + " dateDecesAuteur,"
                + " nationaliteAuteur,"
                + " photoAuteur,"
                + " bioAuteur"
                + ")"
                + "values(?,?,?,?,?,?,?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setString(1, getNomAuteur());
            pstm.setString(2, getPrenomAuteur());
            pstm.setObject(3, getDateNaissanceAuteur(), java.sql.Types.DATE);
            if (getDateDecesAuteur() == null) {
                pstm.setNull(4, java.sql.Types.DATE);
            } else {
                pstm.setObject(4, getDateDecesAuteur(), java.sql.Types.DATE);
            }

            pstm.setString(5, getNationaliteAuteur());
            pstm.setString(6, getPhotoAuteur());
            pstm.setString(7, getBioAuteur());

            int i = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + i);

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }

    }
    
    public void UpdateAuteur(){
        SqlManager sql1 = null;

            sql1 = new SqlManager();
        
        
        String req = " UPDATE Auteur "
                + " SET "
                + " nomAuteur = ?,"
                + " prenomAuteur = ?,"
                + " statutAuteur = ?,"
                + " nationaliteAuteur = ?,"
                + " bioAuteur = ?"
                + " where idAuteur = ? ";
        
        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            int i = 1;
            pstm.setString(i++, getNomAuteur());
            pstm.setString(i++, getPrenomAuteur());
            if (getStatutAuteur()== null){
                pstm.setNull(i++, java.sql.Types.VARCHAR);
            } else {
                pstm.setString(i++, getStatutAuteur());
            }
            pstm.setString(i++, getNationaliteAuteur());
            pstm.setString(i++, getBioAuteur());
            pstm.setInt(i++, getIdAuteur());
            
            int j = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + j);

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
    }

    public String getStatutAuteur() {
        return statutAuteur;
    }

    public void setStatutAuteur(String statutAuteur) {
        this.statutAuteur = statutAuteur;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }
}
