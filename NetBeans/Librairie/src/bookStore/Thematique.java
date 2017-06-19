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

    public Thematique(String nomThematique) {
        this.nomThematique = nomThematique;
    }

    public Thematique(String idSousThematique, String nomSousThematique, String descriptionSousThematique) {
        this.idSousThematique = idSousThematique;
        this.nomSousThematique = nomSousThematique;
        this.descriptionSousThematique = descriptionSousThematique;
    }

    public Thematique(String nomThematique, String idSousThematique, String nomSousThematique, String descriptionSousThematique) {
        this.nomThematique = nomThematique;
        this.idSousThematique = idSousThematique;
        this.nomSousThematique = nomSousThematique;
        this.descriptionSousThematique = descriptionSousThematique;
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
        try {
            sql1 = new SqlManager();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }

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

                System.out.println("Nom Thematique = " + thematique.getNomThematique());

                listeThematique.add(thematique);
            }
            for (int i = 0; i < listeThematique.size(); i++) {
                listeThematique.get(i).toString();
            }
        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
        return listeThematique;
    }

    public static ArrayList<Thematique> AffichageSousThematique() {

        ArrayList<Thematique> listeSousThematique = new ArrayList();

        SqlManager sql1 = null;
        try {
            sql1 = new SqlManager();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Thematique.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select nomThematique,"
                    + " idSousThematique,"
                    + " nomSousThematique,"
                    + " descriptionSousThematique "
                    + " from SousThematique"
                    + " ORDER BY nomThematique ";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Thematique thematique = new Thematique();
                thematique.setIdSousThematique(rs.getString("idSousThematique"));
                thematique.setNomSousThematique(rs.getString("nomSousThematique"));
                thematique.setNomThematique(rs.getString("nomThematique"));
                thematique.setDescriptionSousThematique(rs.getString("descriptionSousThematique"));

                System.out.println("ID Sous Thematique : " + thematique.getIdSousThematique() + "\t Nom Thematique & Sous-Thematique : " + thematique.getNomThematique() + " " + thematique.getNomSousThematique() + "\t\t Description :  " + thematique.getDescriptionSousThematique());

                listeSousThematique.add(thematique);
            }
            for (int i = 0; i < listeSousThematique.size(); i++) {
                listeSousThematique.get(i).toString();
            }
        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
        return listeSousThematique;
    }

    public void CreerThematique() {

        SqlManager sql1 = null;
        try {
            sql1 = new SqlManager();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.err.println("2) erreur sql : " + ex.getMessage());
        }

    }

    public void CreerSousThematique() {

        SqlManager sql1 = null;
        try {
            sql1 = new SqlManager();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.err.println("2) erreur sql : " + ex.getMessage());
        }

    }
}
