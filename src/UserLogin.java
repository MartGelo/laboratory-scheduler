import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.prefs.Preferences;
import java.awt.event.*;




public class UserLogin extends javax.swing.JFrame {

    private static final String URL = "jdbc:mysql://localhost/labsched";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    Connection conn;
Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    
    public UserLogin() {
        initComponents();
        Connect();
    }
    
public class LoginForm {
    private JTextField TEmail;
    private JPasswordField PPassword;
    private JCheckBox CBRemember;
    private JButton loginButton;
    private JPanel panel;
      private JPopupMenu popup;

    public LoginForm() {
        // Initialize your components here
        TEmail = new JTextField(20);
        PPassword = new JPasswordField(20);
        CBRemember = new JCheckBox("Remember Me");
        loginButton = new JButton("Login");
        
         // Initialize popup menu
        popup = new JPopupMenu();

        // Load saved email and password if available
        loadSavedCredentials();

        // Add a listener to the email field to suggest password
        TEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                suggestPassword();
            }
        });

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // Perform login logic here
            }
         });
            // Add action listener to the "Remember Me" checkbox
        CBRemember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CBRememberActionPerformed(evt);
            }

        });

        // Create a panel and add components to it
        panel = new JPanel();
        panel.add(new JLabel("Email:"));
        panel.add(TEmail);
        panel.add(new JLabel("Password:"));
        panel.add(PPassword);
        panel.add(CBRemember);
        panel.add(loginButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    private void loadSavedCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        String savedEmail = prefs.get("userEmail", "");
        String savedPassword = prefs.get("userPassword", "");

        TEmail.setText(savedEmail);
        if (!savedEmail.isEmpty()) {
            PPassword.setText(savedPassword);
            CBRemember.setSelected(true);
        }
    }

    private void suggestPassword() {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        String savedEmail = prefs.get("userEmail", "");
        String savedPassword = prefs.get("userPassword", "");

        String currentEmail = TEmail.getText();
        if (currentEmail.equals(savedEmail)) {
            // Suggest the password
            JPopupMenu popup = new JPopupMenu();
            JMenuItem suggestion = new JMenuItem(savedPassword);
            suggestion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PPassword.setText(savedPassword);
                     popup.setVisible(false);
                }
            });
            popup.add(suggestion);
            popup.show(TEmail, 0, TEmail.getHeight());
        } else {
            popup.setVisible(false);
        }
    }

    private void saveCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.put("userEmail", TEmail.getText());
        prefs.put("userPassword", new String(PPassword.getPassword()));
    }

    private void clearCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.remove("userEmail");
        prefs.remove("userPassword");
    }
}
    
 public void Connect(){
        try {
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost/labsched","root", "12345");
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
       }
 }
 
  private boolean confirmEmailInDatabase(String email) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/labsched", "root", "12345");
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                return rs.next(); // Returns true if the email exists in the database
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void saveNewPassword(String email, String newPassword) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/labsched", "root", "12345");
            String query = "UPDATE users SET password = ? WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newPassword);
                pstmt.setString(2, email);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Password changed successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to change password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TEmail = new javax.swing.JTextField();
        PPassword = new javax.swing.JPasswordField();
        BLogin = new javax.swing.JButton();
        BSignup = new javax.swing.JButton();
        BForgot = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        CBShowpassword = new javax.swing.JCheckBox();
        CBRemember = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Cooper Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ascot-removebg-preview (1).png"))); // NOI18N
        jLabel1.setText("Log in");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Email Address:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Password:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel4.setText("Doesn't have an account?");

        TEmail.setBackground(new java.awt.Color(0, 153, 153));

        PPassword.setBackground(new java.awt.Color(0, 153, 153));

        BLogin.setBackground(new java.awt.Color(0, 204, 204));
        BLogin.setForeground(new java.awt.Color(255, 255, 255));
        BLogin.setText("Log in");
        BLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLoginActionPerformed(evt);
            }
        });

        BSignup.setBackground(new java.awt.Color(0, 204, 204));
        BSignup.setForeground(new java.awt.Color(255, 255, 255));
        BSignup.setText("Sign Up");
        BSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSignupActionPerformed(evt);
            }
        });

        BForgot.setText("Forgot");
        BForgot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BForgotActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setText("Forgot Password?");

        CBShowpassword.setText("Show Password");
        CBShowpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBShowpasswordActionPerformed(evt);
            }
        });

        CBRemember.setText("Remember Password");
        CBRemember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBRememberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BSignup))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(CBRemember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CBShowpassword))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(PPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BLogin)))))
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BForgot)
                        .addGap(320, 320, 320))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(PPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBShowpassword)
                    .addComponent(CBRemember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BForgot)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(BSignup))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BLoginActionPerformed(java.awt.event.ActionEvent evt) {                                       
   String userEmail = TEmail.getText();
    String userPassword = new String(PPassword.getPassword());

    try {
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        ps.setString(1, userEmail);
        ps.setString(2, userPassword);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String userStatus = rs.getString("status"); // Assuming the column name for status is "status"

            // If login is successful, close the success message dialog
            JOptionPane.showMessageDialog(this, "Login successful!");

            // Close the login frame or panel
            setVisible(false); // Assuming this code is inside your login frame or panel

            adminPanel dashboardPanel = null;
            try {
                dashboardPanel = new adminPanel();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            LabSched userPanel = null;
            try {
                userPanel = new LabSched();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (userStatus.equalsIgnoreCase("admin")) { // Assuming the status is either "admin" or "user"
                // Show the dashboard panel
                dashboardPanel.setVisible(true);
            } else { // Assuming the status is either "admin" or "user"
                // Show the user panel
                userPanel.setVisible(true);
            }

            // Optionally, you can clear the input fields after successful login
            TEmail.setText("");
            PPassword.setText("");

            // Check if Remember Password is selected
            if (CBRemember.isSelected()) {
                // Save the email and password in preferences
                Preferences prefs = Preferences.userNodeForPackage(getClass());
                prefs.put("userEmail", userEmail);
                prefs.put("userPassword", userPassword);
            }
        } else {
            // If login fails, show an error dialog
            JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    }                                       
                                     
                                  

    private void BSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSignupActionPerformed
        UserSignup signUp = new UserSignup();
        signUp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BSignupActionPerformed

    private void BForgotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BForgotActionPerformed
       openForgotPasswordDialog();

    }//GEN-LAST:event_BForgotActionPerformed

    private void CBShowpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBShowpasswordActionPerformed
 // Check if the checkbox is selected
    if (CBShowpassword.isSelected()) {
        // Show the password
        PPassword.setEchoChar((char) 0); // Set the echo char to 0 to display the password in plain text
    } else {
        // Hide the password
        PPassword.setEchoChar('\u25cf'); // Reset the echo char to default (bullet character) to hide the password
    }

    }//GEN-LAST:event_CBShowpasswordActionPerformed

    private void CBRememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBRememberActionPerformed
  // Check if the checkbox is selected
    if (CBRemember.isSelected()) {
        // Save the email and password in preferences
        String userEmail = TEmail.getText();
        String userPassword = new String(PPassword.getPassword());

        // Use the Preferences API to store the user's email and password
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.put("userEmail", userEmail);
        prefs.put("userPassword", userPassword);
    } else {
        // If the checkbox is not selected, clear the stored email and password
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.remove("userEmail");
        prefs.remove("userPassword");
    }


    }//GEN-LAST:event_CBRememberActionPerformed

private void openForgotPasswordDialog() {
    JDialog dialog = new JDialog(this, "Create New Password", true);
    JTextField emailField = new JTextField(20);
    JPasswordField newPasswordField = new JPasswordField(20);
    JCheckBox rememberPasswordCheckbox = new JCheckBox("Remember Password");
    JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");
    JButton saveButton = new JButton("Save");

    // Define a variable to store the original password field's echo char
    char defaultEchoChar = newPasswordField.getEchoChar();

    // Retrieve stored email and password, if available
    String storedEmail = preferences.get("email", "");
    String storedPassword = preferences.get("password", "");
    if (!storedEmail.isEmpty()) {
        emailField.setText(storedEmail);
        newPasswordField.setText(storedPassword);
        rememberPasswordCheckbox.setSelected(true);
    }
  showPasswordCheckbox.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Show the password
                newPasswordField.setEchoChar((char) 0); // Set the echo char to 0 to display the password in plain text
            } else {
                // Hide the password
                newPasswordField.setEchoChar(defaultEchoChar); // Reset the echo char to default to hide the password
            }
        }
    });
    saveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String newPassword = new String(newPasswordField.getPassword());
        
        // Check if email is empty or does not end with "@gmail.com"
        if (email.isEmpty() || !email.toLowerCase().endsWith("@gmail.com")) {
            JOptionPane.showMessageDialog(dialog, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!confirmEmailInDatabase(email)) {
            JOptionPane.showMessageDialog(dialog, "Email does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if new password is empty
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "New password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Store email and password if "Remember Password" is selected
        if (rememberPasswordCheckbox.isSelected()) {
            preferences.put("email", email);
            preferences.put("password", newPassword);
        }
        
        saveNewPassword(email, newPassword);
        dialog.dispose();
    }
});
    
    
    

    JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5)); // Adjust grid layout for spacing
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    panel.add(new JLabel("Enter your email:"));
    panel.add(emailField);
    panel.add(new JLabel("Enter your new password:"));
    panel.add(newPasswordField);
    panel.add(rememberPasswordCheckbox);
    panel.add(showPasswordCheckbox);
    panel.add(saveButton);

    dialog.add(panel);
    dialog.pack();
    dialog.setLocationRelativeTo(null); // Center the dialog on the screen
    dialog.setVisible(true);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UserLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BForgot;
    private javax.swing.JButton BLogin;
    private javax.swing.JButton BSignup;
    private javax.swing.JCheckBox CBRemember;
    private javax.swing.JCheckBox CBShowpassword;
    private javax.swing.JPasswordField PPassword;
    private javax.swing.JTextField TEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    
}
