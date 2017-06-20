/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookStore;

import SqlManager.SqlManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author cdi407
 */
public class jfBookStore extends javax.swing.JFrame {

    /**
     * Creates new form jfBookStore
     */
    Livre livre = new Livre();
    Thematique sousT = new Thematique();

    public jfBookStore() {
        initComponents();
    }

    private DefaultTreeModel initModelBookStore() {
        return new DefaultTreeModel(initTreeBookStore());
    }
    
    private DefaultComboBoxModel initModelThematique() {
        return new DefaultComboBoxModel(initThematique());
    }
    
    private Vector initThematique() {
        Vector v = new Vector();
        
        SqlManager sql1 = null;

            sql1 = new SqlManager();
        
        Connection connexion = null;
        try {
            connexion = sql1.GetConnection();
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
            return v;
        }
        try {
            String query = "SELECT nomSousThematique,"
                    + " nomThematique"
                    + " FROM SousThematique"
                    + " ORDER BY nomSousThematique"
                    + ";";
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {

                v.add(new Thematique(rs.getString("nomSousThematique")));

            }

        return v;
    }   catch (SQLException ex) {
            Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return v;
    }
        
    
    
    
    private DefaultMutableTreeNode initTreeBookStore() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bibliothèque");

        SqlManager sql1 = null;

            sql1 = new SqlManager();
        
        Connection connexion = null;
        try {
            connexion = sql1.GetConnection();
        } catch (SQLException ex) {
            System.err.println("Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage());
            return root;
        }
        try {
            String req = "SELECT "
                    + "     l.isbnLivre,"
                    + "     l.titreLivre,"
                    + "     l.sousTitreLivre,"
                    + "     l.dateParutionLIvre,"
                    + "     l.resumeLIvre,"
                    + "     l.extraitLivre,"
                    + "     l.imageLivre,"
                    + "     l.prixHTLivre,"
                    + "     l.poidLivre,"
                    + "     l.affichageLivre,"
                    + "     a.nomAuteur,"
                    + "     a.prenomAuteur,"
                    + "     e.nomEditeur,"
                    + "     t.nomThematique,"
                    + "     sT.nomSousThematique "
                    + " FROM"
                    + " Livre l"
                    + " JOIN Redaction red"
                    + "     ON l.isbnLivre = red.isbnLivre"
                    + " JOIN Auteur a"
                    + "     ON red.idAuteur = a.idAuteur"
                    + " JOIN Editeur e"
                    + "     ON l.nomEditeur = e.nomEditeur"
                    + " JOIN Genre g"
                    + "     ON l.isbnLivre = g.isbnLivre"
                    + " JOIN SousThematique sT"
                    + "     ON g.idSousThematique = sT.idSousThematique"
                    + " JOIN Thematique t"
                    + "     ON sT.nomThematique = t.nomThematique"
                    + " ORDER BY nomThematique"
                    + ";";

            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(req);

            String lastNomThematique = null;
            String lastNomSousThematique = null;
            String lastAuteur = null;

            DefaultMutableTreeNode tnNomThematique = null;
            DefaultMutableTreeNode tnNomSousThematique = null;
            DefaultMutableTreeNode tnAuteur = null;

            while (rs.next()) {

                if (lastNomThematique == null) {
                    tnNomThematique = new DefaultMutableTreeNode(rs.getString("nomThematique"));
                    root.add(tnNomThematique);
                    lastNomThematique = rs.getString("nomThematique");
                    System.out.println(lastNomThematique);
                }
                if (!lastNomThematique.equalsIgnoreCase(rs.getString("nomThematique"))) {
                    root.add(tnNomThematique = new DefaultMutableTreeNode(rs.getString("nomThematique")));
                    lastNomThematique = rs.getString("nomThematique");
                    System.out.println(lastNomThematique);
                }
                if (lastNomSousThematique == null) {
                    lastNomSousThematique = rs.getString("NomSousThematique");
                    tnNomThematique.add(tnNomSousThematique = new DefaultMutableTreeNode(lastNomSousThematique));
                    System.out.println(lastNomSousThematique);

                }
                if (!lastNomSousThematique.equalsIgnoreCase(rs.getString("NomSousThematique"))) {
                    lastNomSousThematique = rs.getString("NomSousThematique");
                    tnNomThematique.add(tnNomSousThematique = new DefaultMutableTreeNode(lastNomSousThematique));
                    System.out.println(lastNomSousThematique);
                }
                if (lastAuteur == null) {
                    lastNomSousThematique = rs.getString("nomAuteur");
                    tnNomSousThematique.add(tnAuteur = new DefaultMutableTreeNode(lastAuteur));
                    System.out.println(lastAuteur);

                }
                if (!lastAuteur.equalsIgnoreCase(rs.getString("nomAuteur"))) {
                    lastNomSousThematique = rs.getString("nomAuteur");
                    tnNomSousThematique.add(tnAuteur = new DefaultMutableTreeNode(lastAuteur));
                    System.out.println(lastAuteur);
                }

                Livre livre = new Livre();
                Auteur auteur = new Auteur(rs.getString("nomAuteur"), rs.getString("prenomAuteur"));
                Editeur editeur = new Editeur(rs.getString("nomEditeur"));

                livre.setIsbnLivre(rs.getString("isbnLivre"));
                livre.setTitreLivre(rs.getString("titreLivre"));
                livre.setEditeur(editeur);

                tnAuteur.add(new DefaultMutableTreeNode(new Livre(lastNomThematique, lastNomSousThematique, lastAuteur)));
            }

        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeLivre = new javax.swing.JTree();
        jtIsbn = new javax.swing.JTextField();
        jtNomTVA = new javax.swing.JTextField();
        jtNomEditeur = new javax.swing.JTextField();
        jtTitreLivre = new javax.swing.JTextField();
        jtSousTitreLivre = new javax.swing.JTextField();
        jtDateParutionLIvre = new javax.swing.JTextField();
        jtResumeLivre = new javax.swing.JTextField();
        jtExtraitLivre = new javax.swing.JTextField();
        jtPrixHTLivre = new javax.swing.JTextField();
        jtPoidLivre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jCThematique = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTreeLivre.setModel(initModelBookStore());
        jTreeLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeLivreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeLivre);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 11, 260, 490);

        jtIsbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtIsbnActionPerformed(evt);
            }
        });
        getContentPane().add(jtIsbn);
        jtIsbn.setBounds(510, 80, 130, 20);
        getContentPane().add(jtNomTVA);
        jtNomTVA.setBounds(510, 110, 130, 20);
        getContentPane().add(jtNomEditeur);
        jtNomEditeur.setBounds(510, 140, 130, 20);
        getContentPane().add(jtTitreLivre);
        jtTitreLivre.setBounds(510, 170, 130, 20);
        getContentPane().add(jtSousTitreLivre);
        jtSousTitreLivre.setBounds(510, 200, 130, 20);
        getContentPane().add(jtDateParutionLIvre);
        jtDateParutionLIvre.setBounds(510, 230, 130, 20);
        getContentPane().add(jtResumeLivre);
        jtResumeLivre.setBounds(510, 260, 130, 20);
        getContentPane().add(jtExtraitLivre);
        jtExtraitLivre.setBounds(510, 290, 130, 20);
        getContentPane().add(jtPrixHTLivre);
        jtPrixHTLivre.setBounds(510, 320, 130, 20);
        getContentPane().add(jtPoidLivre);
        jtPoidLivre.setBounds(510, 350, 130, 20);

        jLabel1.setText("ISBN : ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(410, 80, 33, 14);

        jLabel2.setText("TVA :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 110, 26, 14);

        jLabel3.setText("Editeur :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(410, 140, 50, 14);

        jLabel4.setText("Titre :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(410, 170, 29, 14);

        jLabel5.setText("Sous Titre :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(410, 200, 60, 14);

        jLabel6.setText("Date de Parution :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(410, 230, 90, 14);

        jLabel7.setText("Resumé :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(410, 260, 60, 14);

        jLabel8.setText("Extrait :");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(410, 290, 50, 14);

        jLabel10.setText("Prix HT :");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(410, 320, 50, 14);

        jLabel11.setText("Poids :");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(410, 350, 32, 14);

        jButton1.setText("OK !");
        getContentPane().add(jButton1);
        jButton1.setBounds(430, 420, 53, 23);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Creer");
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(350, 20, 53, 23);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Editer");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(450, 20, 53, 23);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Effacer");
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(550, 20, 93, 23);

        jCThematique.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCThematique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCThematiqueActionPerformed(evt);
            }
        });
        getContentPane().add(jCThematique);
        jCThematique.setBounds(516, 50, 120, 20);

        setBounds(0, 0, 686, 557);
    }// </editor-fold>//GEN-END:initComponents

    private void jtIsbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtIsbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtIsbnActionPerformed

    private void jTreeLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeLivreMouseClicked
        DefaultMutableTreeNode tn
                = (DefaultMutableTreeNode) jTreeLivre.getLastSelectedPathComponent();
        
        if (tn.getUserObject() instanceof Livre) {
            Livre livre = (Livre) tn.getUserObject();
            
            jtIsbn.setText(livre.getIsbnLivre());
            jtNomTVA.setText(livre.getNomTVA());
            jtNomEditeur.setText(livre.getEditeur().getNomEditeur());
            jtTitreLivre.setText(livre.getTitreLivre());
            jtSousTitreLivre.setText(livre.getSousTitreLivre());
            jtDateParutionLIvre.setText(livre.getDateParutionLivre().toString());
            jtResumeLivre.setText(livre.getResumeLivre());
            jtExtraitLivre.setText(livre.getExtraitLivre());
            jtPrixHTLivre.setText(""+livre.getPrixHTLivre());
            jtPoidLivre.setText(""+livre.getPoidLivre());
            
        } else {
            jtIsbn.setText("Oops");
        }
    }//GEN-LAST:event_jTreeLivreMouseClicked

    private void jCThematiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCThematiqueActionPerformed
       
    }//GEN-LAST:event_jCThematiqueActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jfBookStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jfBookStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jfBookStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jfBookStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfBookStore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCThematique;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTreeLivre;
    private javax.swing.JTextField jtDateParutionLIvre;
    private javax.swing.JTextField jtExtraitLivre;
    private javax.swing.JTextField jtIsbn;
    private javax.swing.JTextField jtNomEditeur;
    private javax.swing.JTextField jtNomTVA;
    private javax.swing.JTextField jtPoidLivre;
    private javax.swing.JTextField jtPrixHTLivre;
    private javax.swing.JTextField jtResumeLivre;
    private javax.swing.JTextField jtSousTitreLivre;
    private javax.swing.JTextField jtTitreLivre;
    // End of variables declaration//GEN-END:variables
}
