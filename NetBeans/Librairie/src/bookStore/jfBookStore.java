/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookStore;

import SqlManager.SqlManager;
import com.toedter.calendar.JCalendar;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
    
    public class jif01 extends javax.swing.JInternalFrame {

    private int compteur;
    
    public jif01() {
        initComponents();
        
        this.setTitle("fenetre "+ compteur);
        
        setBounds(10*compteur,10*(compteur++),547,390);
        
        
    }
    }
    
    
    private DefaultComboBoxModel initModelSousThematique() {
        return new DefaultComboBoxModel(initSousThematique());
    }

    private DefaultComboBoxModel initModelSousThematique2() {
        return new DefaultComboBoxModel(initSousThematique2());
    }

    private DefaultComboBoxModel initModelThematique() {
        return new DefaultComboBoxModel(initThematique());
    }
    
    private DefaultComboBoxModel initModelEditeur(){
        return new DefaultComboBoxModel(initEditeur());
    }
    
    private DefaultComboBoxModel initModelStatutEditeur(){
        return new DefaultComboBoxModel(initStatutEditeur());
    }
    
    private DefaultComboBoxModel initModelAuteur(){
        return new DefaultComboBoxModel(initAuteur());
    }
    
    private Vector initStatutEditeur(){
        Vector v = new Vector();
        ArrayList<Editeur> mesEditeurs = Editeur.AffichageStatutEditeur();        
        String lastEditeur = null;
        for (int i = 0; i < mesEditeurs.size(); i++) {
            if(lastEditeur == null){
                lastEditeur = mesEditeurs.get(i).getStatutEditeur();
                Editeur editeur = null;
                try {
                    editeur = new Editeur(lastEditeur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    editeur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(editeur);
            }
            if (!lastEditeur.equalsIgnoreCase(mesEditeurs.get(i).getStatutEditeur())) {
                lastEditeur = mesEditeurs.get(i).getStatutEditeur();
                Editeur editeur = null;
                try {
                    editeur = new Editeur(lastEditeur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    editeur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(editeur);
            }
        }
        
        
        return v;
    }
    
    private Vector initEditeur(){
        Vector v = new Vector();
        ArrayList<Editeur> mesEditeurs = Editeur.AffichageEditeur();
        
        String lastEditeur = null;
        for (int i = 0; i < mesEditeurs.size(); i++) {
            if(lastEditeur == null){
                lastEditeur = mesEditeurs.get(i).getNomEditeur();
                Editeur editeur = null;
                try {
                    editeur = new Editeur(lastEditeur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    editeur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(editeur);
            }
            if (!lastEditeur.equalsIgnoreCase(mesEditeurs.get(i).getNomEditeur())) {
                lastEditeur = mesEditeurs.get(i).getNomEditeur();
                Editeur editeur = null;
                try {
                    editeur = new Editeur(lastEditeur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    editeur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(editeur);
            }
        }
        
        
        return v;
    }

    private Vector initAuteur(){
        Vector v = new Vector();
        ArrayList<Auteur> mesAuteurs = Auteur.AffichageAuteur2();
        
        String lastNomAuteur = null;
        String lastPrenomAuteur = null;
        String lastBioAuteur = null;
        LocalDate lastNaissanceAuteur = null;
        LocalDate lastDecesAuteur = null;
        String lastNationaliteAuteur = null;
        String lastPhotoAuteur = null;
        
        
        for (int i = 0; i < mesAuteurs.size(); i++) {
            if(lastNomAuteur == null){
                lastNomAuteur = mesAuteurs.get(i).getNomAuteur();
                lastPrenomAuteur = mesAuteurs.get(i).getPrenomAuteur();
                lastBioAuteur = mesAuteurs.get(i).getBioAuteur();
                lastNaissanceAuteur = mesAuteurs.get(i).getDateNaissanceAuteur();
                lastDecesAuteur = mesAuteurs.get(i).getDateDecesAuteur();
                lastNationaliteAuteur = mesAuteurs.get(i).getNationaliteAuteur();
                lastPhotoAuteur = mesAuteurs.get(i).getPhotoAuteur();
                Auteur auteur = null;
                try {
                    auteur = new Auteur();
                    auteur.setNomAuteur(lastNomAuteur);
                    auteur.setPrenomAuteur(lastPrenomAuteur);
                    auteur.setBioAuteur(lastBioAuteur);
                    auteur.setDateNaissanceAuteur(lastNaissanceAuteur);
                    auteur.setDateDecesAuteur(lastDecesAuteur);
                    auteur.setNationaliteAuteur(lastNationaliteAuteur);
                    auteur.setPhotoAuteur(lastPhotoAuteur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    auteur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(auteur);
            }
            if (!lastNomAuteur.equalsIgnoreCase(mesAuteurs.get(i).getNomAuteur())) {
                lastNomAuteur = mesAuteurs.get(i).getNomAuteur();
                lastPrenomAuteur = mesAuteurs.get(i).getPrenomAuteur();
                lastBioAuteur = mesAuteurs.get(i).getBioAuteur();
                lastNaissanceAuteur = mesAuteurs.get(i).getDateNaissanceAuteur();
                lastDecesAuteur = mesAuteurs.get(i).getDateDecesAuteur();
                lastNationaliteAuteur = mesAuteurs.get(i).getNationaliteAuteur();
                lastPhotoAuteur = mesAuteurs.get(i).getPhotoAuteur();
                Auteur auteur = null;
                try {
                    auteur = new Auteur();
                    auteur.setNomAuteur(lastNomAuteur);
                    auteur.setPrenomAuteur(lastPrenomAuteur);
                    auteur.setBioAuteur(lastBioAuteur);
                    auteur.setDateNaissanceAuteur(lastNaissanceAuteur);
                    auteur.setDateDecesAuteur(lastDecesAuteur);
                    auteur.setNationaliteAuteur(lastNationaliteAuteur);
                    auteur.setPhotoAuteur(lastPhotoAuteur);
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    auteur.getSqlData();
                } catch (Exception ex) {
                    Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.add(auteur);
            }
        }
        
        
        return v;
    }
    private Vector initThematique() {
        Vector v = new Vector();
        ArrayList<Thematique> mesThematique = Thematique.AffichageThematique();
        String lastThematique = null;
        for (int i = 0; i < mesThematique.size(); i++) {
            if (lastThematique == null) {
                lastThematique = mesThematique.get(i).getNomThematique();
                v.add(lastThematique);
            }
            if (!lastThematique.equalsIgnoreCase(mesThematique.get(i).getNomThematique())) {
                lastThematique = mesThematique.get(i).getNomThematique();
                v.add(lastThematique);
            }
        }
        return v;
    }  

    private Vector initSousThematique() {
        Vector v = new Vector();
        ArrayList<Thematique> mesThematique = null;
        jCThematique.getActionListeners();

        if (mesThematique == null) {
            mesThematique = Thematique.AffichageThematique();
            mesThematique = Thematique.AffichageSousThematique(mesThematique.get(0).getNomThematique());
            String lastThematique = null;
            for (int i = 0; i < mesThematique.size(); i++) {
                if (lastThematique == null) {
                    lastThematique = mesThematique.get(i).getNomSousThematique();
                    v.add(lastThematique);

                }
                if (!lastThematique.equalsIgnoreCase(mesThematique.get(i).getNomSousThematique())) {
                    lastThematique = mesThematique.get(i).getNomSousThematique();
                    v.add(lastThematique);

                }

            }

        }

        return v;
    }

    private Vector initSousThematique2() {
        Vector v = new Vector();
        ArrayList<Thematique> mesThematique = Thematique.AffichageSousThematique(jCThematique.getSelectedItem().toString());
        jCThematique.getActionListeners();

        if (mesThematique != null) {
            String lastThematique = null;
            for (int i = 0; i < mesThematique.size(); i++) {
                if (lastThematique == null) {
                    lastThematique = mesThematique.get(i).getNomSousThematique();
                    v.add(lastThematique);

                }
                if (!lastThematique.equalsIgnoreCase(mesThematique.get(i).getNomSousThematique())) {
                    lastThematique = mesThematique.get(i).getNomSousThematique();
                    v.add(lastThematique);

                }

            }

        }

        return v;
    }

    private DefaultTreeModel initModelBookStore() {
        return new DefaultTreeModel(initTreeCD());
    }

    public static DefaultMutableTreeNode initTreeCD() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bibiothèques");

        SqlManager sql1 = new SqlManager();

        try (Connection cnt = sql1.GetConnection();
                Statement stm = cnt.createStatement();) {

            String query = "SELECT st.nomThematique,"
                    + " l.titreLivre,"
                    + " st.nomSousThematique,"
                    + " a.nomAuteur,"
                    + " a.prenomAuteur,"
                    + " l.isbnLivre"
                    + " FROM SousThematique st"
                    + " JOIN Genre g"
                    + " ON g.idSousThematique = st.idSousThematique"
                    + " JOIN Livre l"
                    + " ON g.isbnLivre = l.isbnLivre"
                    + " JOIN Redaction red"
                    + " ON l.isbnLivre = red.isbnLivre"
                    + " JOIN Auteur a"
                    + " ON red.idAuteur = a.idAuteur"
                    + " ORDER BY st.nomThematique";

            ResultSet rs = stm.executeQuery(query);

            String lastThematique = null;
            String lastSousThematique = null;
            String lastAuteur = null;

            DefaultMutableTreeNode tnThematique = null;
            DefaultMutableTreeNode tnSousThematique = null;
            DefaultMutableTreeNode tnAuteur = null;

            while (rs.next()) {

                if (lastThematique == null) {
                    tnThematique = new DefaultMutableTreeNode(rs.getString("nomThematique"));
                    root.add(tnThematique);
                    lastThematique = rs.getString("nomThematique");
                }
                if (!lastThematique.equalsIgnoreCase(rs.getString("nomThematique"))) {
                    root.add(tnThematique = new DefaultMutableTreeNode(rs.getString("nomThematique")));
                    lastThematique = rs.getString("nomThematique");
                }
                if (lastSousThematique == null) {
                    lastSousThematique = rs.getString("nomSousThematique");
                    tnThematique.add(tnSousThematique = new DefaultMutableTreeNode(lastSousThematique));

                }
                if (!lastSousThematique.equalsIgnoreCase(rs.getString("nomSousThematique"))) {
                    lastSousThematique = rs.getString("nomSousThematique");
                    tnThematique.add(tnSousThematique = new DefaultMutableTreeNode(lastSousThematique));
                }
                if (lastAuteur == null) {
                    lastAuteur = (rs.getString("nomAuteur") + " " + rs.getString("prenomAuteur"));
                    tnSousThematique.add(tnAuteur = new DefaultMutableTreeNode(lastAuteur));
                }
                if (!lastAuteur.equalsIgnoreCase((rs.getString("nomAuteur") + " " + rs.getString("prenomAuteur")))) {
                    lastAuteur = (rs.getString("nomAuteur") + " " + rs.getString("prenomAuteur"));
                    tnSousThematique.add(tnAuteur = new DefaultMutableTreeNode(lastAuteur));
                }
                Livre livre = new Livre(rs.getString("titreLivre"), rs.getString("isbnLivre"));
                livre.selectUnLivre();
                //String s = "TITRE : " + livre.getTitreLivre() + "\t ISBN : " + livre.getIsbnLivre();
                tnAuteur.add(new DefaultMutableTreeNode(livre));
            }

            rs.close();
            stm.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return root;
        } catch (Exception ex) {
            Logger.getLogger(jfBookSt0re.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done!");

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
        jFrame1 = new javax.swing.JFrame();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTnomEditeur2 = new javax.swing.JTextField();
        jTlogoEditeur = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jCEditeur = new javax.swing.JComboBox();
        jCstatutEditeur = new javax.swing.JComboBox();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFrame2 = new javax.swing.JFrame();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLImageAuteur = new javax.swing.JLabel();
        jRcreeAuteur = new javax.swing.JRadioButton();
        jREdiAuteur = new javax.swing.JRadioButton();
        jReffAuteur = new javax.swing.JRadioButton();
        jRselAuteur = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTnomAuteur = new javax.swing.JTextField();
        jTprenomAuteur = new javax.swing.JTextField();
        jTbioAuteur = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTnationaliteAuteur = new javax.swing.JTextField();
        jCAuteurs = new javax.swing.JComboBox();
        jDateChooserAuteurNaissance = new com.toedter.calendar.JDateChooser();
        jDateChooserAuteurDeces = new com.toedter.calendar.JDateChooser();
        buttonGroup4 = new javax.swing.ButtonGroup();
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
        jCSousThematique = new javax.swing.JComboBox();
        jCThematique = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLImage = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jtnomAuteurMain = new javax.swing.JTextField();
        jDateChooserLivre = new com.toedter.calendar.JDateChooser();

        jFrame1.setResizable(false);
        jFrame1.setSize(new java.awt.Dimension(300, 500));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(300, 500));

        jLabel13.setText("Nom :");
        jDesktopPane1.add(jLabel13);
        jLabel13.setBounds(50, 150, 40, 30);

        jLabel14.setText("Logo :");
        jDesktopPane1.add(jLabel14);
        jLabel14.setBounds(50, 190, 30, 30);

        jLabel15.setText("Statut :");
        jDesktopPane1.add(jLabel15);
        jLabel15.setBounds(50, 230, 40, 30);

        jLabel16.setText("LOGO");
        jPanel2.add(jLabel16);

        jDesktopPane1.add(jPanel2);
        jPanel2.setBounds(60, 280, 170, 150);
        jDesktopPane1.add(jTnomEditeur2);
        jTnomEditeur2.setBounds(130, 150, 110, 30);
        jDesktopPane1.add(jTlogoEditeur);
        jTlogoEditeur.setBounds(130, 190, 110, 30);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("EDITEURS");
        jPanel3.add(jLabel12);

        jDesktopPane1.add(jPanel3);
        jPanel3.setBounds(0, 0, 300, 40);

        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jButton4);
        jButton4.setBounds(110, 440, 73, 23);

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Creer");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jRadioButton4);
        jRadioButton4.setBounds(20, 50, 90, 23);

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Editer");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jRadioButton5);
        jRadioButton5.setBounds(180, 50, 80, 23);

        jRadioButton6.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Effacer");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jRadioButton6);
        jRadioButton6.setBounds(180, 80, 80, 23);

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setText("Selectioner");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jRadioButton7);
        jRadioButton7.setBounds(20, 80, 90, 23);

        jCEditeur.setModel(initModelEditeur());
        jCEditeur.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCEditeurItemStateChanged(evt);
            }
        });
        jCEditeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCEditeurActionPerformed(evt);
            }
        });
        jDesktopPane1.add(jCEditeur);
        jCEditeur.setBounds(40, 120, 200, 20);

        jCstatutEditeur.setModel(initModelStatutEditeur());
        jDesktopPane1.add(jCstatutEditeur);
        jCstatutEditeur.setBounds(130, 230, 110, 30);

        jFrame1.getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jFrame2.setMinimumSize(new java.awt.Dimension(300, 500));
        jFrame2.setResizable(false);
        jFrame2.setSize(new java.awt.Dimension(300, 500));

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane2.setPreferredSize(new java.awt.Dimension(300, 500));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel20.setText("Auteurs");
        jPanel5.add(jLabel20);

        jDesktopPane2.add(jPanel5);
        jPanel5.setBounds(0, 0, 600, 50);

        jButton7.setText("Photo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7);
        jPanel6.add(jLImageAuteur);

        jDesktopPane2.add(jPanel6);
        jPanel6.setBounds(310, 240, 250, 210);

        buttonGroup4.add(jRcreeAuteur);
        jRcreeAuteur.setText("Creer");
        jDesktopPane2.add(jRcreeAuteur);
        jRcreeAuteur.setBounds(10, 70, 60, 23);

        buttonGroup4.add(jREdiAuteur);
        jREdiAuteur.setText("Editer");
        jDesktopPane2.add(jREdiAuteur);
        jREdiAuteur.setBounds(160, 70, 60, 23);

        buttonGroup4.add(jReffAuteur);
        jReffAuteur.setText("Effacer");
        jReffAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReffAuteurActionPerformed(evt);
            }
        });
        jDesktopPane2.add(jReffAuteur);
        jReffAuteur.setBounds(330, 70, 70, 23);

        buttonGroup4.add(jRselAuteur);
        jRselAuteur.setText("Selectionner");
        jDesktopPane2.add(jRselAuteur);
        jRselAuteur.setBounds(500, 70, 90, 23);

        jLabel22.setText("Nom :");
        jDesktopPane2.add(jLabel22);
        jLabel22.setBounds(80, 160, 28, 30);

        jLabel23.setText("Prenom :");
        jDesktopPane2.add(jLabel23);
        jLabel23.setBounds(80, 200, 50, 30);

        jLabel24.setText("Date de Naissance :");
        jDesktopPane2.add(jLabel24);
        jLabel24.setBounds(300, 130, 100, 30);

        jLabel25.setText("Bio :");
        jDesktopPane2.add(jLabel25);
        jLabel25.setBounds(140, 270, 30, 30);
        jDesktopPane2.add(jTnomAuteur);
        jTnomAuteur.setBounds(170, 160, 120, 30);
        jDesktopPane2.add(jTprenomAuteur);
        jTprenomAuteur.setBounds(170, 200, 120, 30);
        jDesktopPane2.add(jTbioAuteur);
        jTbioAuteur.setBounds(30, 300, 250, 130);

        jButton6.setText("Ok");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(jButton6);
        jButton6.setBounds(130, 440, 45, 23);

        jLabel19.setText("Date de Deces :");
        jDesktopPane2.add(jLabel19);
        jLabel19.setBounds(320, 180, 90, 30);

        jLabel21.setText("Nationalité :");
        jDesktopPane2.add(jLabel21);
        jLabel21.setBounds(80, 240, 70, 30);
        jDesktopPane2.add(jTnationaliteAuteur);
        jTnationaliteAuteur.setBounds(170, 240, 120, 30);

        jCAuteurs.setModel(initModelAuteur());
        jCAuteurs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCAuteursItemStateChanged(evt);
            }
        });
        jCAuteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCAuteursActionPerformed(evt);
            }
        });
        jDesktopPane2.add(jCAuteurs);
        jCAuteurs.setBounds(30, 110, 230, 20);
        jDesktopPane2.add(jDateChooserAuteurNaissance);
        jDateChooserAuteurNaissance.setBounds(410, 130, 200, 30);
        jDesktopPane2.add(jDateChooserAuteurDeces);
        jDateChooserAuteurDeces.setBounds(410, 180, 200, 30);

        jFrame2.getContentPane().add(jDesktopPane2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(null);

        jTreeLivre.setModel(initModelBookStore());
        jTreeLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeLivreMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTreeLivreMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeLivre);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 11, 260, 410);

        jtIsbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtIsbnActionPerformed(evt);
            }
        });
        getContentPane().add(jtIsbn);
        jtIsbn.setBounds(340, 90, 110, 30);
        getContentPane().add(jtNomTVA);
        jtNomTVA.setBounds(340, 130, 110, 30);
        getContentPane().add(jtNomEditeur);
        jtNomEditeur.setBounds(520, 90, 110, 30);
        getContentPane().add(jtTitreLivre);
        jtTitreLivre.setBounds(520, 130, 110, 30);
        getContentPane().add(jtSousTitreLivre);
        jtSousTitreLivre.setBounds(520, 170, 110, 30);

        jtDateParutionLIvre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDateParutionLIvreActionPerformed(evt);
            }
        });
        getContentPane().add(jtDateParutionLIvre);
        jtDateParutionLIvre.setBounds(520, 210, 110, 30);

        jtResumeLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtResumeLivreActionPerformed(evt);
            }
        });
        getContentPane().add(jtResumeLivre);
        jtResumeLivre.setBounds(350, 290, 330, 40);
        getContentPane().add(jtExtraitLivre);
        jtExtraitLivre.setBounds(350, 340, 330, 40);
        getContentPane().add(jtPrixHTLivre);
        jtPrixHTLivre.setBounds(340, 170, 60, 30);
        getContentPane().add(jtPoidLivre);
        jtPoidLivre.setBounds(340, 210, 60, 30);

        jLabel1.setText("ISBN : ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(300, 90, 40, 30);

        jLabel2.setText("TVA :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 130, 40, 30);

        jLabel3.setText("Editeur :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(470, 90, 50, 30);

        jLabel4.setText("Titre :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(470, 130, 50, 30);

        jLabel5.setText("Sous Titre :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(460, 170, 60, 30);

        jLabel6.setText("Date de Parution :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(430, 210, 90, 30);

        jLabel7.setText("Resumé :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(290, 290, 60, 40);

        jLabel8.setText("Extrait :");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(290, 340, 60, 40);

        jLabel10.setText("Prix HT :");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(290, 170, 50, 30);

        jLabel11.setText("Poids :");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(300, 210, 40, 30);

        jButton1.setText("OK !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(720, 390, 60, 23);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Creer");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(350, 20, 53, 23);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Editer");
        jRadioButton2.setEnabled(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(450, 20, 53, 23);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Effacer");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(550, 20, 93, 23);

        jCSousThematique.setModel(initModelSousThematique());
        jCSousThematique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSousThematiqueActionPerformed(evt);
            }
        });
        getContentPane().add(jCSousThematique);
        jCSousThematique.setBounds(476, 50, 160, 20);

        jCThematique.setModel(initModelThematique());
        jCThematique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCThematiqueActionPerformed(evt);
            }
        });
        getContentPane().add(jCThematique);
        jCThematique.setBounds(300, 50, 160, 20);

        jLabel9.setText("Editeur :");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(290, 390, 70, 30);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });
        getContentPane().add(jFileChooser1);
        jFileChooser1.setBounds(170, -70, 70, 70);

        jButton2.setText("Choisir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(730, 240, 70, 20);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLImage);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(660, 90, 140, 140);

        jButton3.setText("Choisir");
        jButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jButton3ItemStateChanged(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(370, 390, 80, 30);

        jLabel17.setText("Image :");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(700, 54, 50, 30);

        jLabel18.setText("Auteur :");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(520, 390, 50, 30);

        jButton5.setText("Choisir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(580, 390, 65, 30);

        jLabel26.setText("Auteur :");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(290, 250, 50, 30);
        getContentPane().add(jtnomAuteurMain);
        jtnomAuteurMain.setBounds(340, 250, 70, 30);
        getContentPane().add(jDateChooserLivre);
        jDateChooserLivre.setBounds(480, 250, 150, 30);

        setBounds(0, 0, 834, 473);
    }// </editor-fold>//GEN-END:initComponents

    private void jtIsbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtIsbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtIsbnActionPerformed

    private void jTreeLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeLivreMouseClicked

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTreeLivre.getLastSelectedPathComponent();

        if (node == null) //Nothing is selected.  
        {
            return;
        }

        Object nodeInfo = node.getUserObject();

        if (node.isLeaf()) {
            try {
                Livre book = (Livre) nodeInfo;

                jtIsbn.setText(book.getIsbnLivre());
                Date d = Date.valueOf(book.getDateParutionLivre());
                jDateChooserLivre.setDate(d);
                jtDateParutionLIvre.setText(book.getDateParutionLivre().toString());
                jtExtraitLivre.setText(book.getExtraitLivre());
                jtNomEditeur.setText(book.getEditeur().getNomEditeur());
                jtNomTVA.setText(book.getNomTVA());
                jtPoidLivre.setText("" + book.getPoidLivre());
                jtPrixHTLivre.setText("" + book.getPrixHTLivre());
                jtResumeLivre.setText(book.getResumeLivre());
                jtSousTitreLivre.setText(book.getSousTitreLivre());
                jtTitreLivre.setText(book.getTitreLivre());
                jRadioButton2.setSelected(true);
                jRadioButton1.setEnabled(false);
                jRadioButton3.setEnabled(false);
                
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("C'est pas un node");
        }
    }//GEN-LAST:event_jTreeLivreMouseClicked

    private void jCSousThematiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSousThematiqueActionPerformed

    }//GEN-LAST:event_jCSousThematiqueActionPerformed

    private void jCThematiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCThematiqueActionPerformed
        ArrayList<Thematique> mesThematique = Thematique.AffichageThematique();
        mesThematique = Thematique.AffichageSousThematique(jCThematique.getSelectedItem().toString().trim());
        jCSousThematique.setModel(initModelSousThematique2());
    }//GEN-LAST:event_jCThematiqueActionPerformed

    private void jTreeLivreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeLivreMouseReleased

    }//GEN-LAST:event_jTreeLivreMouseReleased

    private void jtResumeLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtResumeLivreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtResumeLivreActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Menu");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : "+  chooser.getSelectedFile());
            File f = chooser.getSelectedFile();
            BufferedImage im = null;
            try {
                im = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLImage.setIcon(new ImageIcon(im.getScaledInstance(jPanel1.getWidth(), jPanel1.getHeight(),0)));
            //Redimensionner l'image a la taille du jPanel1
            jPanel1.add(jLImage);
            
        }
        else {
            System.out.println("Pas de fichier séléctioné ");
        }
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jtIsbn.setEditable(false);
        jtDateParutionLIvre.setEditable(false);
        jtExtraitLivre.setEditable(false);
        jtNomEditeur.setEditable(false);
        jtNomTVA.setEditable(false);
        jtPoidLivre.setEditable(false);
        jtPrixHTLivre.setEditable(false);
        jtResumeLivre.setEditable(false);
        jtSousTitreLivre.setEditable(false);
        jtTitreLivre.setEditable(false);  
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jtIsbn.setEditable(true);
        jtDateParutionLIvre.setEditable(true);
        jtExtraitLivre.setEditable(true);
        jtNomEditeur.setEditable(true);
        jtNomTVA.setEditable(true);
        jtPoidLivre.setEditable(true);
        jtPrixHTLivre.setEditable(true);
        jtResumeLivre.setEditable(true);
        jtSousTitreLivre.setEditable(true);
        jtTitreLivre.setEditable(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jtIsbn.setEditable(false);
        jtDateParutionLIvre.setEditable(false);
        jtExtraitLivre.setEditable(false);
        jtNomEditeur.setEditable(false);
        jtNomTVA.setEditable(false);
        jtPoidLivre.setEditable(false);
        jtPrixHTLivre.setEditable(false);
        jtResumeLivre.setEditable(false);
        jtSousTitreLivre.setEditable(false);
        jtTitreLivre.setEditable(false);  
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            Livre livre = new Livre();
            try {
                livre.setIsbnLivre(jtIsbn.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setNomTVA(jtNomTVA.getText());
            Editeur editeur = null;
            try {
                editeur = new Editeur(jtNomEditeur.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                editeur.getSqlData();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setEditeur(editeur);
            livre.setDateParutionLivre(date2LocalDate(jDateChooserLivre.getDate()));
            
            livre.setResumeLivre(jtResumeLivre.getText());
            livre.setExtraitLivre(jtExtraitLivre.getText());
            livre.setImageLivre(jLImage.getText());
            try {
                livre.setTitreLivre(jtTitreLivre.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setSousTitreLivre(jtSousTitreLivre.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setPrixHTLivre(Float.valueOf(jtPrixHTLivre.getText()));
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setPoidLivre(Integer.valueOf(jtPoidLivre.getText()));
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setAffichageLivre(true);
            livre.CreerLivre();
            jTreeLivre.setModel(initModelBookStore());
        }
        if(jRadioButton2.isSelected()){
            Livre livre = new Livre();
            try {
                livre.setIsbnLivre(jtIsbn.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setNomTVA(jtNomTVA.getText());
            Editeur editeur = null;
            try {
                editeur = new Editeur(jtNomEditeur.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setTitreLivre(jtTitreLivre.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setSousTitreLivre(jtSousTitreLivre.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                editeur.getSqlData();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setEditeur(editeur);
            
            
            livre.setDateParutionLivre(date2LocalDate(jDateChooserLivre.getDate()));
            livre.setResumeLivre(jtResumeLivre.getText());
            livre.setExtraitLivre(jtExtraitLivre.getText());
            livre.setImageLivre(jLImage.getText());
            try {
                livre.setPrixHTLivre(Float.valueOf(jtPrixHTLivre.getText()));
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                livre.setPoidLivre(Integer.valueOf(jtPoidLivre.getText()));
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            livre.setAffichageLivre(true);
            try {
                livre.UpdateLivre2();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTreeLivre.setModel(initModelBookStore());
        }
        if(jRadioButton3.isSelected()){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private java.time.LocalDate date2LocalDate(java.util.Date date){
        if (date==null){ return null;}
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    private java.util.Date localDate2Date(java.time.LocalDate localDate){
        if (localDate==null){ return null;}
        return java.util.Date.from((localDate).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        jFrame1.setSize(300, 500);
        jFrame1.setLocation(550, 200);
        jFrame1.setVisible(true);
        jButton4.setEnabled(false);
        jCEditeur.setModel(initModelEditeur());
        
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jRadioButton4.isSelected()){
            Editeur editeur = new Editeur();
            try {
                editeur.setNomEditeur(jTnomEditeur2.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            editeur.setLogoEditeur(jTlogoEditeur.getText());
            try {
                editeur.setStatutEditeur(jCstatutEditeur.getSelectedItem().toString());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            editeur.CreerEditeur();
            jFrame1.setVisible(false);
            
        }
        if(jRadioButton5.isSelected()){
            Editeur editeur = new Editeur();
            try {
                editeur.setNomEditeur(jTnomEditeur2.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            editeur.setLogoEditeur(jTlogoEditeur.getText());
            try {
                editeur.setStatutEditeur(jCstatutEditeur.getSelectedItem().toString());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                editeur.UpdateEditeur();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jFrame1.setVisible(false);
            
        }
        if(jRadioButton6.isSelected()){
            Editeur editeur = new Editeur();
            try {
                editeur.setNomEditeur(jTnomEditeur2.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            editeur.setLogoEditeur(jTlogoEditeur.getText());
            try {
                editeur.setStatutEditeur(jCstatutEditeur.getSelectedItem().toString());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                editeur.deleteEditeur();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jFrame1.setVisible(false);
            
        }
        if(jRadioButton7.isSelected()){
            Editeur editeur = null;
            try {
                editeur = new Editeur(jTnomEditeur2.getText());
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                editeur.getSqlData();
            } catch (Exception ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jtNomEditeur.setText(jTnomEditeur2.getText());
            jtNomEditeur.setEditable(false);
        }
        jCEditeur.setModel(initModelEditeur());
        jFrame1.setVisible(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCEditeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCEditeurActionPerformed

        
    }//GEN-LAST:event_jCEditeurActionPerformed

    private void jCEditeurItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCEditeurItemStateChanged
       
        Editeur editeur =(Editeur) jCEditeur.getSelectedItem();
        
        
        jTnomEditeur2.setText(editeur.getNomEditeur());
        jCstatutEditeur.setSelectedItem(editeur.getStatutEditeur());
        jTlogoEditeur.setText(editeur.getLogoEditeur()); 
        
        
        
    }//GEN-LAST:event_jCEditeurItemStateChanged

    private void jButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jButton3ItemStateChanged
        
       
            jCEditeur.setModel(initModelEditeur());
        
    }//GEN-LAST:event_jButton3ItemStateChanged

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jFrame2.setSize(600, 500);
        jFrame2.setLocation(550, 200);
        jFrame2.setVisible(true);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Menu");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : "+  chooser.getSelectedFile());
            File f = chooser.getSelectedFile();
            BufferedImage im = null;
            try {
                im = ImageIO.read(f);
            } catch (IOException ex) {
                Logger.getLogger(jfBookStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLImageAuteur.setIcon(new ImageIcon(im.getScaledInstance(jPanel6.getWidth(), jPanel6.getHeight(),0)));
            //Redimensionner l'image a la taille du jPanel1
            jPanel6.add(jLImageAuteur);
            
        }
        else {
            System.out.println("Pas de fichier séléctioné ");
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(jRcreeAuteur.isSelected()){
            Auteur auteur = new Auteur();
            auteur.setNomAuteur(jTnomAuteur.getText());
            auteur.setPrenomAuteur(jTprenomAuteur.getText());
            auteur.setBioAuteur(jTbioAuteur.getText());
            auteur.setNationaliteAuteur(jTnationaliteAuteur.getText());
            
            LocalDate d = LocalDate.parse(jDateChooserAuteurNaissance.getDateFormatString());
            auteur.setDateNaissanceAuteur(d);
            
            LocalDate d2 = LocalDate.parse(jDateChooserAuteurDeces.getDateFormatString());
            auteur.setDateDecesAuteur(d2);
            auteur.CreerAuteur();
            jFrame2.setVisible(false);

        }
        if(jREdiAuteur.isSelected()){
            Auteur auteur = new Auteur();
            auteur.setNomAuteur(jTnomAuteur.getText());
            auteur.setPrenomAuteur(jTprenomAuteur.getText());
            auteur.setBioAuteur(jTbioAuteur.getText());
            auteur.setNationaliteAuteur(jTnationaliteAuteur.getText());
            
            LocalDate d = LocalDate.parse(jDateChooserAuteurNaissance.getDateFormatString());
            auteur.setDateNaissanceAuteur(d);
            
            LocalDate d2 = LocalDate.parse(jDateChooserAuteurDeces.getDateFormatString());
            auteur.setDateDecesAuteur(d2);

            auteur.UpdateAuteur();
            jFrame2.setVisible(false);
        }
        if(jReffAuteur.isSelected()){
            Auteur auteur = new Auteur();
            auteur.setNomAuteur(jTnomAuteur.getText());
            auteur.setPrenomAuteur(jTprenomAuteur.getText());
            auteur.setBioAuteur(jTbioAuteur.getText());
            auteur.setNationaliteAuteur(jTnationaliteAuteur.getText());
            
            LocalDate d = LocalDate.parse(jDateChooserAuteurNaissance.getDateFormatString());
            auteur.setDateNaissanceAuteur(d);
            
            LocalDate d2 = LocalDate.parse(jDateChooserAuteurDeces.getDateFormatString());
            auteur.setDateDecesAuteur(d2);
            jFrame2.setVisible(false);
            
        }
        if(jRselAuteur.isSelected()){
            Auteur auteur = new Auteur();
            auteur.setNomAuteur(jTnomAuteur.getText());
            auteur.setPrenomAuteur(jTprenomAuteur.getText());
            auteur.setBioAuteur(jTbioAuteur.getText());
            auteur.setNationaliteAuteur(jTnationaliteAuteur.getText());
            
            String s = jDateChooserAuteurNaissance.getDate().toString();
            LocalDate d = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
            auteur.setDateNaissanceAuteur(d);
            
            LocalDate d2 = LocalDate.parse(jDateChooserAuteurDeces.getDateFormatString());
            auteur.setDateDecesAuteur(d2);
            
            jtnomAuteurMain.setText(jTnomAuteur.getText());
            jFrame2.setVisible(false);

            
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void editEditeur(){
        this.setVisible(true);
        jFrame1.setSize(300, 500);
        jFrame1.setLocation(550, 200);
        jFrame1.setVisible(true);
        jButton4.setEnabled(false);
        jCEditeur.setModel(initModelEditeur());
    }
    
	
    public void editAuteur(){
        this.setVisible(true);
        jFrame2.setSize(600, 500);
        jFrame2.setLocation(550, 200);
        jFrame2.setVisible(true);
    }
    
    private void jReffAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReffAuteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jReffAuteurActionPerformed

    private void jCAuteursItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCAuteursItemStateChanged
        Auteur auteur =(Auteur) jCAuteurs.getSelectedItem();
        System.out.println(auteur);
        
        
        jTnomAuteur.setText(auteur.getNomAuteur());
        
        jTprenomAuteur.setText(auteur.getPrenomAuteur());
        jTnationaliteAuteur.setText(auteur.getNationaliteAuteur()); 
        jTbioAuteur.setText(auteur.getBioAuteur()); 
        System.out.println(auteur.getDateNaissanceAuteur());
        System.out.println(auteur.getDateDecesAuteur());
        
        Date d = Date.valueOf(auteur.getDateNaissanceAuteur());
            jDateChooserAuteurNaissance.setDate(d);
              
        if(auteur.getDateDecesAuteur() == null){
            jDateChooserAuteurDeces.setDate(null);
        }
        else {
            Date d2 = Date.valueOf(auteur.getDateDecesAuteur());
            jDateChooserAuteurDeces.setDate(d2);
        }
        
        
        
        
//        LocalDate d = LocalDate.parse(jTdateDeNaissanceAuteur.getText());
//        auteur.setDateNaissanceAuteur(d);
//        jTdateDeNaissanceAuteur.setText(d.toString());
        
//        LocalDate d2 = LocalDate.parse(jTdateDeDecesAuteur.getText());
//        auteur.setDateDecesAuteur(d2);
//        jTdateDeDecesAuteur.setText(d2.toString());
    }//GEN-LAST:event_jCAuteursItemStateChanged

    private void jtDateParutionLIvreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDateParutionLIvreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDateParutionLIvreActionPerformed

    private void jCAuteursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAuteursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCAuteursActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jCAuteurs;
    private javax.swing.JComboBox jCEditeur;
    private javax.swing.JComboBox jCSousThematique;
    private javax.swing.JComboBox jCThematique;
    private javax.swing.JComboBox jCstatutEditeur;
    private com.toedter.calendar.JDateChooser jDateChooserAuteurDeces;
    private com.toedter.calendar.JDateChooser jDateChooserAuteurNaissance;
    private com.toedter.calendar.JDateChooser jDateChooserLivre;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLImage;
    private javax.swing.JLabel jLImageAuteur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jREdiAuteur;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRcreeAuteur;
    private javax.swing.JRadioButton jReffAuteur;
    private javax.swing.JRadioButton jRselAuteur;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTbioAuteur;
    private javax.swing.JTextField jTlogoEditeur;
    private javax.swing.JTextField jTnationaliteAuteur;
    private javax.swing.JTextField jTnomAuteur;
    private javax.swing.JTextField jTnomEditeur2;
    private javax.swing.JTextField jTprenomAuteur;
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
    private javax.swing.JTextField jtnomAuteurMain;
    // End of variables declaration//GEN-END:variables
}
