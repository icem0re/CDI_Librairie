/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commande;

import Utilisateur.Client;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author cdi409
 */
public class GestionCommande extends javax.swing.JFrame {
    

    /**
     * Creates new form GestionCommande
     */
    public GestionCommande() {
        initComponents();
        jRadioButtonNumCmd.setSelected(true);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelInfoText = new javax.swing.JLabel();
        jTextFieldInfo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jRadioButtonNumCmd = new javax.swing.JRadioButton();
        jRadioButtonIdClient = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Gestion de commande");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 137, 17);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Critère d'affichage"));

        jLabelInfoText.setText("Afficher la commande portant le numéro:");

        jTextFieldInfo.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jTextFieldInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInfoActionPerformed(evt);
            }
        });

        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNumCmd);
        jRadioButtonNumCmd.setText("Par numéro de commande");
        jRadioButtonNumCmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNumCmdActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonIdClient);
        jRadioButtonIdClient.setText("Par ID client");
        jRadioButtonIdClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIdClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelInfoText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jRadioButtonNumCmd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jRadioButtonIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonNumCmd)
                    .addComponent(jRadioButtonIdClient))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInfoText)
                    .addComponent(jTextFieldInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 46, 513, 136);

        setSize(new java.awt.Dimension(529, 219));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInfoActionPerformed

    }//GEN-LAST:event_jTextFieldInfoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jRadioButtonNumCmd.isSelected()) {
            Commande Cmd;
            try {
                Cmd = new Commande(Integer.valueOf(jTextFieldInfo.getText()));
                GestionNumCommande Gnc = new GestionNumCommande(this, Cmd);
                Gnc.setVisible(rootPaneCheckingEnabled);
                this.setVisible(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur de saisie. \n[" 
                        + ex.getMessage() 
                        +"]"
                        , "Erreur"
                        , JOptionPane.ERROR_MESSAGE);
            }

        }
        if (jRadioButtonIdClient.isSelected()) {
            jRadioButtonNumCmd.setSelected(false);
            try {
                Client myclient = new Client(Integer.valueOf(jTextFieldInfo.getText()));
                myclient = new Client(Integer.valueOf(jTextFieldInfo.getText()));
                GestionTouteCommande Gtc = new GestionTouteCommande(this, myclient);
                Gtc.setVisible(rootPaneCheckingEnabled);
                this.setVisible(false);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Erreur de saisie. \n[" 
                        + ex.getMessage() 
                        +"]"
                        , "Erreur"
                        , JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButtonNumCmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNumCmdActionPerformed

        jLabelInfoText.setText("Afficher la commande portant le numéro :");       
    }//GEN-LAST:event_jRadioButtonNumCmdActionPerformed

    private void jRadioButtonIdClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonIdClientActionPerformed

        jLabelInfoText.setText("Afficher toutes les commandes du client :");
    }//GEN-LAST:event_jRadioButtonIdClientActionPerformed

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
            java.util.logging.Logger.getLogger(GestionCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionCommande().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelInfoText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonIdClient;
    private javax.swing.JRadioButton jRadioButtonNumCmd;
    private javax.swing.JTextField jTextFieldInfo;
    // End of variables declaration//GEN-END:variables
}