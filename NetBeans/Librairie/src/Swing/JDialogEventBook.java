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

import Evenement.Evenement;
import Exception.InvalidSearchException;
import bookStore.Editeur;
import bookStore.Livre;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author cdi415
 */
public class JDialogEventBook extends javax.swing.JDialog {

    private Action buttonClicked = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            // get affected table
            javax.swing.JTable table = (javax.swing.JTable)e.getSource();
            // get affected row
            int clickedRow = Integer.valueOf( e.getActionCommand() );
            
            ((JDialogEvent)self.getParent()).addRow((Livre) table.getValueAt(clickedRow, 0));
//            if (clickedRow == 0){
//                new JDialogEvent(self, true, new Evenement()).setVisible(true);
//            } else {
//                new JDialogEvent(self, true, (Evenement) table.getValueAt(clickedRow, 0)).setVisible(true);
//            }
        }
    };
    
    private JDialogEventBook self;
    ImageIcon icon_add;
    
    /**
     * Creates new form JDialogEventBook
     */
    public JDialogEventBook(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        self = this;
        initIcons();
        initComponents();
    }
    
    private void initIcons(){
        icon_add = null;
        try {
            icon_add = new ImageIcon(getClass().getClassLoader().getResource("resources/images/icon_add_30.png"));
        } catch (java.lang.NullPointerException ex) {}
    }
    
    protected void refreshTable (){
        jTable.setModel(initTableModel());
        
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        
        jTable.getColumnModel().getColumn(1).setMinWidth(110);
        jTable.getColumnModel().getColumn(1).setMaxWidth(110);
        
        TableColumn iconColumn = jTable.getColumnModel().getColumn(jTable.getColumnModel().getColumnCount()-1);
        iconColumn.setMinWidth(40);
        iconColumn.setMaxWidth(40);
        ButtonColumn buttonColumn = new ButtonColumn(jTable, buttonClicked, iconColumn.getModelIndex());
        
        jTable.setGridColor(Color.CYAN);
        jTable.setShowGrid(true);
        jTable.setShowHorizontalLines(true);
        jTable.setShowVerticalLines(false);
        jTable.setRowHeight(40);
    }
    
    private DefaultTableModel initTableModel(){
        
        DefaultTableModel myModel = new DefaultTableModel();
        
        try {
            
            myModel.addColumn("");
            myModel.addColumn("ISBN");
            myModel.addColumn("Titre");
            myModel.addColumn("Sous-Titre");
            myModel.addColumn("");
            
            String isbn = null;
            if (!jTextFieldISBN.getText().equals("")){isbn = jTextFieldISBN.getText();}
            String titre = null;
            if (!jTextFieldTitre.getText().equals("")){titre = jTextFieldTitre.getText();}
            String sousTitre = null;
            if (!jTextFieldSousTitre.getText().equals("")){sousTitre = jTextFieldSousTitre.getText();}
            String nomAuteur = null;
            if (!jTextFieldNomAuteur.getText().equals("")){nomAuteur = jTextFieldNomAuteur.getText();}
            String prenomAuteur = null;
            if (!jTextFieldPrenomAuteur.getText().equals("")){prenomAuteur = jTextFieldPrenomAuteur.getText();}
            String nomEditeur = null;
            if (jComboBoxEditeur.getSelectedItem() != null){nomEditeur = jComboBoxEditeur.getSelectedItem().toString();}
            
            for (Livre monLivre : Livre.searchLivres(isbn, titre, sousTitre, nomEditeur, nomAuteur, prenomAuteur)){
                Vector monVec = new Vector();
                monVec.add(monLivre);
                monVec.add(monLivre.getIsbnLivre());
                monVec.add(monLivre.getTitreLivre());
                monVec.add(monLivre.getSousTitreLivre());
                if (icon_add == null) {
                    monVec.add("Add");
                } else {
                    monVec.add(icon_add);
                }
                myModel.addRow(monVec);
            }
        } catch (InvalidSearchException ex){
            // Ne pas effectuer d'action
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur System", JOptionPane.ERROR_MESSAGE);
        }
        
            return myModel;
    }
    
    private DefaultComboBoxModel initComboEditeur(){
        DefaultComboBoxModel myModel = new DefaultComboBoxModel();
        
        myModel.addElement(null);
        for (Editeur monEditeur : Editeur.AffichageEditeur()){
            myModel.addElement(monEditeur);
        }
        
        return myModel;
    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldSousTitre = new javax.swing.JTextField();
        jTextFieldTitre = new javax.swing.JTextField();
        jTextFieldNomAuteur = new javax.swing.JTextField();
        jTextFieldPrenomAuteur = new javax.swing.JTextField();
        jComboBoxEditeur = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(754, 526));
        setPreferredSize(new java.awt.Dimension(754, 526));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.BorderLayout());

        refreshTable();
        jScrollPane1.setViewportView(jTable);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(4, 200));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Rechercher par :");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Numéros d'ISBN : ");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nom d'auteur : ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Prènom d'auteur : ");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Titre : ");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Sous-Titre : ");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Editeur : ");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBoxEditeur.setModel(initComboEditeur());

        jButton1.setText("Rechercher !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldTitre)
                                .addComponent(jTextFieldSousTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomAuteur, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(jTextFieldPrenomAuteur)
                            .addComponent(jComboBoxEditeur, 0, 241, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxEditeur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNomAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldSousTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPrenomAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(JDialogEventBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogEventBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogEventBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogEventBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogEventBook dialog = new JDialogEventBook(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxEditeur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldNomAuteur;
    private javax.swing.JTextField jTextFieldPrenomAuteur;
    private javax.swing.JTextField jTextFieldSousTitre;
    private javax.swing.JTextField jTextFieldTitre;
    // End of variables declaration//GEN-END:variables
}



/**
 public static ArrayList<Livre> searchLivres(String byISBN, 
            String byTitre, 
            String bySousTitre, 
            String byEditeur, 
            String byNomAuteur, 
            String byPrenomAuteur) throws SQLException, Exception {

        ArrayList<Livre> Biblio = new ArrayList();
        Boolean hasValidSearch = false;
        
        String req = "SELECT "
                    + "     l.isbnLivre "
                    + " FROM "
                    + "     LIVRE l "
                    + " JOIN Editeur e "
                    + "     ON l.nomEditeur = e.nomEditeur "
                    + " JOIN Redaction red "
                    + "     ON l.isbnLivre = red.isbnLivre "
                    + " JOIN Auteur a "
                    + "     ON red.idAuteur = a.idAuteur ";
        
        if (byNomAuteur != null){
            byNomAuteur = byNomAuteur.replaceAll("\\*", "%");
            req = req + " AND a.nomAuteur like ? ";
            hasValidSearch = true;
        }
        if (byPrenomAuteur != null){
            byPrenomAuteur = byPrenomAuteur.replaceAll("\\*", "%");
            req = req + " AND a.prenomAuteur like ? ";
            hasValidSearch = true;
        }
        
        req = req   + " WHERE ";
        
        if (byISBN != null){
            byISBN = byISBN.replaceAll("\\*", "%");
            req = req + " l.isbnLivre like ? AND ";
            hasValidSearch = true;
        }
        if (byTitre != null){
            byTitre = byTitre.replaceAll("\\*", "%");
            req = req + " l.titreLivre like ? AND ";
            hasValidSearch = true;
        }
        if (bySousTitre != null){
            bySousTitre = bySousTitre.replaceAll("\\*", "%");
            req = req + " l.sousTitreLivre like ? AND ";
            hasValidSearch = true;
        }
        if (byEditeur != null){
            byEditeur = byEditeur.replaceAll("\\*", "%");
            req = req + " l.nomEditeur like ? AND ";
            hasValidSearch = true;
        }
        
        if (!hasValidSearch){
            throw new InvalidSearchException("Merci de fournir moins un champs de recherche");
        }
        
        // Nettoyage fin de requete
        if (("AND ").equals(req.substring(req.length()-4))){
            req = req.substring(0, req.length()-("AND ").length());
        } else {
            req = req.substring(0, req.length()-(" WHERE ").length());
        }
        //System.out.println(req);
        
        try (Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ) 
        {

            int i = 1;
            if (byNomAuteur != null) {
                pstm.setString(i++, byNomAuteur);
            }
            if (byPrenomAuteur != null) {
                pstm.setString(i++, byPrenomAuteur);
            }
            if (byISBN != null) {
                pstm.setString(i++, byISBN);
            }
            if (byTitre != null) {
                pstm.setString(i++, byTitre);
            }
            if (bySousTitre != null) {
                pstm.setString(i++, bySousTitre);
            }
            if (byEditeur != null) {
                pstm.setString(i++, byEditeur);
            }

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(rs.getString("isbnLivre"));
                Biblio.add(livre);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return Biblio;
    }
 */