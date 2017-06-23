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
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author cdi407
 */
public class Thematique {

    private String nomThematique = "";
    private String idSousThematique;
    private String nomSousThematique = "";
    private String descriptionSousThematique = "";

    public Thematique() {
    }

    public Thematique(String idSousThematique) throws Exception {
        this.idSousThematique = idSousThematique;
        getSqlData();

    }

    public Thematique(String nomThematique, String idSousThematique, String nomSousThematique, String descriptionSousThematique) {
        this.nomThematique = nomThematique;
        this.idSousThematique = idSousThematique;
        this.nomSousThematique = nomSousThematique;
        this.descriptionSousThematique = descriptionSousThematique;
    }
    
    public void getAllThematique() throws SQLException{
        
        String req = "SELECT "
                    + " * "
                    + " FROM "
                    + " SousThematique";
                    
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);
            ) {
            
            ResultSet rs = stm.executeQuery();

        while (rs.next()) {

                setIdSousThematique(rs.getString("idSousThematique"));
                setNomThematique(rs.getString("nomThematique"));
                setNomSousThematique(rs.getString("nomSousThematique"));
                setDescriptionSousThematique(rs.getString("descriptionSousThematique"));
                
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

    
    }
    
    public void getSqlData() throws SQLException, Exception {

        String req = "SELECT "
                    + " idSousThematique, "
                    + " nomThematique, "
                    + " nomSousThematique, "
                    + " descriptionSousThematique "
                    + " FROM "
                    + " SousThematique"
                    + " WHERE idSousThematique = ?";
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);
            ) {
            
            stm.setString(1, getIdSousThematique());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                setIdSousThematique(rs.getString("idSousThematique"));
                setNomThematique(rs.getString("nomThematique"));
                setNomSousThematique(rs.getString("nomSousThematique"));
                setDescriptionSousThematique(rs.getString("descriptionSousThematique"));
                
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public String toString() {
        return "Thematique{" + "nomThematique=" + nomThematique + ",\n idSousThematique=" + idSousThematique + ",\n nomSousThematique=" + nomSousThematique + ",\n descriptionSousThematique=" + descriptionSousThematique + '}';
    }

    public String getNomThematique() {
        return nomThematique;
    }

    public void setNomThematique(String nomThematique) {
        this.nomThematique = nomThematique;
    }

    public String getIdSousThematique() {
        return idSousThematique;
    }

    public void setIdSousThematique(String idSousThematique) {
        this.idSousThematique = idSousThematique;
    }

    public String getNomSousThematique() {
        return nomSousThematique;
    }

    public void setNomSousThematique(String nomSousThematique) {
        this.nomSousThematique = nomSousThematique;
    }

    public String getDescriptionSousThematique() {
        return descriptionSousThematique;
    }

    public void setDescriptionSousThematique(String descriptionSousThematique) {
        this.descriptionSousThematique = descriptionSousThematique;
    }

    public static ArrayList<Thematique> AffichageThematique() {

        ArrayList<Thematique> listeThematique = new ArrayList();

        SqlManager sql1 = null;
 
            sql1 = new SqlManager();
       

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select t.nomThematique, s.nomSousThematique"
                    + " from Thematique t"
                    + " join sousThematique s"
                    + " on t.nomThematique = s.nomThematique"
                    + " ORDER BY nomThematique ";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                
                Thematique thematique = new Thematique();
                thematique.setNomThematique(rs.getString("nomThematique"));

                listeThematique.add(thematique);
            }
            for (int i = 0; i < listeThematique.size(); i++) {
                listeThematique.get(i).toString();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return listeThematique;
    }

    public static ArrayList<Thematique> AffichageSousThematique(String nomThematique) {

        ArrayList<Thematique> listeSousThematique = new ArrayList();
            
       String req = "select idSousThematique,"
                    + " nomThematique,"
                    + " nomSousThematique,"
                    + " descriptionSousThematique "
                    + " from SousThematique"
                    + " where nomThematique = ? ";
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement stm = cnt.prepareStatement(req);

            ) {
            stm.setString(1,nomThematique);
            ResultSet rs = stm.executeQuery();
            

            while (rs.next()) {
                
                Thematique thematique = new Thematique();
                thematique.setIdSousThematique(rs.getString("idSousThematique"));
                thematique.setNomSousThematique(rs.getString("nomSousThematique"));
                thematique.setNomThematique(rs.getString("nomThematique"));
                thematique.setDescriptionSousThematique(rs.getString("descriptionSousThematique"));

                listeSousThematique.add(thematique);
            }
            for (int i = 0; i < listeSousThematique.size(); i++) {
                listeSousThematique.get(i).toString();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return listeSousThematique;
    }
    

    public void CreerThematique() {

        SqlManager sql1 = null;

            sql1 = new SqlManager();
       
        String req = "insert into Thematique"
                + "("
                + "nomThematique"
                + ")"
                + "values(?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setString(1, getNomThematique());

            int i = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + i);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void CreerSousThematique() {

        SqlManager sql1 = null;

            sql1 = new SqlManager();
        
        String req = "insert into SousThematique"
                + "("
                + " idSousThematique,"
                + " nomThematique,"
                + " nomSousThematique,"
                + " descriptionSousThematique"
                + ")"
                + "values(?,?,?,?)";

        try (Connection cnt = sql1.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setString(1, getIdSousThematique());
            pstm.setString(2, getNomThematique());
            pstm.setString(3, getNomSousThematique());
            pstm.setString(4, getDescriptionSousThematique());

            int i = pstm.executeUpdate();
            System.out.println("nombre de lignes affectées : " + i);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
