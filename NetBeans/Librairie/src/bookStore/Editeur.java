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
public class Editeur {

    private String nomEditeur;
    private String logoEditeur;
    private String statutEditeur;

    private Livre livre;

    public Editeur() {
        try {
            setNomEditeur("");
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLogoEditeur("");
        try {
            setStatutEditeur("");
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Editeur(String nomEditeur, String logoEditeur, String statutEditeur) {
        try {
            setNomEditeur(nomEditeur);
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLogoEditeur(logoEditeur);
        try {
            setStatutEditeur(statutEditeur);
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Editeur(String nomEditeur) {
        try {
            setNomEditeur(nomEditeur);
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
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
        } else {
            this.statutEditeur = statutEditeur;
        }
    }
    
    public void deleteStatutEditeur(){
        this.statutEditeur = null;
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
            System.err.println("2) erreur sql : " + ex.getMessage());
        }
    }

    public static ArrayList<Editeur> AffichageEditeur() {

        ArrayList<Editeur> ListeEditeur = new ArrayList();

        SqlManager sql1 = null;
       
            sql1 = new SqlManager();
        

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String req = "select e.nomEditeur,"
                    + " l.titreLivre,"
                    + " e.statutEditeur,"
                    + " e.logoEditeur "
                    + " from EDITEUR e"
                    + " join Livre l"
                    + " on e.nomEditeur = l.nomEditeur"
                    + " order by nomEditeur";

            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Editeur editeur = new Editeur(rs.getString("nomEditeur"));

                editeur.setLogoEditeur(rs.getString("logoEditeur"));
                if (rs.getString("statutEditeur")!=null){
                    editeur.setStatutEditeur(rs.getString("statutEditeur"));
                }
                
                Livre newlivre = new Livre();
                newlivre.setTitreLivre(rs.getString("titreLivre"));
                newlivre.setEditeur(editeur);
                
                ListeEditeur.add(editeur);

            }

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Editeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListeEditeur;
    }

    public void CreerEditeur() {

        SqlManager sql1 = null;
 
            sql1 = new SqlManager();
        
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
            System.err.println("2) erreur sql : " + ex.getMessage());
        }

    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Editeur{" + "nomEditeur=" + nomEditeur + ", logoEditeur=" + logoEditeur + ", statutEditeur=" + statutEditeur + ", livre=" + livre + '}';
    }

    
    
}
