
package Swing;

import Administration.Statut;
import Utilisateur.Client;
import java.awt.Frame;
import java.util.ArrayList;
import bookStore.jfBookStore;

/**
 *
 * @author cdi411
 */
public class JGestion extends javax.swing.JFrame {

    private JClientMain jdClient;
    private jfBookStore jStore;
    private JEvent jEvent;
    /**
     * Creates new form JGestion
     */
    public JGestion() {
        jdClient = null;
        
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

        jToolBar1 = new javax.swing.JToolBar();
        jButtonGestionClients = new javax.swing.JButton();
        jButtonGestionLivres = new javax.swing.JButton();
        jButtonGestionEvenements = new javax.swing.JButton();
        jButtonGestionEditeurs = new javax.swing.JButton();
        jButtonGestionAuteurs = new javax.swing.JButton();
        jButtonGestionCommandes = new javax.swing.JButton();
        jButtonGestionThematiques = new javax.swing.JButton();
        jButtonGestionEmployes = new javax.swing.JButton();
        jButtonFermer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jButtonGestionClients.setText("Gestion Clients");
        jButtonGestionClients.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionClients.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGestionClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionClientsActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGestionClients);

        jButtonGestionLivres.setText("Gestion Livres");
        jButtonGestionLivres.setFocusable(false);
        jButtonGestionLivres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionLivres.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGestionLivres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionLivresActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGestionLivres);

        jButtonGestionEvenements.setText("Gestion Evenements");
        jButtonGestionEvenements.setFocusable(false);
        jButtonGestionEvenements.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionEvenements.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGestionEvenements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionEvenementsActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGestionEvenements);

        jButtonGestionEditeurs.setText("Gestion Editeurs");
        jButtonGestionEditeurs.setFocusable(false);
        jButtonGestionEditeurs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionEditeurs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonGestionEditeurs);

        jButtonGestionAuteurs.setText("Gestion Auteurs");
        jButtonGestionAuteurs.setFocusable(false);
        jButtonGestionAuteurs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionAuteurs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonGestionAuteurs);

        jButtonGestionCommandes.setText("Gestion Commandes");
        jButtonGestionCommandes.setFocusable(false);
        jButtonGestionCommandes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionCommandes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonGestionCommandes);

        jButtonGestionThematiques.setText("Gestion Thématiques");
        jButtonGestionThematiques.setFocusable(false);
        jButtonGestionThematiques.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionThematiques.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonGestionThematiques);

        jButtonGestionEmployes.setText("Gestion Employés");
        jButtonGestionEmployes.setFocusable(false);
        jButtonGestionEmployes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGestionEmployes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonGestionEmployes);

        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(0, 12, 150, 220);

        jButtonFermer.setText("Fermer");
        jButtonFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFermerActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFermer);
        jButtonFermer.setBounds(30, 250, 73, 23);

        setSize(new java.awt.Dimension(165, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGestionClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionClientsActionPerformed
        // TODO add your handling code here:
        if (jdClient == null){
            jdClient = new JClientMain();
            jdClient.setVisible(true);
        } else if (!jdClient.isVisible()){
            jdClient = new JClientMain();
            jdClient.setVisible(true);
        } else if (jdClient.getState() == Frame.ICONIFIED){
            jdClient.setState ( Frame.NORMAL );
        } else {
            jdClient.toFront();
        }
       
        
    }//GEN-LAST:event_jButtonGestionClientsActionPerformed

    private void jButtonFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFermerActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFermerActionPerformed

    private void jButtonGestionLivresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionLivresActionPerformed
        
        // TODO add your handling code here:
        if(jStore==null){
           jStore=new jfBookStore();
           jStore.setVisible(true);          
        } else if (!jStore.isVisible()){
            jStore=new jfBookStore();
            jStore.setVisible(true);           
        }else if(jStore.getState()==Frame.ICONIFIED){
            jStore.setState(Frame.NORMAL);
        }else{
            jStore.toFront();
        }
    }//GEN-LAST:event_jButtonGestionLivresActionPerformed

    private void jButtonGestionEvenementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionEvenementsActionPerformed
        // TODO add your handling code here:
        
         if(jEvent==null){
           jEvent=new JEvent();
           jEvent.setVisible(true);          
        } else if (!jEvent.isVisible()){
            jEvent=new JEvent();
            jEvent.setVisible(true);           
        }else if(jEvent.getState()==Frame.ICONIFIED){
            jEvent.setState(Frame.NORMAL);
        }else{
            jEvent.toFront();
        }
    }//GEN-LAST:event_jButtonGestionEvenementsActionPerformed

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
            java.util.logging.Logger.getLogger(JGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JGestion().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFermer;
    private javax.swing.JButton jButtonGestionAuteurs;
    private javax.swing.JButton jButtonGestionClients;
    private javax.swing.JButton jButtonGestionCommandes;
    private javax.swing.JButton jButtonGestionEditeurs;
    private javax.swing.JButton jButtonGestionEmployes;
    private javax.swing.JButton jButtonGestionEvenements;
    private javax.swing.JButton jButtonGestionLivres;
    private javax.swing.JButton jButtonGestionThematiques;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}