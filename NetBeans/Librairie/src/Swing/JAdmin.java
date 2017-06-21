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

import Administration.Employe;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author cdi415
 */
public class JAdmin extends javax.swing.JFrame {

    private Action delete = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            // get affected table
            javax.swing.JTable table = (javax.swing.JTable)e.getSource();
            // get affected row
            int clickedRow = Integer.valueOf( e.getActionCommand() );
            
//            if (((DefaultTableModel)table.getModel()).getRowCount()-1 == clickedRow){
            if (clickedRow == 0){
                new JDialogAddAdmin(self, true, new Employe()).setVisible(true);
            } else {
                new JDialogAddAdmin(self, true, (Employe) table.getValueAt(clickedRow, 0)).setVisible(true);
            }
            //((DefaultTableModel)table.getModel()).removeRow(clickedRow);
        }
    };
    
    private JAdmin self;
    
    /**
     * Creates new form JAdmin
     */
    public JAdmin() {
        
        initComponents();
        self = this;
        //refreshTable();
        
    }
    
    
    protected void refreshTable (){
        jTable.setModel(initAdminTable());
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable.getColumnModel().getColumn(5).setMinWidth(120);
        jTable.getColumnModel().getColumn(6).setMinWidth(120);
        
        TableColumn iconColumn = jTable.getColumnModel().getColumn(7);
        iconColumn.setMinWidth(40);
        iconColumn.setMaxWidth(40);
        ButtonColumn buttonColumn = new ButtonColumn(jTable, delete, iconColumn.getModelIndex());
        
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/images/icon_edit_30.png"));
        
        jTable.setGridColor(Color.LIGHT_GRAY);
        //jTable.setShowGrid(true);
        jTable.setShowHorizontalLines(true);
        jTable.setShowVerticalLines(false);
        jTable.setRowHeight(40);
    }
    
    private DefaultTableModel initAdminTable(){
        
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               if (column == 7){
                   return true;
               }
               
               return false;
            }
        };
        
        tableModel.addColumn("");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prénom");
        tableModel.addColumn("login");
        tableModel.addColumn("e-mail");
        tableModel.addColumn("date d'entrée en Poste");
        tableModel.addColumn("date de sortie de Poste");
        tableModel.addColumn("");
        
        ImageIcon icon_edit = null;
        ImageIcon icon_add = null;
        try {
            icon_edit = new ImageIcon(getClass().getClassLoader().getResource("resources/images/icon_edit_30.png"));
            icon_add = new ImageIcon(getClass().getClassLoader().getResource("resources/images/icon_add_30.png"));
        } catch (java.lang.NullPointerException ex) {}
        
        Vector vec = new Vector();
        vec.add("");
        vec.add("");
        vec.add("");
        vec.add("");
        vec.add("");
        vec.add("");
        vec.add("");
        if (icon_add == null) {
            vec.add("Add");
        } else {
            vec.add(icon_add);
        }
        tableModel.addRow(vec);
        
        for (Employe empl : Employe.getArrayListAllEmploye()){
            vec = new Vector();
            vec.add(empl);
            vec.add(empl.getNomEmploye());
            vec.add(empl.getPrenomEmploye());
            vec.add(empl.getLoginEmploye());
            vec.add(empl.getEmailEmploye());
            vec.add(empl.getDebutPosteEmploye());
            vec.add(empl.getFinPosteEmploye());
            if (icon_edit == null) {
                vec.add("E");
            } else {
                vec.add(icon_edit);
            }
            
            tableModel.addRow(vec);
        }
        
        return tableModel;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        refreshTable();
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(892, 655));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            
            System.out.println("double click");
          }
    }//GEN-LAST:event_jTableMouseClicked

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
            java.util.logging.Logger.getLogger(JAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
