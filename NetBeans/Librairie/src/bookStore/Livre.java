/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookStore;

import Exception.InvalidSearchException;
import SqlManager.SqlManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

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
    
    private ArrayList<Auteur> mesAuteur;
    private ArrayList<Thematique> mesThematique;

    public Livre() {
        editeur = new Editeur();
        mesAuteur = new ArrayList();
        mesThematique = new ArrayList();
    }

    public Livre(String isbnLivre) throws SQLException, Exception{
        this();
        setIsbnLivre(isbnLivre);
        getSqlData();
        
    }
    
    public void getSqlData() throws SQLException, Exception {

        String req = " SELECT liv.isbnLivre, "
                    + "     liv.nomTVA, "
                    + "     liv.nomEditeur, "
                    + "     liv.titreLivre, "
                    + "     liv.sousTitreLivre, "
                    + "     liv.dateParutionLivre, "
                    + "     liv.resumeLivre, "
                    + "     liv.extraitLivre, "
                    + "     liv.imageLivre, "
                    + "     liv.prixHTLivre, "
                    + "     liv.poidLivre, "
                    + "     liv.affichageLivre,"
                    + "     gen.idSousThematique"
                    + " FROM "
                    + "     Livre liv"
                    + " JOIN Genre gen"
                    + "     ON liv.isbnLivre = gen.isbnLivre"
                    + " WHERE "
                    + "     liv.isbnLivre = ?";
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);

            ) {
            
            stm.setString(1, getIsbnLivre());
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                
                // Définition proprièté du livre
                setIsbnLivre(rs.getString("isbnLivre"));
                setNomTVA(rs.getString("nomTVA"));
                setTitreLivre(rs.getString("titreLivre"));
                setSousTitreLivre(rs.getString("sousTitreLivre"));
                setDateParutionLivre((rs.getDate("dateParutionLivre")).toLocalDate());
                setResumeLivre(rs.getString("resumeLivre"));
                setExtraitLivre(rs.getString("extraitLivre"));
                setImageLivre(rs.getString("imageLivre"));
                setPrixHTLivre(rs.getFloat("prixHTLivre"));
                setPoidLivre(rs.getInt("poidLivre"));
                setAffichageLivre(rs.getBoolean("affichageLivre"));
                
                // Définition liste auteur du livre
                mesAuteur.addAll(Auteur.AffichageAuteur(rs.getString("isbnLivre")));
                
                // Définition Editeur du livre
                setEditeur(new Editeur(rs.getString("nomEditeur")));
                
                // Définition des thématique du livre
                mesThematique.addAll(Thematique.getThematiqueLivre(rs.getString("isbnLivre")));
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    
    
    public void UpdateLivre2() throws SQLException, Exception {
        
        SqlManager sql1 = new SqlManager();
       

        String req = " UPDATE Livre"
                + " SET "
                + " nomTVA = ?,"
                + " nomEditeur = ?,"
                + " dateParutionLivre = ?,"
                + " titreLivre = ?, "
                + " sousTitreLivre = ?, "
                + " resumeLivre = ?, "
                + " extraitLivre = ?, "
                + " imageLivre = ?, "
                + " prixHTLivre = ?, "
                + " poidLivre = ?, "
                + " affichageLivre = ? "
                + " FROM Livre "
                + " WHERE isbnLivre = ?";

        try (Connection cnt2 = sql1.GetConnection();
                PreparedStatement pstm2 = cnt2.prepareStatement(req);) {
            java.sql.Date d;
            
            int i = 1;
            pstm2.setString(i++, getNomTVA());
            pstm2.setString(i++, getEditeur().getNomEditeur());
            pstm2.setDate(i++, d = java.sql.Date.valueOf(this.getDateParutionLivre()));
            pstm2.setString(i++, getTitreLivre());
            pstm2.setString(i++, getSousTitreLivre());
            pstm2.setString(i++, getResumeLivre());
            pstm2.setString(i++, getExtraitLivre());
            pstm2.setString(i++, getImageLivre());
            pstm2.setFloat(i++, getPrixHTLivre());
            pstm2.setInt(i++, getPoidLivre());
            pstm2.setBoolean(i++, isAffichageLivre());
            pstm2.setString(i++, getIsbnLivre());
            
            pstm2.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    @Override
    public String toString() {
        //return "Livre{" + "isbnLivre=" + isbnLivre + ",\n titreLivre=" + titreLivre + ",\n sousTitreLivre=" + sousTitreLivre + ",\n dateParutionLivre=" + dateParutionLivre + ",\n resumeLivre=" + resumeLivre + ",\n extraitLivre=" + extraitLivre + ",\n imageLivre=" + imageLivre + ",\n prixHTLivre=" + prixHTLivre + ",\n nomTVA=" + nomTVA + ",\n poidLivre=" + poidLivre + ",\n affichageLivre=" + affichageLivre + ",\n editeur=" + editeur + ",\n mesAuteur=" + mesAuteur + ",\n mesThematique=" + mesThematique + '}';
        return "TITRE : " + getTitreLivre() + "\t ISBN : " + getIsbnLivre();
    }

    public String toString2() {
        return "Livre{" + "isbnLivre=" + isbnLivre + ",\n titreLivre=" + titreLivre + ",\n sousTitreLivre=" + sousTitreLivre + ",\n dateParutionLivre=" + dateParutionLivre + ",\n resumeLivre=" + resumeLivre + ",\n extraitLivre=" + extraitLivre + ",\n imageLivre=" + imageLivre + ",\n prixHTLivre=" + prixHTLivre + ",\n nomTVA=" + nomTVA + ",\n poidLivre=" + poidLivre + ",\n affichageLivre=" + affichageLivre + ",\n editeur=" + editeur + ",\n mesAuteur=" + mesAuteur + ",\n mesThematique=" + mesThematique + '}';
        //return "TITRE : " + getTitreLivre() + "\t ISBN : " + getIsbnLivre();
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
        }
        this.titreLivre = titreLivre;
        
    }

    public String getSousTitreLivre() {
        return sousTitreLivre;
    }

    public void setSousTitreLivre(String sousTitreLivre) throws Exception {
        if (sousTitreLivre.length() > 50) {
            throw new Exception("Longueur sous-titre comprise entre 1 et 50 characteres !!!");
        }
        this.sousTitreLivre = sousTitreLivre;
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
        }
        this.prixHTLivre = prixHTLivre;
        
    }

    public int getPoidLivre() {
        return poidLivre;
    }

    public void setPoidLivre(int poidLivre) throws Exception {
        if (poidLivre < 0) {
            throw new Exception("Le poid du livre ne peut etre negatif");
        }
        this.poidLivre = poidLivre;
        
    }

    public boolean isAffichageLivre() {
        return affichageLivre;
    }

    public void setAffichageLivre(boolean affichageLivre) {
        this.affichageLivre = affichageLivre;
    }

    public static ArrayList<Livre> AffichageLivre() throws SQLException, Exception {

        ArrayList<Livre> Biblio = new ArrayList();

        SqlManager sql1 = new SqlManager();

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "SELECT liv.isbnLivre, "
                    + "     liv.nomTVA, "
                    + "     liv.nomEditeur, "
                    + "     liv.titreLivre, "
                    + "     liv.sousTitreLivre, "
                    + "     liv.dateParutionLivre, "
                    + "     liv.resumeLivre, "
                    + "     liv.extraitLivre, "
                    + "     liv.imageLivre, "
                    + "     liv.prixHTLivre, "
                    + "     liv.poidLivre, "
                    + "     liv.affichageLivre,"
                    + "     gen.idSousThematique"
                    + " FROM "
                    + "     Livre liv"
                    + " JOIN Genre gen"
                    + "     ON liv.isbnLivre = gen.isbnLivre";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                
                // Définition proprièté du livre
//                Livre newLivre = new Livre();
//                
//                newLivre.setIsbnLivre(rs.getString("isbnLivre"));
//                newLivre.setNomTVA(rs.getString("nomTVA"));
//                newLivre.setTitreLivre(rs.getString("titreLivre"));
//                newLivre.setSousTitreLivre(rs.getString("sousTitreLivre"));
//                newLivre.setDateParutionLivre((rs.getDate("dateParutionLivre")).toLocalDate());
//                newLivre.setResumeLivre(rs.getString("resumeLivre"));
//                newLivre.setExtraitLivre(rs.getString("extraitLivre"));
//                newLivre.setImageLivre(rs.getString("imageLivre"));
//                newLivre.setPrixHTLivre(rs.getFloat("prixHTLivre"));
//                newLivre.setPoidLivre(rs.getInt("poidLivre"));
//                newLivre.setAffichageLivre(rs.getBoolean("affichageLivre"));
//                
//                // Définition liste auteur du livre
//               newLivre.setMesAuteur(Auteur.AffichageAuteur(rs.getString("isbnLivre")));
//                
//                // Définition Editeur du livre
//                newLivre.setEditeur(new Editeur(rs.getString("nomEditeur")));
//                
//                // Définition des thématique du livre
//                newLivre.setMesThematique(Thematique.getThematiqueLivre(rs.getString("isbnLivre")));
//
//
//                Biblio.add(newLivre);
                
                Biblio.add(new Livre(rs.getString("isbnLivre")));

            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return Biblio;
    }
   
     public static ArrayList<Livre> searchLivres(String byISBN, 
            String byTitre, 
            String bySousTitre, 
            String byEditeur, 
            String byNomAuteur, 
            String byPrenomAuteur) throws SQLException, Exception {

        ArrayList<Livre> Biblio = new ArrayList();
        Boolean hasValidSearch = false;
        
        String req = "SELECT "
                    + "     l.isbnLivre, "
                    + "     l.titreLivre, "
                    + "     e.nomEditeur, "
                    + "     e.logoEditeur, "
                    + "     e.statutEditeur, "
                    + "     a.nomAuteur, "
                    + "     a.prenomAuteur,"
                    + "     a.idAuteur"
                    + " FROM "
                    + "     LIVRE l "
                    + " JOIN Editeur e "
                    + "     ON l.nomEditeur = e.nomEditeur "
                    + " JOIN Redaction red "
                    + "     ON l.isbnLivre = red.isbnLivre "
                    + " JOIN Auteur a "
                    + "     ON red.idAuteur = a.idAuteur ";
        
        if (byNomAuteur != null){
            byNomAuteur = byNomAuteur.replaceAll("\\*", "%");
            req = req + " AND a.nomAuteur like ? ";
            hasValidSearch = true;
        }
        if (byPrenomAuteur != null){
            byPrenomAuteur = byPrenomAuteur.replaceAll("\\*", "%");
            req = req + " AND a.prenomAuteur like ? ";
            hasValidSearch = true;
        }
        
        req = req   + " WHERE ";
        
        if (byISBN != null){
            byISBN = byISBN.replaceAll("\\*", "%");
            req = req + " l.isbnLivre like ? AND ";
            hasValidSearch = true;
        }
        if (byTitre != null){
            byTitre = byTitre.replaceAll("\\*", "%");
            req = req + " l.titreLivre like ? AND ";
            hasValidSearch = true;
        }
        if (bySousTitre != null){
            bySousTitre = bySousTitre.replaceAll("\\*", "%");
            req = req + " l.sousTitreLivre like ? AND ";
            hasValidSearch = true;
        }
        if (byEditeur != null){
            byEditeur = byEditeur.replaceAll("\\*", "%");
            req = req + " l.nomEditeur like ? AND ";
            hasValidSearch = true;
        }
        
        if (!hasValidSearch){
            throw new InvalidSearchException("Merci de fournir moins un champs de recherche");
        }
        
        // Nettoyage fin de requete
        if (("AND ").equals(req.substring(req.length()-4))){
            req = req.substring(0, req.length()-("AND ").length());
        } else {
            req = req.substring(0, req.length()-(" WHERE ").length());
        }
        //System.out.println(req);
        
        try (Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ) 
        {

            int i = 1;
            if (byNomAuteur != null) {
                pstm.setString(i++, byNomAuteur);
            }
            if (byPrenomAuteur != null) {
                pstm.setString(i++, byPrenomAuteur);
            }
            if (byISBN != null) {
                pstm.setString(i++, byISBN);
            }
            if (byTitre != null) {
                pstm.setString(i++, byTitre);
            }
            if (bySousTitre != null) {
                pstm.setString(i++, bySousTitre);
            }
            if (byEditeur != null) {
                pstm.setString(i++, byEditeur);
            }

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
//                Livre livre = new Livre(rs.getString("isbnLivre"));
//                Biblio.add(livre);
                
                Livre livre = new Livre();

                Auteur auteur = new Auteur(rs.getInt("idAuteur"));
                auteur.setPrenomAuteur(rs.getString("prenomAuteur"));
                auteur.setNomAuteur(rs.getString("nomAuteur"));
                livre.setIsbnLivre(rs.getString("isbnLivre"));
                Editeur newEditeur = new Editeur(rs.getString("nomEditeur"));
                newEditeur.setLogoEditeur(rs.getString("logoEditeur"));
                newEditeur.setStatutEditeur(rs.getString("statutEditeur"));
                livre.setEditeur(newEditeur);
                livre.setTitreLivre(rs.getString("titreLivre"));
                Biblio.add(livre);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return Biblio;
    }
    
    public void CreerLivre() throws SQLException {

        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        String req = "insert into Livre"
                + "("
                + "     isbnLivre, "
                + "     nomTVA, "
                + "     nomEditeur, "
                + "     titreLivre, "
                + "     sousTitreLivre, "
                + "     dateParutionLivre, "
                + "     resumeLivre, "
                + "     extraitLivre, "
                + "     imageLivre, "
                + "     prixHTLivre, "
                + "     poidLivre, "
                + "     affichageLivre"
                + ")"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            java.sql.Date d = java.sql.Date.valueOf(this.getDateParutionLivre());

            int i = 1;
            pstm.setString(i++, getIsbnLivre());
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

            pstm.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }

    }
    
    public void deleteLivre() throws SQLException{
        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        
        String req = " Update Livre "
                + " SET "
                + " affichageLivre = 'false'"
                + " where isbnLivre = ?";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            int i = 1;

            pstm.setString(i++, getIsbnLivre());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public void UpdateLivre() throws SQLException{
        SqlManager sql1 = new SqlManager();   
        
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
            
            pstm.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
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

    public ArrayList<Auteur> getMesAuteur() {
        return mesAuteur;
    }

    public void setMesAuteur(ArrayList<Auteur> mesAuteur) {
        this.mesAuteur = mesAuteur;
    }

    public ArrayList<Thematique> getMesThematique() {
        return mesThematique;
    }

    public void setMesThematique(ArrayList<Thematique> mesThematique) {
        this.mesThematique = mesThematique;
    }

}
