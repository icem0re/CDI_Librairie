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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cdi407
 */
public class Editeur {

    private String nomEditeur;
    private String logoEditeur;
    private String statutEditeur;

    public Editeur() {
    }

    public Editeur(String nomEditeur) throws Exception {
        setNomEditeur(nomEditeur);
        getSqlData();
    }

    public Editeur(String nomEditeur, String logoEditeur, String statutEditeur) throws Exception {
        try {
            setNomEditeur(nomEditeur);
            setLogoEditeur(logoEditeur);
            setStatutEditeur(statutEditeur);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void getSqlData() throws SQLException, Exception {

        String req = "SELECT "
                    + " nomEditeur, "
                    + " logoEditeur, "
                    + " statutEditeur "
                    + " FROM "
                    + " Editeur"
                    + " WHERE nomEditeur = ?";
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);
            ) {
            
            stm.setString(1, getNomEditeur());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                setNomEditeur(rs.getString("nomEditeur"));
                setLogoEditeur(rs.getString("logoEditeur"));
                setStatutEditeur(rs.getString("statutEditeur"));
                
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    public void setNomEditeur(String nomEditeur) throws Exception {
        String MonRegex = "[0-9]";
        if(nomEditeur.matches(MonRegex)){
            throw new Exception("Le nom de l'Editeur n'est pas valide !");
        }
        else {
            this.nomEditeur = nomEditeur;
        }
    }

    public String getLogoEditeur() {
        return logoEditeur;
    }

    public void setLogoEditeur(String logoEditeur) {
        this.logoEditeur = logoEditeur;
    }

    public String getStatutEditeur() {
        return statutEditeur;
    }

    public void setStatutEditeur(String statutEditeur) throws Exception {
        if (statutEditeur.length() > 30) {
            throw new Exception("30 caracteres maximum");
        }
        else {
            this.statutEditeur = statutEditeur;
        }
    }
    
    public void deleteStatutEditeur(){
        this.statutEditeur = null;
    }
    
    public void deleteEditeur() throws SQLException, Exception {
        
        SqlManager sql1 = null;
        
            sql1 = new SqlManager();
       

        String req = " UPDATE Editeur"
                + " SET "
                + " statutEditeur = EdiIna"
                + " from Editeur "
                + " where nomEditeur = ?";

        try (Connection cnt2 = sql1.GetConnection();
                PreparedStatement pstm2 = cnt2.prepareStatement(req);) {
            int i = 1;
            Editeur editeur = new Editeur();
            editeur.deleteStatutEditeur();
            if (editeur.getStatutEditeur() == null){
                pstm2.setString(i++,""+ java.sql.Types.NULL);
            } else {
                pstm2.setString(i++, editeur.getStatutEditeur());
            }
            pstm2.setString(i++, getNomEditeur());
            
            
            
            int j = pstm2.executeUpdate();
            System.out.println("nombre de lignes affectées : " + j);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void UpdateEditeur() throws SQLException, Exception {
        
        SqlManager sql1 = null;
        
            sql1 = new SqlManager();
       

        String req = " UPDATE Editeur"
                + " SET "
                + " nomEditeur = ?,"
                + " logoEditeur = ?,"
                + " statutEditeur = ?"
                + " from Editeur "
                + " where nomEditeur = ?";

        try (Connection cnt2 = sql1.GetConnection();
                PreparedStatement pstm2 = cnt2.prepareStatement(req);) {
            int i = 1;
            pstm2.setString(i++, getNomEditeur());
            pstm2.setString(i++, getLogoEditeur());
            if (getStatutEditeur() == null){
                pstm2.setNull(i++, java.sql.Types.VARCHAR);
            } else {
                pstm2.setString(i++, getStatutEditeur());
            }
            pstm2.setString(i++, getNomEditeur());
            
            
            
            int j = pstm2.executeUpdate();
            System.out.println("nombre de lignes affectées : " + j);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Editeur> AffichageEditeur() {

        ArrayList<Editeur> ListeEditeur = new ArrayList();

        SqlManager sql1 = new SqlManager();
        

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select nomEditeur,"
                    + " logoEditeur,"
                    + " statutEditeur "
                    + " FROM Editeur "
                    + " order by nomEditeur";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Editeur editeur = new Editeur();
                editeur.setNomEditeur(rs.getString("nomEditeur"));
                editeur.setLogoEditeur(rs.getString("logoEditeur"));
                editeur.setStatutEditeur(rs.getString("statutEditeur"));
                
                ListeEditeur.add(editeur);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListeEditeur;
    }

    public static ArrayList<Editeur> AffichageStatutEditeur() {

        ArrayList<Editeur> ListeEditeur = new ArrayList();

        SqlManager sql1 = new SqlManager();
        

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select nomEditeur,"
                    + " logoEditeur,"
                    + " statutEditeur "
                    + " FROM Editeur "
                    + " order by statutEditeur";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Editeur editeur = new Editeur();
                editeur.setNomEditeur(rs.getString("nomEditeur"));
                editeur.setLogoEditeur(rs.getString("logoEditeur"));
                editeur.setStatutEditeur(rs.getString("statutEditeur"));
                
                ListeEditeur.add(editeur);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListeEditeur;
    }
    
    public void CreerEditeur() {

        SqlManager sql1 = new SqlManager();
        
        String req = "insert into Editeur"
                + "("
                + " nomEditeur,"
                + " logoEditeur,"
                + " statutEditeur"
                + ")"
                + "values(?,?,?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setString(1, getNomEditeur());
            pstm.setString(2, getLogoEditeur());
            pstm.setString(3, getStatutEditeur());

            int i = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + i);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public String toString() {
        return nomEditeur ;
    }

}
