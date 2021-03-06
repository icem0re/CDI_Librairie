/*
 * Copyright (C) 2017 cdi415
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Swing;

import Administration.Statut;
import Administration.StatutClient;
import Administration.StatutCommande;
import Administration.StatutEditeur;
import Administration.StatutLivreur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cdi415
 */
public class JStatutMain extends javax.swing.JFrame {

    /**
     * Creates new form JEmploye
     */
    public JStatutMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxType = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbNew = new javax.swing.JButton();
        jBModify = new javax.swing.JButton();
        jBDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestionnaire de code");
        setResizable(false);
        getContentPane().setLayout(null);

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Client", "Editeur", "Commande", "Livreur" }));
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxType);
        jComboBoxType.setBounds(190, 10, 180, 20);

        jTable1.setModel(initModel());
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jTable1ComponentResized(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 360, 180);

        jbNew.setText("Nouveau");
        jbNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewActionPerformed(evt);
            }
        });
        getContentPane().add(jbNew);
        jbNew.setBounds(20, 230, 100, 23);

        jBModify.setText("Modifier");
        jBModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModifyActionPerformed(evt);
            }
        });
        getContentPane().add(jBModify);
        jBModify.setBounds(140, 230, 100, 23);

        jBDelete.setText("Supprimer");
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(jBDelete);
        jBDelete.setBounds(260, 230, 100, 23);

        jLabel1.setFont(new java.awt.Font("Vani", 0, 14)); // NOI18N
        jLabel1.setText("Selectionner la catègorie :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 170, 20);

        setSize(new java.awt.Dimension(398, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeActionPerformed
        // TODO add your handling code here:
        this.refresh();
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jbNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewActionPerformed

        try {
            JDialogStatut jd = new JDialogStatut(this, 
                    true, 
                    (Statut) Class.forName(Statut.class.getName()
                        + jComboBoxType.getSelectedItem().toString()).newInstance()
                );
            jd.setCodeType(jComboBoxType.getSelectedItem().toString());
            jd.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Catégorie de code inconnue \n[errMessage :" 
                            + ex.getMessage() 
                            + "]", 
                    "Erreur de complilation", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jbNewActionPerformed

    private void jBModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModifyActionPerformed
        
        if (jTable1.getSelectedRowCount() == 1){
            JDialogStatut jd = new JDialogStatut(this, 
                true, 
                (Statut) jTable1.getValueAt(jTable1.getSelectedRow(), 0)
            );
            jd.setCodeType(jComboBoxType.getSelectedItem().toString());
            jd.setEditionMode();
            jd.setVisible(true);
        }
        
    }//GEN-LAST:event_jBModifyActionPerformed

    private void jTable1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentResized
        // TODO add your handling code here:
        System.out.println(jTable1.getColumnModel().getColumn(0).getWidth());
                
    }//GEN-LAST:event_jTable1ComponentResized

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRowCount() == 1){
            int choix = JOptionPane.showConfirmDialog(this, 
                    "Voulez-vous vraiment supprimer le code " + 
                        ((Statut)(jTable1.getValueAt(jTable1.getSelectedRow(), 0))).getCode(),
                    "", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            if (choix == 0){
                try {
                    ((Statut)(jTable1.getValueAt(jTable1.getSelectedRow(), 0))).delete();
                    refresh();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, 
                    "Une erreur SQL est survenu" 
                            + "\n[errMessage : " 
                            + ex.getMessage() 
                            + "]",
                    "SQL Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBDeleteActionPerformed

    
    public void refresh(){
        jTable1.setModel(initModel());
    }
    
    public DefaultTableModel initModel(){
        
        String selectedType = jComboBoxType.getSelectedItem().toString();
        DefaultTableModel t = new DefaultTableModel();
        
        // Définition des entetes
        t.addColumn("Code " + selectedType);
        t.addColumn("Description");
        
        try {
            ArrayList<Statut> statutList = new ArrayList<>();
            
            switch (selectedType.toLowerCase()) {
                case "client":
                    statutList = StatutClient.getStatut();
                    break;
                case "commande":
                    statutList = StatutCommande.getStatut();
                    break;
                case "editeur":
                    statutList = StatutEditeur.getStatut();
                    break;
                case "livreur":
                    statutList = StatutLivreur.getStatut();
                    break;
                case "":
                    break;
                default:
                    throw new Exception("Categorie de code statut inconnue");
            }

            for (Statut stat : statutList) {
                Vector v = new Vector();
                v.add(stat);
                v.add(stat.getInfo());
                t.addRow(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return t;
        
    }
    
    
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
            java.util.logging.Logger.getLogger(JStatutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JStatutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JStatutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JStatutMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JStatutMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBModify;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbNew;
    // End of variables declaration//GEN-END:variables
}
