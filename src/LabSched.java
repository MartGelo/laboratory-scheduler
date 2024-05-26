
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;


public final class LabSched extends javax.swing.JFrame {
 private static final String URL = "jdbc:mysql://localhost:3306/labsched?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";
    private Connection con;
    
    public LabSched() {
        initComponents();
        loadTableData();
        connectToDatabase();
    }
    
    private void connectToDatabase() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to Database");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
        }
    }
    

   
        
        private void setupYearLevelSubjectDependencies() {
        CBYearLevel.addActionListener(e -> updateSubjectOptions());
    }

    private void updateSubjectOptions() {
        String selectedYearLevel = (String) CBYearLevel.getSelectedItem();
        DefaultComboBoxModel<String> subjectModel = new DefaultComboBoxModel<>();

        List<String> firstYearSubjects = Arrays.asList("Math 101", "Science 101");
        List<String> secondYearSubjects = Arrays.asList("Math 201", "Science 201");
        List<String> thirdYearSubjects = Arrays.asList("Math 301", "Science 301");
        List<String> fourthYearSubjects = Arrays.asList("Math 401", "Science 401");

        switch (selectedYearLevel) {
            case "First Year" -> {
                for (String subject : firstYearSubjects) {
                    if (!isSubjectTaken(subject, "First Year")) {
                        subjectModel.addElement(subject);
                    }
                }
         }
            case "Second Year" -> {
                for (String subject : secondYearSubjects) {
                    if (!isSubjectTaken(subject, "Second Year")) {
                        subjectModel.addElement(subject);
                    }
                }
         }
            case "Third Year" -> {
                for (String subject : thirdYearSubjects) {
                    if (!isSubjectTaken(subject, "Third Year")) {
                        subjectModel.addElement(subject);
                    }
                }
         }
            case "Fourth Year" -> {
                for (String subject : fourthYearSubjects) {
                    if (!isSubjectTaken(subject, "Fourth Year")) {
                        subjectModel.addElement(subject);
                    }
                }
         }
        }

        CBSubject.setModel(subjectModel);
    }

    private boolean isSubjectTaken(String subject, String yearLevel) {
        DefaultTableModel model = (DefaultTableModel) LabTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingSubject = (String) model.getValueAt(i, 5);
            String existingYearLevel = (String) model.getValueAt(i, 3);
            if (subject.equals(existingSubject) && !yearLevel.equals(existingYearLevel)) {
                return true;
            }
        }
        return false;
    
    }
    
     private void loadTableData() {
       DefaultTableModel model = (DefaultTableModel) LabTable.getModel();
        model.setRowCount(0); // Clear existing data

        if (con != null) {
            try {
                String query = "SELECT * FROM lab";
                try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
                    
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String position = rs.getString("position");
                        String yearLevel = rs.getString("year_level");
                        String section = rs.getString("section");
                        String subject = rs.getString("subject");
                        String month = rs.getString("month");
                        String week = rs.getString("week");
                        String day = rs.getString("day");
                        String semester = rs.getString("semester");
                        String time = rs.getString("time");
                        
                        model.addRow(new Object[]{name, position, yearLevel, section, subject, month, week, day, semester, time});
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        LabTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TName = new javax.swing.JTextField();
        CBMonth = new javax.swing.JComboBox<>();
        CBPosition = new javax.swing.JComboBox<>();
        CBWeek = new javax.swing.JComboBox<>();
        CBDay = new javax.swing.JComboBox<>();
        CBYearLevel = new javax.swing.JComboBox<>();
        CBSection = new javax.swing.JComboBox<>();
        CBSemester = new javax.swing.JComboBox<>();
        CBSubject = new javax.swing.JComboBox<>();
        CBTime = new javax.swing.JComboBox<>();
        BAdd = new javax.swing.JButton();
        BPrint = new javax.swing.JButton();
        BReset = new javax.swing.JButton();
        BSave = new javax.swing.JButton();
        BPreview = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        CBRoom = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(246, 246, 238));

        LabTable.setBackground(new java.awt.Color(158, 209, 206));
        LabTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Position", "Semester", "Year Level", "Section", "Subject", "Time", "Room", "Month", "Week", "Day"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(LabTable);
        if (LabTable.getColumnModel().getColumnCount() > 0) {
            LabTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            LabTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            LabTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            LabTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            LabTable.getColumnModel().getColumn(4).setPreferredWidth(60);
            LabTable.getColumnModel().getColumn(5).setPreferredWidth(180);
            LabTable.getColumnModel().getColumn(6).setPreferredWidth(130);
            LabTable.getColumnModel().getColumn(7).setPreferredWidth(150);
            LabTable.getColumnModel().getColumn(8).setPreferredWidth(80);
            LabTable.getColumnModel().getColumn(9).setPreferredWidth(80);
            LabTable.getColumnModel().getColumn(10).setPreferredWidth(80);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Position:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Month:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Week:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Day:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Year Level:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Section:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Semester");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Subject");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Time:");

        TName.setBackground(new java.awt.Color(175, 228, 228));
        TName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TName.setPreferredSize(new java.awt.Dimension(160, 30));

        CBMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        CBMonth.setPreferredSize(new java.awt.Dimension(160, 30));

        CBPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Affiliate Instructor", "Instructor", "Instructor I", "Instructor II", "Instructor III", "Assistant Professor I", "Assistant Professor II", "Assistant Professor III", "Assistant Professor IV" }));
        CBPosition.setPreferredSize(new java.awt.Dimension(160, 30));

        CBWeek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Week A", "Week B" }));
        CBWeek.setPreferredSize(new java.awt.Dimension(160, 30));

        CBDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));
        CBDay.setPreferredSize(new java.awt.Dimension(160, 30));

        CBYearLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Year", "Second Year", "Third Year - Application Programming", "Third Year - Digital Design", "Fourth Year - Application Programming", "Fourth Year - Digital Design" }));
        CBYearLevel.setPreferredSize(new java.awt.Dimension(160, 30));
        CBYearLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBYearLevelActionPerformed(evt);
            }
        });

        CBSection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I", "1J", "1K", "1L", "1M", "1N", "2A", "2B", "2C", "2D", "2E", "2F", "2G", "2H", "AP-3A", "AP-3B", "AP-3C", "AP-3D", "DD-3A", "DD-3B", "DD-3C", "DD-3D", "AP-4A", "AP-4B", "AP-4C", "AP-4D", "DD-4A", "DD-4B", "DD-4C", "DD-4D" }));
        CBSection.setPreferredSize(new java.awt.Dimension(160, 30));
        CBSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBSectionActionPerformed(evt);
            }
        });

        CBSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Semester", "Second Semester" }));
        CBSemester.setPreferredSize(new java.awt.Dimension(160, 30));

        CBSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laboratory Maintenance", "Introduction to Computing", "Computer Programming 1", "College Algebra", "Introduction to IT", "Communication Skills", "Mathematics in the Modern World", "Philippine History", "Physical Education", " ", " " }));
        CBSubject.setPreferredSize(new java.awt.Dimension(160, 30));

        CBTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00 AM - 9:00 AM", "9:00 AM - 10:00 AM", "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM", "12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM", "2:00 PM - 3:00 PM", "3:00 PM - 4:00 PM", "4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM", "7:00 PM - 8:00 PM", "8:00 PM - 9:00 PM" }));
        CBTime.setPreferredSize(new java.awt.Dimension(160, 30));

        BAdd.setText("Add");
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        BPrint.setBackground(new java.awt.Color(51, 107, 210));
        BPrint.setForeground(new java.awt.Color(255, 255, 255));
        BPrint.setText("Print");

        BReset.setBackground(new java.awt.Color(255, 51, 51));
        BReset.setForeground(new java.awt.Color(255, 255, 255));
        BReset.setText("Reset");

        BSave.setBackground(new java.awt.Color(0, 153, 0));
        BSave.setForeground(new java.awt.Color(255, 255, 255));
        BSave.setText("Save");

        BPreview.setBackground(new java.awt.Color(204, 102, 0));
        BPreview.setForeground(new java.awt.Color(255, 255, 255));
        BPreview.setText("Preview");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Room:");

        CBRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer Laboratory 1", "Computer Laboratory 2A", "Computer Laboratory 2B", "Computer Laboratory 3", "Computer Laboratory 4", "Smart Classroom", "CHS Laboratory", "Audio Visual Room", "ICTC Lobby" }));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ascot.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(BReset, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(BSave, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BAdd)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel11)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBPosition, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBSubject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBWeek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBYearLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBSection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBRoom, 0, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BPreview)
                                .addGap(18, 18, 18)
                                .addComponent(BPrint))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(BAdd)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CBPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CBYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CBSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(CBTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(CBRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(CBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(115, 115, 115))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BReset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
    String name = TName.getText();
    String position = (String) CBPosition.getSelectedItem();
    String room = (String) CBRoom.getSelectedItem();
    String month = (String) CBMonth.getSelectedItem();
    String week = (String) CBWeek.getSelectedItem();
    String day = (String) CBDay.getSelectedItem();
    String time = (String) CBTime.getSelectedItem();
    String yearLevel = (String) CBYearLevel.getSelectedItem();
    String section = (String) CBSection.getSelectedItem();
    String semester = (String) CBSemester.getSelectedItem();
    String subject = (String) CBSubject.getSelectedItem();

    if (name.isEmpty() || position == null || yearLevel == null || section == null || subject == null || month == null || week == null || day == null || time == null || semester == null) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        DefaultTableModel model = (DefaultTableModel) LabTable.getModel();
        boolean slotTaken = false;
        boolean userSubjectConflict = false;
        boolean userTimeConflict = false;

        // Check for conflicts
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingName = (String) model.getValueAt(i, 0);
            String existingMonth = (String) model.getValueAt(i, 8);
            String existingWeek = (String) model.getValueAt(i, 9);
            String existingDay = (String) model.getValueAt(i, 10);
            String existingTime = (String) model.getValueAt(i, 6);
            String existingSubject = (String) model.getValueAt(i, 5);
            String existingSemester = (String) model.getValueAt(i, 3);

            if (name.equals(existingName) && subject.equals(existingSubject)) {
                userSubjectConflict = true;
                break;
            }

            if (name.equals(existingName) && semester.equals(existingSemester) && month.equals(existingMonth) && week.equals(existingWeek) && day.equals(existingDay) && time.equals(existingTime)) {
                userTimeConflict = true;
                break;
            }

            if (semester.equals(existingSemester) && month.equals(existingMonth) && week.equals(existingWeek) && day.equals(existingDay) && time.equals(existingTime)) {
                slotTaken = true;
                break;
            }
        }

        // Handle conflicts
        if (userSubjectConflict) {
            JOptionPane.showMessageDialog(this, "The user is already assigned to this subject.", "User Conflict", JOptionPane.WARNING_MESSAGE);
        } else if (userTimeConflict) {
            JOptionPane.showMessageDialog(this, "The user already has a schedule at this time on the same day.", "Time Conflict", JOptionPane.WARNING_MESSAGE);
        } else if (slotTaken) {
            suggestAvailableTimes(model, semester, month, week, day);
        } else {
            model.addRow(new Object[]{name, position, semester, yearLevel, section, subject, time, room, month, week, day});
                        removeSelectedTimeSlotFromComboBox(month, week, day, time, room, section, yearLevel, semester );

            clearFields();
        }
    }
}
    

private void suggestAvailableTimes(DefaultTableModel model, String semester, String month, String week, String day) {
    String[] times = {"08:00 AM - 09:00 AM", "09:00 AM - 10:00 AM", "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM", "12:00 PM - 01:00 PM", "01:00 PM - 02:00 PM", "02:00 PM - 03:00 PM", "03:00 PM - 04:00 PM", "04:00 PM - 05:00 PM", "05:00 PM - 06:00 PM"};
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] weeks = {"Week A", "Week B"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    int currentMonthIndex = Arrays.asList(months).indexOf(month);
    int currentWeekIndex = Arrays.asList(weeks).indexOf(week);
    int currentDayIndex = Arrays.asList(days).indexOf(day);
    DefaultComboBoxModel<String> timeModel = new DefaultComboBoxModel<>();

    boolean foundAvailableTime = false;

    for (int monthOffset = 0; monthOffset < months.length; monthOffset++) {
        String currentMonth = months[(currentMonthIndex + monthOffset) % months.length];
        for (int weekOffset = 0; weekOffset < weeks.length; weekOffset++) {
            String currentWeek = weeks[(currentWeekIndex + weekOffset) % weeks.length];
            for (int dayOffset = 0; dayOffset < days.length; dayOffset++) {
                String currentDay = days[(currentDayIndex + dayOffset) % days.length];
                for (String time : times) {
                    boolean isAvailable = true;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String existingSemester = (String) model.getValueAt(i, 3);
                        String existingMonth = (String) model.getValueAt(i, 8);
                        String existingWeek = (String) model.getValueAt(i, 9);
                        String existingDay = (String) model.getValueAt(i, 10);
                        String existingTime = (String) model.getValueAt(i, 6);
                        if (semester.equals(existingSemester) && currentMonth.equals(existingMonth) && currentWeek.equals(existingWeek) && currentDay.equals(existingDay) && time.equals(existingTime)) {
                            isAvailable = false;
                            break;
                        }
                    }
                    if (isAvailable) {
                        timeModel.addElement(time);
                        foundAvailableTime = true;
                    }
                }
                if (foundAvailableTime) {
                    CBMonth.setSelectedItem(currentMonth);
                    CBWeek.setSelectedItem(currentWeek);
                    CBDay.setSelectedItem(currentDay);
                    CBTime.setModel(timeModel);
                    JOptionPane.showMessageDialog(this, "The selected time slot is already taken. Please choose another time from the updated list.", "Time Slot Conflict", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
    }

    if (!foundAvailableTime) {
        JOptionPane.showMessageDialog(this, "No available times for the selected period.", "No Available Times", JOptionPane.ERROR_MESSAGE);
    }
}

private void removeSelectedTimeSlotFromComboBox(String month, String week, String day, String time, String room, String section, String yearLevel, String semester) {
    DefaultComboBoxModel<String> timeModel = (DefaultComboBoxModel<String>) CBTime.getModel();
    DefaultComboBoxModel<String> weekModel = (DefaultComboBoxModel<String>) CBWeek.getModel();
    DefaultComboBoxModel<String> dayModel = (DefaultComboBoxModel<String>) CBDay.getModel();
    DefaultComboBoxModel<String> roomModel = (DefaultComboBoxModel<String>) CBRoom.getModel();
    DefaultComboBoxModel<String> sectionModel = (DefaultComboBoxModel<String>) CBSection.getModel();
    DefaultComboBoxModel<String> yearLevelModel = (DefaultComboBoxModel<String>) CBYearLevel.getModel();
    DefaultComboBoxModel<String> semesterModel = (DefaultComboBoxModel<String>) CBSemester.getModel();

    // Remove the selected time slot
    timeModel.removeElement(time);

    // Check if all time slots are used for the current day
    if (timeModel.getSize() == 0) {
        dayModel.removeElement(day);

        // Check if all days are used for the current week
        if (dayModel.getSize() == 0) {
            weekModel.removeElement(week);

            // Check if all weeks are used for the current month
            if (weekModel.getSize() == 0) {
                DefaultComboBoxModel<String> monthModel = (DefaultComboBoxModel<String>) CBMonth.getModel();
                monthModel.removeElement(month);

                // Check if all months are used for the current semester
                if (monthModel.getSize() == 0) {
                    semesterModel.removeElement(semester);

                    // Check if all semesters are used for the current year level
                    if (semesterModel.getSize() == 0) {
                        yearLevelModel.removeElement(yearLevel);

                        // Check if all year levels are used for the current section
                        if (yearLevelModel.getSize() == 0) {
                            sectionModel.removeElement(section);

                            // Check if all sections are used for the current room
                            if (sectionModel.getSize() == 0) {
                                roomModel.removeElement(room);

                                // Reset rooms, sections, year levels, semesters, months, weeks, days, and times
                                resetAllModels(roomModel, sectionModel, yearLevelModel, semesterModel, monthModel, weekModel, dayModel, timeModel);
                            } else {
                                // Reset year levels, semesters, months, weeks, days, and times
                                resetModelsForNextSection(yearLevelModel, semesterModel, monthModel, weekModel, dayModel, timeModel);
                            }
                        } else {
                            // Reset semesters, months, weeks, days, and times
                            resetModelsForNextYearLevel(semesterModel, monthModel, weekModel, dayModel, timeModel);
                        }
                    } else {
                        // Reset months, weeks, days, and times
                        resetModelsForNextSemester(monthModel, weekModel, dayModel, timeModel);
                    }
                } else {
                    // Reset weeks, days, and times
                    resetModelsForNextMonth(weekModel, dayModel, timeModel);
                }
            } else {
                // Reset days and times
                resetDayAndTimeModels(dayModel, timeModel);
            }
        } else {
            // Reset times
            resetTimeModel(timeModel);
        }
    }
}

private void resetAllModels(DefaultComboBoxModel<String> roomModel, DefaultComboBoxModel<String> sectionModel, DefaultComboBoxModel<String> yearLevelModel, DefaultComboBoxModel<String> semesterModel, DefaultComboBoxModel<String> monthModel, DefaultComboBoxModel<String> weekModel, DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    resetRoomModel(roomModel);
    resetSectionModel(sectionModel);
    resetYearLevelModel(yearLevelModel);
    resetSemesterModel(semesterModel);
    resetMonthModel(monthModel);
    resetWeekModel(weekModel);
    resetDayAndTimeModels(dayModel, timeModel);
}

private void resetModelsForNextSection(DefaultComboBoxModel<String> yearLevelModel, DefaultComboBoxModel<String> semesterModel, DefaultComboBoxModel<String> monthModel, DefaultComboBoxModel<String> weekModel, DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    resetYearLevelModel(yearLevelModel);
    resetSemesterModel(semesterModel);
    resetMonthModel(monthModel);
    resetWeekModel(weekModel);
    resetDayAndTimeModels(dayModel, timeModel);
}

private void resetModelsForNextYearLevel(DefaultComboBoxModel<String> semesterModel, DefaultComboBoxModel<String> monthModel, DefaultComboBoxModel<String> weekModel, DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    resetSemesterModel(semesterModel);
    resetMonthModel(monthModel);
    resetWeekModel(weekModel);
    resetDayAndTimeModels(dayModel, timeModel);
}

private void resetModelsForNextSemester(DefaultComboBoxModel<String> monthModel, DefaultComboBoxModel<String> weekModel, DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    resetMonthModel(monthModel);
    resetWeekModel(weekModel);
    resetDayAndTimeModels(dayModel, timeModel);
}

private void resetModelsForNextMonth(DefaultComboBoxModel<String> weekModel, DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    resetWeekModel(weekModel);
    resetDayAndTimeModels(dayModel, timeModel);
}

private void resetRoomModel(DefaultComboBoxModel<String> roomModel) {
    String[] rooms = {"Laboratory 1", "Laboratory 2A", "Laboratory 2B", "Laboratory 3", "Laboratory 4", "Room 6", "Smart Classroom", "ICTC Lobby", "AVR"};
    roomModel.removeAllElements();
    for (String r : rooms) {
        roomModel.addElement(r);
    }
}

private void resetSectionModel(DefaultComboBoxModel<String> sectionModel) {
    String[] sections = {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I", "1J", "1K", "1L", "1M", "1N"};
    sectionModel.removeAllElements();
    for (String s : sections) {
        sectionModel.addElement(s);
    }
}

private void resetYearLevelModel(DefaultComboBoxModel<String> yearLevelModel) {
    String[] yearLevels = {"First Year", "Second Year", "Third Year - Application Programming", "Third Year - Digital Design", "Fourth Year - Application Programming", "Fourth Year - Digital Design"};
    yearLevelModel.removeAllElements();
    for (String y : yearLevels) {
        yearLevelModel.addElement(y);
    }
}

private void resetSemesterModel(DefaultComboBoxModel<String> semesterModel) {
    String[] semesters = {"First Semester", "Second Semester "};
    semesterModel.removeAllElements();
    for (String s : semesters) {
        semesterModel.addElement(s);
    }
}

private void resetMonthModel(DefaultComboBoxModel<String> monthModel) {
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    monthModel.removeAllElements();
    for (String m : months) {
        monthModel.addElement(m);
    }
}

private void resetWeekModel(DefaultComboBoxModel<String> weekModel) {
    String[] weeks = {"Week A", "Week B"};
    weekModel.removeAllElements();
    for (String w : weeks) {
        weekModel.addElement(w);
    }
}

private void resetDayAndTimeModels(DefaultComboBoxModel<String> dayModel, DefaultComboBoxModel<String> timeModel) {
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    dayModel.removeAllElements();
    for (String d : days) {
        dayModel.addElement(d);
    }

    resetTimeModel(timeModel);
}

private void resetTimeModel(DefaultComboBoxModel<String> timeModel) {
    String[] times = {"08:00 AM - 09:00 AM", "09:00 AM - 10:00 AM", "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM", "12:00 PM - 01:00 PM", "01:00 PM - 02:00 PM", "02:00 PM - 03:00 PM", "03:00 PM - 04:00 PM", "04:00 PM - 05:00 PM", "05:00 PM - 06:00 PM"};
    timeModel.removeAllElements();
    for (String t : times) {
        timeModel.addElement(t);
    }
}




    private void clearFields() {
        TName.setText("");
        CBPosition.setSelectedIndex(0);
        CBYearLevel.setSelectedIndex(0);
        CBSection.setSelectedIndex(0);
        CBRoom.setSelectedIndex(0);
        CBSubject.setSelectedIndex(0);
        CBMonth.setSelectedIndex(0);
        CBWeek.setSelectedIndex(0);
        CBDay.setSelectedIndex(0);
        CBTime.setSelectedIndex(0);
        CBSemester.setSelectedIndex(0);

    }//GEN-LAST:event_BAddActionPerformed

    private void CBSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBSectionActionPerformed

    private void CBYearLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBYearLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBYearLevelActionPerformed

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
            java.util.logging.Logger.getLogger(LabSched.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     //</editor-fold>
     //</editor-fold>
     //</editor-fold>
     //</editor-fold>
     
     //</editor-fold>
     //</editor-fold>
     //</editor-fold>
     //</editor-fold>
     
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LabSched().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BPreview;
    private javax.swing.JButton BPrint;
    private javax.swing.JButton BReset;
    private javax.swing.JButton BSave;
    private javax.swing.JComboBox<String> CBDay;
    private javax.swing.JComboBox<String> CBMonth;
    private javax.swing.JComboBox<String> CBPosition;
    private javax.swing.JComboBox<String> CBRoom;
    private javax.swing.JComboBox<String> CBSection;
    private javax.swing.JComboBox<String> CBSemester;
    private javax.swing.JComboBox<String> CBSubject;
    private javax.swing.JComboBox<String> CBTime;
    private javax.swing.JComboBox<String> CBWeek;
    private javax.swing.JComboBox<String> CBYearLevel;
    private javax.swing.JTable LabTable;
    private javax.swing.JTextField TName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    // End of variables declaration//GEN-END:variables
}
