import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public final class LabSched extends javax.swing.JFrame {
    private static final String JDBC_URL = "jdbc:mysql://localhost/labsched";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";
    private Connection con;
    private int roomUsageCount;
    private Component frame;
 
    
    public LabSched() throws ClassNotFoundException {
        initComponents();
        connectToDatabase();
        loadTableData();
        
         // Place the mouse click event listener code here
        LabTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = LabTable.getSelectedRow();
                if (selectedRow >= 0) {
                    DefaultTableModel model = (DefaultTableModel) LabTable.getModel();

                    // Fetch data from the selected row in the table
                    String name = (String) model.getValueAt(selectedRow, 0);
                    String position = (String) model.getValueAt(selectedRow, 1);
                    String semester = (String) model.getValueAt(selectedRow, 2);
                    String yearLevel = (String) model.getValueAt(selectedRow, 3);
                    String section = (String) model.getValueAt(selectedRow, 4);
                    String subject = (String) model.getValueAt(selectedRow, 5);
                    String time = (String) model.getValueAt(selectedRow, 6);
                    String room = (String) model.getValueAt(selectedRow, 7);
                    String month = (String) model.getValueAt(selectedRow, 8);
                    String week = (String) model.getValueAt(selectedRow, 9);
                    String day = (String) model.getValueAt(selectedRow, 10);

                    // Populate fields with fetched data
                    TName.setText(name);
                    CBPosition.setSelectedItem(position);
                    CBSemester.setSelectedItem(semester);
                    CBYearLevel.setSelectedItem(yearLevel);
                    CBSection.setSelectedItem(section);
                    CBSubject.setSelectedItem(subject);
                    CBTime.setSelectedItem(time);
                    CBRoom.setSelectedItem(room);
                    CBMonth.setSelectedItem(month);
                    CBWeek.setSelectedItem(week);
                    CBDay.setSelectedItem(day);
                }
            }
        });
    
        
          
        
    }
    
 private void connectToDatabase() throws ClassNotFoundException {
           try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/labsched","root", "12345");           
           System.out.println("Connected to the database. ");
        } catch (SQLException ex) {
        }
    
    }
 
 
 public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/labsched";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

  
        private void setupYearLevelSubjectDependencies() {
        CBYearLevel.addActionListener(e -> updateSubjectOptions());
    }

    private void updateSubjectOptions() {
        String selectedYearLevel = (String) CBYearLevel.getSelectedItem();
        DefaultComboBoxModel<String> subjectModel = new DefaultComboBoxModel<>();

        List<String> firstYearSubjects = Arrays.asList("Introduction to Computing",  
"Programming 1", "Fundamentals of Database Systems", "Purposive Communication", "Understanding the Self", "Mathematics in the Modern World", "Self-Testing Activities", "National Service Training Program 1");
        List<String> secondYearSubjects = Arrays.asList("Networking 1", 	
"Desktop Publishing", "Web and Multimedia Systems", "Information Management", "Ethics", "Science, Technology & Society", "Life and Works of Rizal", "Individual and Dual Sports");
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
                    String semester = rs.getString("semester");
                    String yearLevel = rs.getString("year_level");
                    String section = rs.getString("section");
                    String subject = rs.getString("subject");
                    String time = rs.getString("time");
                    String room = rs.getString("room");
                    String month = rs.getString("month");
                    String week = rs.getString("week");
                    String day = rs.getString("day");
                    String status = rs.getString("status");
                    
                    model.addRow(new Object[]{name, position, semester, yearLevel, section, subject, time, room, month, week, day, status});
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
        BEdit = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        CBRoom = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        BLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(158, 209, 206));

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
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        LabTable.setRowHeight(40);
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
            LabTable.getColumnModel().getColumn(11).setPreferredWidth(150);
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

        TName.setBackground(new java.awt.Color(246, 246, 238));
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

        CBSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laboratory Maintenance", "First Year - First Sem", "Introduction to Computing ", "Programming 1", "Fundamentals of Database Systems\t", "Purposive Communication / Malayuning Komunikasyon\t", "Understanding the Self / Pang Unawa sa Sarili", "Mathematics in the Modern World / Matematika sa Makabagong Daigdig", "Self-Testing Activities", "National Service Training Program 1", "First Sem - Second Sem", "Programming 2\t\t", "Introduction to IT Systems\t", "Data Structures and Algorithms\t", "Readings in Philippine History / Mga Babasahin hinggil", "sa Kasaysayan ng Pilipinas\t", "Art Appreciation / Pagpapahalaga sa Sining\t", "The Contemporary World / Ang Kasalukuyang Daigdig", "Kontekswalisadong Komunikasyon sa Filipino", "Rhythmic Activities\t", "National Service Training Program 2", "Second Year - First Sem", "Networking 1\t", "Desktop Publishing\t", "Web and Multimedia Systems\t", "Information Management", "Ethics / Etika\t", "Science, Technology & Society / Agham, Teknolohiya at Lipunan\t", "Life and Works of Rizal\t", "Individual and Dual Sports", "Scond Year - Second Sem", "Developments and Emerging Technologies\t", "Discrete Mathematics\t", "Networking 2\t", "Introduction to Visual Design\t", "Introduction to Human Computer Interaction\t", "Filipino sa Iba’t-Ibang Disiplina\t", "Philippine Popular Culture", "Team Sports\t", "Third Year - First Sem AP&DD", "Information Assurance and Security\t", "Quantitative Methods\t", "Current Trends in IT\t", "System Administration and Maintenance", "Fundamentals of Video Production\t", "Mathematics and Statistics for IT\t", "Reading Visual Arts\t", "Third Year - Second Sem AP&DD", "Integrative Programming Technologies\t", "Platform Technologies\t", "Object Oriented Programming\t", "Technical and Professional Communications\t", "IT Business Ventures\t", "People and the Earth’s Ecosystem\t", "Fundamentals of Digital Sound Production\t", "Video Processing", "Fourth Year - First Sem", "Capstone Project and Research 2\t", "Social and Professional Issues in IT\t", "Image Processing\t", "Organic Modeling\t", "Cybersecurity\t", "English for IT\t" }));
        CBSubject.setPreferredSize(new java.awt.Dimension(160, 30));

        CBTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00 AM - 9:00 AM", "9:00 AM - 10:00 AM", "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM", "12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM", "2:00 PM - 3:00 PM", "3:00 PM - 4:00 PM", "4:00 PM - 5:00 PM", "5:00 PM - 6:00 PM", "6:00 PM - 7:00 PM", "7:00 PM - 8:00 PM", "8:00 PM - 9:00 PM" }));
        CBTime.setPreferredSize(new java.awt.Dimension(160, 30));

        BAdd.setBackground(new java.awt.Color(204, 102, 0));
        BAdd.setForeground(new java.awt.Color(255, 255, 255));
        BAdd.setText("Add");
        BAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        BEdit.setBackground(new java.awt.Color(204, 102, 0));
        BEdit.setForeground(new java.awt.Color(255, 255, 255));
        BEdit.setText("Edit");
        BEdit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Room:");

        CBRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer Laboratory 1", "Computer Laboratory 2A", "Computer Laboratory 2B", "Computer Laboratory 3", "Computer Laboratory 4", "Smart Classroom", "CHS Laboratory", "Audio Visual Room", "ICTC Lobby" }));

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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel12.setText("Powered by: Department of Information Technology");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 775, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(607, 607, 607))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(TName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CBPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CBYearLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(CBSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(CBSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(CBTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(CBRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(CBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(CBWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CBDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        BLogout.setBackground(new java.awt.Color(204, 102, 0));
        BLogout.setForeground(new java.awt.Color(255, 255, 255));
        BLogout.setText("Logout");
        BLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(761, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(549, 549, 549)
                .addComponent(BLogout)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1653, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
     int selectedRow = LabTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to edit.", "No Row Selected", JOptionPane.ERROR_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) LabTable.getModel();

    String name = TName.getText();
    String position = (String) CBPosition.getSelectedItem();
    String semester = (String) CBSemester.getSelectedItem();
    String yearLevel = (String) CBYearLevel.getSelectedItem();
    String section = (String) CBSection.getSelectedItem();
    String subject = (String) CBSubject.getSelectedItem();
    String time = (String) CBTime.getSelectedItem();
    String room = (String) CBRoom.getSelectedItem();
    String month = (String) CBMonth.getSelectedItem();
    String week = (String) CBWeek.getSelectedItem();
    String day = (String) CBDay.getSelectedItem();

    if (name.isEmpty() || position == null || semester == null || yearLevel == null || section == null || subject == null || time == null || room == null || month == null || week == null || day == null) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    boolean slotTaken = false, userSubjectConflict = false, userTimeConflict = false;

    for (int i = 0; i < model.getRowCount(); i++) {
        if (i != selectedRow) {
            String existingName = (String) model.getValueAt(i, 0);
            String existingSemester = (String) model.getValueAt(i, 2);
            String existingMonth = (String) model.getValueAt(i, 8);
            String existingWeek = (String) model.getValueAt(i, 9);
            String existingDay = (String) model.getValueAt(i, 10);
            String existingTime = (String) model.getValueAt(i, 6);
            String existingSubject = (String) model.getValueAt(i, 5);

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
    }

    if (userSubjectConflict) {
        JOptionPane.showMessageDialog(this, "This teacher is already assigned to the selected subject.", "Subject Conflict", JOptionPane.ERROR_MESSAGE);
    } else if (userTimeConflict) {
        JOptionPane.showMessageDialog(this, "This teacher already has a schedule at the selected time.", "Time Conflict", JOptionPane.ERROR_MESSAGE);
    } else if (slotTaken) {
        JOptionPane.showMessageDialog(this, "The selected time slot is already taken.", "Slot Taken", JOptionPane.ERROR_MESSAGE);
    } else {
        String oldName = (String) model.getValueAt(selectedRow, 0);
        String oldPosition = (String) model.getValueAt(selectedRow, 1);
        String oldSemester = (String) model.getValueAt(selectedRow, 2);
        String oldYearLevel = (String) model.getValueAt(selectedRow, 3);
        String oldSection = (String) model.getValueAt(selectedRow, 4);
        String oldSubject = (String) model.getValueAt(selectedRow, 5);
        String oldTime = (String) model.getValueAt(selectedRow, 6);
        String oldRoom = (String) model.getValueAt(selectedRow, 7);
        String oldMonth = (String) model.getValueAt(selectedRow, 8);
        String oldWeek = (String) model.getValueAt(selectedRow, 9);
        String oldDay = (String) model.getValueAt(selectedRow, 10);

        updateDataInDatabase(oldName, oldPosition, oldSemester, oldYearLevel, oldSection, oldSubject, oldTime, oldRoom, oldMonth, oldWeek, oldDay,
                             name, position, semester, yearLevel, section, subject, time, room, month, week, day);

        model.setValueAt(name, selectedRow, 0);
        model.setValueAt(position, selectedRow, 1);
        model.setValueAt(semester, selectedRow, 2);
        model.setValueAt(yearLevel, selectedRow, 3);
        model.setValueAt(section, selectedRow, 4);
        model.setValueAt(subject, selectedRow, 5);
        model.setValueAt(time, selectedRow, 6);
        model.setValueAt(room, selectedRow, 7);
        model.setValueAt(month, selectedRow, 8);
        model.setValueAt(week, selectedRow, 9);
        model.setValueAt(day, selectedRow, 10);

        JOptionPane.showMessageDialog(this, "Schedule updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    }//GEN-LAST:event_BEditActionPerformed
    private void updateDataInDatabase(String oldName, String oldPosition, String oldSemester, String oldYearLevel, String oldSection, String oldSubject, String oldTime, String oldRoom, String oldMonth, String oldWeek, String oldDay,
                                  String newName, String newPosition, String newSemester, String newYearLevel, String newSection, String newSubject, String newTime, String newRoom, String newMonth, String newWeek, String newDay) {
    String updateSQL = "UPDATE lab SET name = ?, position = ?, semester = ?, year_level = ?, section = ?, subject = ?, time = ?, room = ?, month = ?, week = ?, day = ? " +
                       "WHERE name = ? AND position = ? AND semester = ? AND year_level = ? AND section = ? AND subject = ? AND time = ? AND room = ? AND month = ? AND week = ? AND day = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

        pstmt.setString(1, newName);
        pstmt.setString(2, newPosition);
        pstmt.setString(3, newSemester);
        pstmt.setString(4, newYearLevel);
        pstmt.setString(5, newSection);
        pstmt.setString(6, newSubject);
        pstmt.setString(7, newTime);
        pstmt.setString(8, newRoom);
        pstmt.setString(9, newMonth);
        pstmt.setString(10, newWeek);
        pstmt.setString(11, newDay);
        pstmt.setString(12, oldName);
        pstmt.setString(13, oldPosition);
        pstmt.setString(14, oldSemester);
        pstmt.setString(15, oldYearLevel);
        pstmt.setString(16, oldSection);
        pstmt.setString(17, oldSubject);
        pstmt.setString(18, oldTime);
        pstmt.setString(19, oldRoom);
        pstmt.setString(20, oldMonth);
        pstmt.setString(21, oldWeek);
        pstmt.setString(22, oldDay);

        pstmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error updating data in database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
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

        // Check if the selected laboratory room already has 3 users
        int roomUserCount = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingRoom = (String) model.getValueAt(i, 7);
            if (room.equals(existingRoom)) {
                roomUserCount++;
            }
        }

        if (roomUserCount >= 3) {
            JOptionPane.showMessageDialog(this, "This laboratory is full. Please select another laboratory.", "Room Full", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmDialog = JOptionPane.showConfirmDialog(this, "Do you want to add teacher schedule?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            boolean slotTaken = checkIfSlotTaken(room, month, week, day, time, semester);

            if (slotTaken) {
                JOptionPane.showMessageDialog(this, "The selected slot is already taken. Please choose a different time or room.", "Slot Taken", JOptionPane.ERROR_MESSAGE);
            } else {
                model.addRow(new Object[]{name, position, semester, yearLevel, section, subject, time, room, month, week, day});
                removeSelectedTimeSlotFromComboBox(month, week, day, time, room, section, yearLevel, semester);

                // Save data to database
                saveDataToDatabase(name, position, room, month, week, day, time, yearLevel, section, semester, subject);

                clearFields();
                loadTableData(); // Refresh the table data to show the newly added entry
            }
        }
    }
}
    
    private boolean checkIfSlotTaken(String room, String month, String week, String day, String time, String semester) {
    String checkSQL = "SELECT COUNT(*) FROM lab WHERE room = ? AND month = ? AND week = ? AND day = ? AND time = ? AND semester = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(checkSQL)) {

        pstmt.setString(1, room);
        pstmt.setString(2, month);
        pstmt.setString(3, week);
        pstmt.setString(4, day);
        pstmt.setString(5, time);
        pstmt.setString(6, semester);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Return true if there is already an entry with the same details
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error checking slot availability: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

private void saveDataToDatabase(String name, String position, String room, String month, String week, String day, String time, String yearLevel, String section, String semester, String subject) {
    String insertSQL = "INSERT INTO lab (name, position, room, month, week, day, time, year_level, section, semester, subject, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

        pstmt.setString(1, name);
        pstmt.setString(2, position);
        pstmt.setString(3, room);
        pstmt.setString(4, month);
        pstmt.setString(5, week);
        pstmt.setString(6, day);
        pstmt.setString(7, time);
        pstmt.setString(8, yearLevel);
        pstmt.setString(9, section);
        pstmt.setString(10, semester);
        pstmt.setString(11, subject);
        pstmt.setString(12, "Pending");

        pstmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saving data to database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    private void suggestAlternativeRoom() {
    String[] rooms = {"Laboratory 1", "Laboratory 2A", "Laboratory 2B", "Laboratory 3", "Laboratory 4", "Room 6", "Smart Classroom", "ICTC Lobby", "AVR"};
    DefaultComboBoxModel<String> roomModel = new DefaultComboBoxModel<>(rooms);
    CBRoom.setModel(roomModel);
    JOptionPane.showMessageDialog(this, "The selected laboratory room is full for the day. Please choose another room.", "Room Full", JOptionPane.WARNING_MESSAGE);
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
                        if (!isAvailable) {
                            break;
                        }
                    }
                    if (isAvailable) {
                        int duration = 0;
                        for (int i = 0; i < duration; i++) {
                            int timeIndex = 0;
                            int checkTimeIndex = (timeIndex + i) % times.length;
                            timeModel.addElement(times[checkTimeIndex]);
                        }
                        foundAvailableTime = true;
                        break;
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
    CBPosition.setSelectedIndex(-1);
    CBSemester.setSelectedIndex(-1);
    CBYearLevel.setSelectedIndex(-1);
    CBSection.setSelectedIndex(-1);
    CBSubject.setSelectedIndex(-1);
    CBTime.setSelectedIndex(-1);
    CBRoom.setSelectedIndex(-1);
    CBMonth.setSelectedIndex(-1);
    CBWeek.setSelectedIndex(-1);
    CBDay.setSelectedIndex(-1);


            
    }//GEN-LAST:event_BAddActionPerformed

    private void CBSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBSectionActionPerformed

    private void CBYearLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBYearLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBYearLevelActionPerformed

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
            try {
                new LabSched().setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LabSched.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BLogout;
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
