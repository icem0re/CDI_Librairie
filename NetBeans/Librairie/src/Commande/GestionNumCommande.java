/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commande;

import Administration.Statut;
import Utilisateur.Adresse;
import Utilisateur.Client;
import java.sql.SQLException;
import Administration.StatutCommande;
import bookStore.Editeur;
import bookStore.jfBookStore;
import com.toedter.calendar.demo.DateChooserPanel;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author cdi409
 */
public class GestionNumCommande extends javax.swing.JFrame {

    private Commande Cmd;
    private GestionCommande gc;
    private GestionTouteCommande gtc;

    
    public GestionNumCommande(GestionTouteCommande gtc, Commande Cmd) throws SQLException {
        this(Cmd);
        this.gtc = gtc;
    }
    
    public GestionNumCommande(GestionCommande gc, Commande Cmd) throws SQLException {
        this(Cmd);
        this.gc = gc;
    }
    /**
     * Creates new form GestionNumCommande
     *
     * @param Cmd
     */
    public GestionNumCommande(Commande Cmd) throws SQLException {
        this.Cmd = Cmd;

        initComponents();
        setSize(new java.awt.Dimension(801, 750));
        jTextField1.setText("" + new Client(Cmd.getIdClient()));
        jTextField2.setText("" + new Adresse(Cmd.getIdAdresseFacturation()));
        jTextField3.setText("" + new Adresse(Cmd.getIdAdresseLivraison()));
        jTextField4.setText("" + Cmd.getDateCommande());
        jTextField5.setText("" + Cmd.getDatePaiementCommande());
        if (Cmd.getDatePreparationCommande() != null) {
            jDateChooserPreparation.setDate(java.sql.Date.valueOf(Cmd.getDatePreparationCommande()));
        }
        if (Cmd.getDateExpeditionCommande() != null) {
            jDateChooserExpedition.setDate(java.sql.Date.valueOf(Cmd.getDateExpeditionCommande()));
        }
        if (Cmd.getDateAccuseReceptionCommande() != null) {
            jDateChooserReception.setDate(java.sql.Date.valueOf(Cmd.getDateAccuseReceptionCommande()));
        }
        jTextField9.setText("" + Cmd.getDateAnnulationCommande());
        jTextField6.setText("" + Cmd.getStatutCommande());
        jTextField11.setText("" + Cmd.getIpCommande());
        jTextField12.setText("" + Cmd.getNomLivreur());
        jTextArea1.setText("" + Cmd.getLignesDeCommande());
    }

    private DefaultComboBoxModel initModelStatutCom() {
        try {
            return new DefaultComboBoxModel(initStatutCom());
        } catch (SQLException ex) {
            Logger.getLogger(GestionNumCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DefaultComboBoxModel();
    }

    private Vector initStatutCom() throws SQLException {
        Vector v = new Vector();
        ArrayList<Statut> mesStatutsCom = StatutCommande.getStatut();

        String lastStatut = null;
        String lastInfo = null;
        for (int i = 0; i < mesStatutsCom.size(); i++) {
            if (lastStatut == null) {
                lastStatut = mesStatutsCom.get(i).getCode();
                lastInfo = mesStatutsCom.get(i).getInfo();
                StatutCommande Statut = null;

                Statut = new StatutCommande();
                Statut.setCode(lastStatut);
                Statut.setInfo(lastInfo);

                v.add(Statut);
            }
            if (!lastStatut.equalsIgnoreCase(mesStatutsCom.get(i).getCode())) {
                lastStatut = mesStatutsCom.get(i).getCode();
                lastInfo = mesStatutsCom.get(i).getInfo();
                StatutCommande Statut = null;

                Statut = new StatutCommande();
                Statut.setCode(lastStatut);
                Statut.setInfo(lastInfo);

                v.add(Statut);
            }
        }

        return v;
    }

    private java.time.LocalDate date2LocalDate(java.util.Date date) {
        if (date==null){return null;}
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }
    
    private java.util.Date date2SQLDate(java.time.LocalDate date){
        return java.sql.Date.valueOf(date);
    }

    private java.util.Date localDate2Date(java.time.LocalDate localDate) {
        return java.util.Date.from((localDate).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
    }

    private java.sql.Date localDate2SQLDate(java.time.LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

//    public void affichageCommande(){
//      // jTextArea1.setText(""+Cmd.getIdAdresseFacturation()+"\n"+Cmd.getIdClient());
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButtonRetour = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jDateChooserPreparation = new com.toedter.calendar.JDateChooser();
        jDateChooserExpedition = new com.toedter.calendar.JDateChooser();
        jDateChooserReception = new com.toedter.calendar.JDateChooser();
        jTextField6 = new javax.swing.JTextField();
        jButtonValider = new javax.swing.JButton();
        listStatut = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(784, 612));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Numéro de la commande:");

        jLabel2.setText(""+Cmd.getNumCommande());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Détail de la commande", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 255, 255));
        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 255, 255));
        jTextField2.setText("jTextField2");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 255, 255));
        jTextField3.setText("jTextField3");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(204, 255, 255));
        jTextField4.setText("jTextField4");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(204, 255, 255));
        jTextField5.setText("jTextField5");

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(204, 255, 255));
        jTextField9.setText("jTextField9");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(204, 255, 255));
        jTextField11.setText("jTextField11");

        jLabel3.setText("Client");

        jLabel4.setText("Adresse livraison");

        jLabel5.setText("Adresse facturation");

        jLabel6.setText("Date commande");

        jLabel7.setText("Date paiement");

        jLabel8.setText("Date preparation");

        jLabel9.setText("Date expedition");

        jLabel10.setText("Date reception");

        jLabel11.setText("Date annulation");

        jLabel12.setText("Statut");

        jLabel13.setText("IP commande");

        jLabel14.setText("Livreur");

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(204, 255, 255));
        jTextField12.setText("jTextField12");

        jLabel15.setText("Ligne(s) de commande");

        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetourActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jDateChooserPreparation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooserPreparationMouseClicked(evt);
            }
        });
        jDateChooserPreparation.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserPreparationPropertyChange(evt);
            }
        });
        jDateChooserPreparation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooserPreparationKeyPressed(evt);
            }
        });

        jDateChooserExpedition.setName("JDateChooserExpedition"); // NOI18N

        jDateChooserReception.setName("JDateChooserReception"); // NOI18N

        jTextField6.setText("jTextField6");

        jButtonValider.setText("Valider changements");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        listStatut.setModel(initModelStatutCom());
        listStatut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listStatutItemStateChanged(evt);
            }
        });
        listStatut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listStatutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonRetour)
                        .addGap(55, 55, 55)
                        .addComponent(jButtonValider))
                    .addComponent(jTextField12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField11)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(jDateChooserPreparation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserExpedition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(listStatut, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooserPreparation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserExpedition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(listStatut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRetour)
                    .addComponent(jButtonValider)))
        );

        jDateChooserExpedition.getAccessibleContext().setAccessibleName("JDateChooserExpedition");
        jDateChooserReception.getAccessibleContext().setAccessibleName("JDateChooserReception");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(562, 611));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButtonRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetourActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonRetourActionPerformed

    private void jDateChooserPreparationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserPreparationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserPreparationKeyPressed

    private void jDateChooserPreparationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserPreparationMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jDateChooserPreparationMouseClicked

    private void jDateChooserPreparationPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserPreparationPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jDateChooserPreparationPropertyChange

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        try {
            Commande c1 = new Commande(Integer.valueOf(jLabel2.getText()));
            if (c1.getDatePreparationCommande() == null) {
                c1.setDatePreparationCommande(null);
            }
            c1.setDatePreparationCommande((date2LocalDate(jDateChooserPreparation.getDate())));
            if (c1.getDateExpeditionCommande() == null) {
                c1.setDateExpeditionCommande(null);
            }
            c1.setDatePreparationCommande(date2LocalDate(jDateChooserPreparation.getDate()));
            if (c1.getDateExpeditionCommande() == null) {
                c1.setDateExpeditionCommande(null);
            }
            c1.setDateExpeditionCommande(date2LocalDate(jDateChooserExpedition.getDate()));
            if (c1.getDateAccuseReceptionCommande() == null) {
                c1.setDateAccuseReceptionCommande(null);
            }
            c1.setDateAccuseReceptionCommande(date2LocalDate(jDateChooserReception.getDate()));
            c1.setStatutCommande(listStatut.getSelectedItem().toString());
            c1.updateCommandeSQL();
            
            this.dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(GestionNumCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void listStatutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listStatutActionPerformed

// lister les statuts...

    }//GEN-LAST:event_listStatutActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void listStatutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listStatutItemStateChanged
        StatutCommande t = (StatutCommande) listStatut.getSelectedItem();
        jTextField6.setText(t.getCode());
    }//GEN-LAST:event_listStatutItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (gtc!=null){
            gtc.setVisible(true);
        } else if(gc!=null){
            gc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Le module parent est introuvable", "Erreur Fatale", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_formWindowClosed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GestionNumCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GestionNumCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GestionNumCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GestionNumCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GestionCommande G = new GestionCommande();
//                G.setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonRetour;
    private javax.swing.JButton jButtonValider;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooserExpedition;
    private com.toedter.calendar.JDateChooser jDateChooserPreparation;
    private com.toedter.calendar.JDateChooser jDateChooserReception;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox listStatut;
    // End of variables declaration//GEN-END:variables
}
