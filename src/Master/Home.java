package Master;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.*;
import javax.swing.JOptionPane;

public class Home extends javax.swing.JFrame {

    public static String user;
    String pwrd;
    Statement st;

    public Home() {
        initComponents();
        this.setResizable(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        signup = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        passwd = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(750, 580));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 580));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("UserID");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(110, 20, 60, 20);
        jPanel2.add(userid);
        userid.setBounds(160, 20, 120, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Password");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(300, 20, 60, 20);

        signup.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        signup.setText("New User");
        signup.setFocusPainted(false);
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        jPanel2.add(signup);
        signup.setBounds(600, 20, 110, 30);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setText("GO");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton1KeyTyped(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(510, 20, 70, 30);
        jPanel2.add(passwd);
        passwd.setBounds(370, 20, 130, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 750, 60);

        jLabel3.setBackground(new java.awt.Color(0, 153, 153));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Deep Learning-Based Threat Detection and Prevention System for Cyber Attacks");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 70, 730, 60);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/data-mining.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 130, 740, 420);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 560);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        user = userid.getText();
        pwrd = passwd.getText();
        if ("admin".equals(user) && "admin".equals(pwrd)) {
            MasterPage mpo = new MasterPage();
            mpo.setVisible(true);
            this.dispose();
        } else {
            try {
                Dbconn db = new Dbconn();
                Connection con = (Connection) db.conn();
                st = (Statement) con.createStatement();
                Statement st1 = (Statement) con.createStatement();
                Statement st2 = (Statement) con.createStatement();
                Statement st3 = (Statement) con.createStatement();
                ResultSet rs = (ResultSet) st.executeQuery("select * from userdetail where uid='" + user + "' && pwd='" + pwrd + "'");
                if (rs.next()) {
                   
                    st1.executeUpdate("TRUNCATE table testdata");
                    st2.executeUpdate("TRUNCATE table studentsdata");
                    st3.executeUpdate("TRUNCATE table traindata");
                     MasterPage pd = new MasterPage();
                    pd.setVisible(true);
                    this.hide();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Enter username or password correctly");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed

        NewUser nu = new NewUser();
        nu.setVisible(true);
        this.dispose();
        this.setResizable(false);


    }//GEN-LAST:event_signupActionPerformed

    private void jButton1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyTyped
//       user=userid.getText();
//        pwrd=passwd.getText();
//        try{
//            Dbconn db=new Dbconn();
//            Connection con=(Connection) db.conn();
//            st=(Statement) con.createStatement();
//
//            ResultSet rs=(ResultSet) st.executeQuery("select * from userdetail where userid='"+user+"' && password='"+pwrd+"'");
//            if(rs.next())
//            {
//                MasterPage p = new MasterPage();
//                this.hide();
//                p.show();
//
//            }
//            else
//            JOptionPane.showMessageDialog(rootPane, "Enter username or password correctly");
//        }catch(Exception e)
//        {
//            System.out.println(e);
//        }
    }//GEN-LAST:event_jButton1KeyTyped

    public String pass() {

        return user;

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField passwd;
    private javax.swing.JButton signup;
    private javax.swing.JTextField userid;
    // End of variables declaration//GEN-END:variables
}
