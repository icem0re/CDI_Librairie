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
public class Livre {

    private String isbnLivre = "";
    private String titreLivre = "";
    private String sousTitreLivre = "";
    private LocalDate dateParutionLivre;
    private String resumeLivre = "";
    private String extraitLivre = "";
    private String imageLivre = "";
    private float prixHTLivre;
    private String nomTVA = "";
    private int poidLivre;
    private boolean affichageLivre;

    private Editeur editeur;

    public Livre() {
        editeur = new Editeur();
    }
    
    public Livre(String isbnLivre){
        try {
            setIsbnLivre(isbnLivre);
            getSqlData();
        } catch (Exception ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Livre getSqlData(){
        
        SqlManager sql1 = null;
        Livre livre = new Livre();

            sql1 = new SqlManager();
       

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {
            
            String req = "SELECT isbnLivre, "
                    + " nomTVA, "
                    + " nomEditeur, "
                    + " titreLivre, "
                    + " sousTitreLivre, "
                    + " dateParutionLivre, "
                    + " resumeLivre, "
                    + " extraitLivre, "
                    + " imageLivre, "
                    + " prixHTLivre, "
                    + " poidLivre, "
                    + " affichageLivre "
                    + " FROM "
                    + " Livre"
                    + " where isbnLivre = ?";
            
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {

                livre.setIsbnLivre(rs.getString("isbnLivre"));
                livre.setNomTVA(rs.getString("nomTVA"));
                    Editeur newEditeur = new Editeur(rs.getString("nomEditeur"));
                    newEditeur.setLogoEditeur(rs.getString("logoEditeur"));
                    newEditeur.setStatutEditeur(rs.getString("statutEditeur"));
                livre.setEditeur(newEditeur);
                livre.setTitreLivre(rs.getString("titreLivre"));
                livre.setSousTitreLivre(rs.getString("sousTitreLivre"));
                livre.setDateParutionLivre((rs.getDate("dateParutionLivre")).toLocalDate());
                livre.setResumeLivre(rs.getString("resumeLivre"));
                livre.setExtraitLivre(rs.getString("extraitLivre"));
                livre.setImageLivre(rs.getString("imageLivre"));
                livre.setPrixHTLivre(rs.getFloat("prixHTLivre"));
                livre.setPoidLivre(rs.getInt("poidLivre"));
                livre.setAffichageLivre(rs.getBoolean("affichageLivre"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livre;
    }
    
    
    
    public Livre(String nomThematique, String nomSousThematique, String nomAuteur){
        
    }

    @Override
    public String toString() {
        return "Livre{" + "isbnLivre=" + isbnLivre + ", titreLivre=" + titreLivre + ", sousTitreLivre=" + sousTitreLivre + ", dateParutionLivre=" + dateParutionLivre + ", resumeLivre=" + resumeLivre + ", extraitLivre=" + extraitLivre + ", imageLivre=" + imageLivre + ", prixHTLivre=" + prixHTLivre + ", nomTVA=" + nomTVA + ", poidLivre=" + poidLivre + ", affichageLivre=" + affichageLivre + ", editeur=" + editeur + '}';
    }

    public String getIsbnLivre() {
        return isbnLivre;
    }

    /**
     * verification de l'ISBN a 13 chiffres et a 10 chiffres
     *
     * @param isbnLivre
     */
    public void setIsbnLivre(String isbnLivre) throws Exception {
        if (isbnLivre.length() == 13) {
            String monRegex = "978[0-9]{9}[0-9X]{1}";
            if (isbnLivre.matches(monRegex)) {
                this.isbnLivre = isbnLivre;
            } else if (!isbnLivre.matches(monRegex)) {
                throw new Exception("Les numeros ISBN a 13 chiffres commencent obligatoirement par '978'");
            }
        }
        if (isbnLivre.length() == 10) {
            String monRegex = "[0-9]{9}[0-9X]{1}";
            if (isbnLivre.matches(monRegex)) {
                this.isbnLivre = isbnLivre;
            } else if (!isbnLivre.matches(monRegex)) {
                throw new Exception("Les numeros ISBN a 10 chiffres ne contienne aucune lettre sauf la 10eme qui peut etre un 'X'!");
            }
        }
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) throws Exception {
        if (titreLivre.length() < 1) {
            throw new Exception("Longueur titre comprise entre 1 et 50 characteres !!!");
        } else if (titreLivre.length() > 50) {
            throw new Exception("Longueur titre comprise entre 1 et 50 characteres !!!");
        } else {
            this.titreLivre = titreLivre;
        }
    }

    public String getSousTitreLivre() {
        return sousTitreLivre;
    }

    public void setSousTitreLivre(String sousTitreLivre) throws Exception {
        if (sousTitreLivre.length() < 1) {
            throw new Exception("Longueur sous-titre comprise entre 1 et 50 characteres !!!");
        } else if (sousTitreLivre.length() > 50) {
            throw new Exception("Longueur sous-titre comprise entre 1 et 50 characteres !!!");
        } else {
            this.sousTitreLivre = sousTitreLivre;
        }

    }

    public LocalDate getDateParutionLivre() {
        return dateParutionLivre;
    }

    public void setDateParutionLivre(LocalDate dateParutionLivre) {
        this.dateParutionLivre = dateParutionLivre;
    }

    public String getResumeLivre() {
        return resumeLivre;
    }

    public void setResumeLivre(String resumeLivre) {
        this.resumeLivre = resumeLivre;
    }

    public String getExtraitLivre() {
        return extraitLivre;
    }

    public void setExtraitLivre(String extraitLivre) {
        this.extraitLivre = extraitLivre;
    }

    public String getImageLivre() {
        return imageLivre;
    }

    public void setImageLivre(String imageLivre) {
        this.imageLivre = imageLivre;
    }

    public float getPrixHTLivre() {
        return prixHTLivre;
    }

    public void setPrixHTLivre(float prixHTLivre) throws Exception {
        if (prixHTLivre < 0) {
            throw new Exception("Le prix ne peut etre négatif");
        } else {
            this.prixHTLivre = prixHTLivre;
        }
    }

    public int getPoidLivre() {
        return poidLivre;
    }

    public void setPoidLivre(int poidLivre) throws Exception {
        if (poidLivre < 0) {
            throw new Exception("Le poid du livre ne peut etre negatif");
        } else {
            this.poidLivre = poidLivre;
        }
    }

    public boolean isAffichageLivre() {
        return affichageLivre;
    }

    public void setAffichageLivre(boolean affichageLivre) {
        this.affichageLivre = affichageLivre;
    }

    public static ArrayList<Livre> AffichageLivre() {

        ArrayList<Livre> Biblio = new ArrayList();

        SqlManager sql1 = null;

            sql1 = new SqlManager();
       

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select l.isbnLivre, "
                    + " l.titreLivre, "
                    + " e.nomEditeur, "
                    + " e.logoEditeur, "
                    + " e.statutEditeur, "
                    + " a.nomAuteur, "
                    + " a.prenomAuteur"
                    + " from LIVRE l "
                    + " join Editeur e "
                    + " on l.nomEditeur = e.nomEditeur"
                    + " join Redaction red "
                    + " on l.isbnLivre = red.isbnLivre"
                    + " join Auteur a "
                    + " on red.idAuteur = a.idAuteur ";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Livre livre = new Livre();

                Auteur auteur = new Auteur(rs.getString("nomAuteur"), rs.getString("prenomAuteur"));
                livre.setIsbnLivre(rs.getString("isbnLivre"));
                Editeur newEditeur = new Editeur(rs.getString("nomEditeur"));
                newEditeur.setLogoEditeur(rs.getString("logoEditeur"));
                newEditeur.setStatutEditeur(rs.getString("statutEditeur"));
                livre.setEditeur(newEditeur);
                livre.setTitreLivre(rs.getString("titreLivre"));

                System.out.println("ISBN : " + livre.getIsbnLivre() + "\t Titre : " + livre.getTitreLivre() + "\t Editeur : " + livre.getEditeur().getNomEditeur() + "\t Auteur : " + auteur.getNomAuteur() + " " + auteur.getPrenomAuteur());

                Biblio.add(livre);

            }
            for (int i = 0; i < Biblio.size(); i++) {
                Biblio.get(i).toString();
            }
        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Biblio;
    }

    public void CreerLivre() {

        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        String req = "insert into Livre"
                + "("
                + "isbnLivre, "
                + "nomTVA, "
                + "nomEditeur, "
                + "titreLivre, "
                + "sousTitreLivre, "
                + "dateParutionLivre, "
                + "resumeLivre, "
                + "extraitLivre, "
                + "imageLivre, "
                + "prixHTLivre, "
                + "poidLivre, "
                + "affichageLivre"
                + ")"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            java.sql.Date d = java.sql.Date.valueOf(this.getDateParutionLivre());

            pstm.setString(1, getIsbnLivre());
            pstm.setString(2, getNomTVA());
            pstm.setString(3, getEditeur().getNomEditeur());
            pstm.setString(4, getTitreLivre());
            pstm.setString(5, getSousTitreLivre());
            pstm.setDate(6, d);
            pstm.setString(7, getResumeLivre());
            pstm.setString(8, getExtraitLivre());
            pstm.setString(9, getImageLivre());
            pstm.setFloat(10, getPrixHTLivre());
            pstm.setInt(11, getPoidLivre());
            pstm.setBoolean(12, isAffichageLivre());

            int i = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + i);

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }

    }
    
    public void deleteLivre(){
        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        
        String req = " delete "
                + " from Livre"
                + " where isbnLivre = ?";
        
        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            int i = 1;
            
            pstm.setString(i++, getIsbnLivre());
        
            
        int j = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + j);

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
    }
    
    public void UpdateLivre(){
        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        
        String req = " UPDATE Livre"
                + " SET "
                    + " nomTVA = ?,"
                    + " nomEditeur = ?,"
                    + " titreLivre =?,"
                    + " sousTitreLivre = ?,"
                    + " dateParutionLIvre = ?,"
                    + " resumeLivre = ?,"
                    + " extraitLivre = ?,"
                    + " imageLivre = ?,"
                    + " prixHTLivre = ?,"
                    + " poidLivre = ?,"
                    + " affichageLivre = ?"
                + " FROM "
                    + " Livre"
                + " WHERE "
                    + " isbnLivre = ?";
        
        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            int i = 1;
            
            java.sql.Date d = java.sql.Date.valueOf(getDateParutionLivre());
            
            pstm.setString(i++, getNomTVA());
            pstm.setString(i++, getEditeur().getNomEditeur());
            pstm.setString(i++, getTitreLivre());
            pstm.setString(i++, getSousTitreLivre());
            pstm.setDate(i++, d);
            pstm.setString(i++, getResumeLivre());
            pstm.setString(i++, getExtraitLivre());
            pstm.setString(i++, getImageLivre());
            pstm.setFloat(i++, getPrixHTLivre());
            pstm.setInt(i++, getPoidLivre());
            pstm.setBoolean(i++, isAffichageLivre());
            pstm.setString(i++, getIsbnLivre());
            
            int j = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + j);

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
    }
    
      

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public String getNomTVA() {
        return nomTVA;
    }

    public void setNomTVA(String nomTVA) {
        this.nomTVA = nomTVA;
    }
}
