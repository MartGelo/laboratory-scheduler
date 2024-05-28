
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Kenneth
 */
public class adminPanel extends javax.swing.JFrame {
private final String url = "jdbc:mysql://localhost:3306/labsched";
    private final String user = "root";
    private final String password = "12345";
    private Connection conn = null;
    /**
     * Creates new form adminPanel
     */
    public adminPanel() {
        initComponents();
          try {
            conn = DriverManager.getConnection(url, user, password);
            populateLabTable(); // Populate the LabTable
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + ex.getMessage(), "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 private void populateLabTable() {
        try {
            String query = "SELECT * FROM lab"; // Assuming your table name is lab_table
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Populate the table model with data from the result set
            DefaultTableModel model = (DefaultTableModel) LabTable.getModel();
            model.setRowCount(0); // Clear existing data
            while (resultSet.next()) {
                Object[] row = new Object[]{
                    resultSet.getString("name"),
                    resultSet.getString("position"),
                    resultSet.getString("semester"),
                    resultSet.getString("year_level"),
                    resultSet.getString("section"),
                    resultSet.getString("subject"),
                    resultSet.getString("time"),
                    resultSet.getString("room"),
                    resultSet.getString("month"),
                    resultSet.getString("week"),
                    resultSet.getString("day"),
                    resultSet.getString("status")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to fetch data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LabTable = new javax.swing.JTable();
        BPrint = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        acceptbtn = new javax.swing.JButton();
        declinebtn = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        BLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 16)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ascot-removebg-preview (1).png"))); // NOI18N
        jLabel13.setText("LABORATORY SCHEDULER");

        jPanel1.setBackground(new java.awt.Color(158, 209, 206));
        jPanel1.setPreferredSize(new java.awt.Dimension(1534, 800));

        LabTable.setBackground(new java.awt.Color(246, 246, 238));
        LabTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Position", "Semester", "Year Level", "Section", "Subject", "Time", "Room", "Month", "Week", "Day", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        LabTable.setRowHeight(40);
        jScrollPane1.setViewportView(LabTable);

        BPrint.setBackground(new java.awt.Color(204, 102, 0));
        BPrint.setForeground(new java.awt.Color(255, 255, 255));
        BPrint.setText("Print");
        BPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPrintActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel12.setText("Powered by: Department of Information Technology");

        acceptbtn.setBackground(new java.awt.Color(204, 102, 0));
        acceptbtn.setForeground(new java.awt.Color(255, 255, 255));
        acceptbtn.setText("Accept");
        acceptbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptbtnActionPerformed(evt);
            }
        });

        declinebtn.setBackground(new java.awt.Color(204, 102, 0));
        declinebtn.setForeground(new java.awt.Color(255, 255, 255));
        declinebtn.setText("Decline");
        declinebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declinebtnActionPerformed(evt);
            }
        });

        deletebtn.setBackground(new java.awt.Color(204, 102, 0));
        deletebtn.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(526, 526, 526))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptbtn)
                        .addGap(18, 18, 18)
                        .addComponent(declinebtn)
                        .addGap(18, 18, 18)
                        .addComponent(deletebtn)
                        .addGap(489, 489, 489))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(declinebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(38, 38, 38))
        );

        BLogout.setBackground(new java.awt.Color(204, 102, 0));
        BLogout.setForeground(new java.awt.Color(255, 255, 255));
        BLogout.setText("Logout");
        BLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLogoutActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(627, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(549, 549, 549)
                .addComponent(BLogout)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1521, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(BLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void BLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLogoutActionPerformed
        // Ask for logout confirmation
        int response = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to log out?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Check the user's response
        if (response == JOptionPane.YES_OPTION) {
            // Perform logout logic
            dispose();
        }
    }//GEN-LAST:event_BLogoutActionPerformed

    private void declinebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declinebtnActionPerformed
   int selectedRow = LabTable.getSelectedRow();
    if (selectedRow != -1) {
        String name = LabTable.getValueAt(selectedRow, 0).toString(); // Get the name from the selected row
        try {
            String deleteQuery = "DELETE FROM lab WHERE name = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.setString(1, name);
            int deletedRows = deleteStatement.executeUpdate();
            if (deletedRows > 0) {
                // Update the status column in the JTable to "Declined"
                LabTable.setValueAt("Declined", selectedRow, 11); // Assuming status column is at index 11
                JOptionPane.showMessageDialog(this, "Record deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete record", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to delete record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_declinebtnActionPerformed

    private void acceptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptbtnActionPerformed
       int selectedRow = LabTable.getSelectedRow();
    if (selectedRow != -1) {
        String name = LabTable.getValueAt(selectedRow, 0).toString(); // Get the name from the selected row
        try {
            String query = "UPDATE lab SET status = 'Accepted' WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(this, "Status updated to Accepted", "Success", JOptionPane.INFORMATION_MESSAGE);
                LabTable.setValueAt("Accepted", selectedRow, 11); // Update the status in the table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update status", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to update status: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_acceptbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
         int selectedRow = LabTable.getSelectedRow();
    if (selectedRow != -1) {
        String status = LabTable.getValueAt(selectedRow, 11).toString(); // Get the status from the selected row

        // Check if the status is "Accepted"
        if (status.equals("Accepted")) {
            JOptionPane.showMessageDialog(this, "Cannot delete record with status 'Accepted'", "Deletion Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // If the status is not "Accepted", proceed with deletion
            DefaultTableModel model = (DefaultTableModel) LabTable.getModel();
            model.removeRow(selectedRow); // Remove the selected row from the JTable
            
            // You can add code here to update the status in the database if needed
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to delete", "Selection Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deletebtnActionPerformed
private void printTable(JTable table) {
      try {
        // Baguhin ang laki ng JTable bago ito i-print
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        boolean complete = table.print(); // I-print ang JTable
        if (complete) {
            System.out.println("Printing Successful");
        } else {
            System.out.println("Printing Cancelled");
        }
    } catch (PrinterException pe) {
        System.out.println("Printing Failed: " + pe.getMessage());
    }
}
    private void BPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPrintActionPerformed
       printTable(LabTable); 
    }//GEN-LAST:event_BPrintActionPerformed

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
            java.util.logging.Logger.getLogger(adminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BLogout;
    private javax.swing.JButton BPrint;
    private javax.swing.JTable LabTable;
    private javax.swing.JButton acceptbtn;
    private javax.swing.JButton declinebtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
